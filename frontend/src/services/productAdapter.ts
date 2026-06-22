import { getImageUrl } from "../services/api";
import type { Producto } from "../services/types";

export interface ProductCardData {
    id: number;
    title: string;
    name: string;
    price: number;
    images: string[];
    stock: number;
    slug: string;
    category?: string;
    badge?: string;
}

export function productoToCardData(p: Producto): ProductCardData {
    const slug = slugify(p.nombre);
    return {
        id: p.id,
        title: p.nombre,
        name: p.nombre,
        price: p.precio,
        images: [p.imagenUrl ? getImageUrl(p.imagenUrl) : "/placeholder.svg"],
        stock: p.stock,
        slug,
        category: p.marca?.nombre,
        badge: p.estado === 0 ? "Agotado" : undefined,
    };
}

export function slugify(text: string): string {
    return text
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .replace(/[^a-z0-9]+/g, "-")
        .replace(/(^-|-$)/g, "");
}

export const productSlugMap = new Map<string, Producto>();

export function registerProductSlug(p: Producto) {
    productSlugMap.set(slugify(p.nombre), p);
    productSlugMap.set(String(p.id), p);
}

export function findProductBySlug(slug: string): Producto | undefined {
    return productSlugMap.get(slug);
}
