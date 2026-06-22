import { atom, computed } from "nanostores";
import { carritoService } from "../services/carritoService";
import { getImageUrl, getApiErrorMessage } from "../services/api";
import { currentUser } from "./authStore";
import type { Carrito, CarritoDetalle } from "../services/types";
import { toast } from "sonner";

export interface CartItem {
    id: number;
    name: string;
    price: number;
    image: string;
    quantity: number;
    stock: number;
    slug?: string;
    productId: number;
}

export const isCartOpen = atom(false);
export const cartLoading = atom(false);

const initialCarrito: Carrito | null = null;
export const currentCarrito = atom<Carrito | null>(initialCarrito);

function detalleToItem(d: CarritoDetalle): CartItem {
    return {
        id: d.producto.id,
        productId: d.producto.id,
        name: d.producto.nombre,
        price: d.producto.precio,
        image: getImageUrl(d.producto.imagenUrl),
        quantity: d.cantidad,
        stock: 9999,
    };
}

function syncFromCarrito(carrito: Carrito | null) {
    if (!carrito) {
        currentCarrito.set(null);
        return;
    }
    currentCarrito.set(carrito);
}

export async function loadCarrito(): Promise<void> {
    const user = currentUser.get();
    if (!user) {
        currentCarrito.set(null);
        return;
    }
    cartLoading.set(true);
    try {
        const carrito = await carritoService.obtener(user.id);
        syncFromCarrito(carrito);
    } catch (err) {
        console.warn("No se pudo cargar el carrito:", getApiErrorMessage(err));
        currentCarrito.set(null);
    } finally {
        cartLoading.set(false);
    }
}

export async function addToCart(productId: number, qty: number = 1): Promise<{ success: boolean; message: string }> {
    const user = currentUser.get();
    if (!user) {
        return { success: false, message: "Debes iniciar sesión para agregar al carrito" };
    }
    try {
        const carrito = await carritoService.agregar(user.id, productId, qty);
        syncFromCarrito(carrito);
        return { success: true, message: "Producto agregado al carrito" };
    } catch (err) {
        return { success: false, message: getApiErrorMessage(err, "No se pudo agregar") };
    }
}

export async function removeFromCart(productId: number): Promise<void> {
    const user = currentUser.get();
    if (!user) return;
    try {
        await carritoService.eliminar(user.id, productId);
        await loadCarrito();
    } catch (err) {
        toast.error(getApiErrorMessage(err, "No se pudo eliminar"));
    }
}

export async function updateQuantity(productId: number, qty: number): Promise<{ success: boolean; message: string }> {
    if (qty <= 0) {
        await removeFromCart(productId);
        return { success: true };
    }
    const current = currentCarrito.get();
    if (!current) return { success: false, message: "Carrito no cargado" };
    const existing = current.detalles.find((d) => d.producto.id === productId);
    const delta = qty - (existing?.cantidad || 0);
    if (delta === 0) return { success: true };

    const user = currentUser.get();
    if (!user) return { success: false, message: "No autenticado" };

    if (delta > 0) {
        try {
            const carrito = await carritoService.agregar(user.id, productId, delta);
            syncFromCarrito(carrito);
            return { success: true };
        } catch (err) {
            return { success: false, message: getApiErrorMessage(err, "No se pudo actualizar") };
        }
    } else {
        try {
            await carritoService.eliminar(user.id, productId);
            if (qty > 0) {
                const carrito = await carritoService.agregar(user.id, productId, qty);
                syncFromCarrito(carrito);
            } else {
                await loadCarrito();
            }
            return { success: true };
        } catch (err) {
            return { success: false, message: getApiErrorMessage(err, "No se pudo actualizar") };
        }
    }
}

export async function clearCart(): Promise<void> {
    const user = currentUser.get();
    if (!user) return;
    try {
        await carritoService.vaciar(user.id);
        currentCarrito.set(null);
    } catch (err) {
        toast.error(getApiErrorMessage(err, "No se pudo vaciar"));
    }
}

export function toggleCart() {
    isCartOpen.set(!isCartOpen.get());
}

export function openCart() {
    isCartOpen.set(true);
}

export function closeCart() {
    isCartOpen.set(false);
}

export const cartItems = computed(currentCarrito, (carrito) => {
    if (!carrito) return [] as CartItem[];
    return carrito.detalles.map(detalleToItem);
});

export const totalPrice = computed(currentCarrito, (carrito) => carrito?.total ?? 0);

export const totalItems = computed(cartItems, (items) =>
    items.reduce((acc, item) => acc + item.quantity, 0)
);
