import { useEffect, useState } from "react";
import { useStore } from "@nanostores/react";
import {
    cartItems,
    totalPrice,
    currentCarrito,
    clearCart,
} from "../../store/cartStore";
import { currentUser } from "../../store/authStore";
import { direccionService } from "../../services/direccionService";
import { metodoPagoService } from "../../services/metodoPagoService";
import { ventaService } from "../../services/ventaService";
import { getApiErrorMessage, getImageUrl } from "../../services/api";
import { toast } from "sonner";
import type { Direccion, MetodoPago, Venta } from "../../services/types";

type Step = "address" | "payment" | "review";

function formatPrice(price: number) {
    return new Intl.NumberFormat("es-PE", { style: "currency", currency: "PEN" })
        .format(price)
        .replace("S/", "S/ ");
}

export default function CheckoutFlow() {
    const user = useStore(currentUser);
    const items = useStore(cartItems);
    const total = useStore(totalPrice);
    const carrito = useStore(currentCarrito);

    const [step, setStep] = useState<Step>("address");
    const [direcciones, setDirecciones] = useState<Direccion[]>([]);
    const [selectedDireccion, setSelectedDireccion] = useState<number | null>(null);
    const [showNewAddress, setShowNewAddress] = useState(false);
    const [newAddress, setNewAddress] = useState({ direccion: "", ciudad: "", referencia: "" });

    const [metodosPago, setMetodosPago] = useState<MetodoPago[]>([]);
    const [selectedMetodo, setSelectedMetodo] = useState<number | null>(null);

    const [loading, setLoading] = useState(false);
    const [completedVenta, setCompletedVenta] = useState<Venta | null>(null);

    useEffect(() => {
        if (!user) {
            window.location.href = "/login?redirect=/checkout";
            return;
        }
        Promise.all([
            direccionService.listarPorUsuario(user.id).catch(() => [] as Direccion[]),
            metodoPagoService.listar().catch(() => [] as MetodoPago[]),
        ]).then(([dirs, metodos]) => {
            setDirecciones(dirs);
            if (dirs.length === 0) setShowNewAddress(true);
            else setSelectedDireccion(dirs[0].id);
            setMetodosPago(metodos);
            if (metodos.length > 0) setSelectedMetodo(metodos[0].id);
        });
    }, [user?.id]);

    if (!user) return null;

    if (items.length === 0 && !completedVenta) {
        return (
            <div className="text-center py-12">
                <p className="text-slate-500 mb-4">Tu carrito está vacío.</p>
                <a href="/" className="btn btn-primary rounded-2xl">Ver productos</a>
            </div>
        );
    }

    const handleCreateAddress = async () => {
        if (!newAddress.direccion || !newAddress.ciudad) {
            toast.error("Completa los campos obligatorios");
            return;
        }
        setLoading(true);
        try {
            const dir = await direccionService.agregar(user.id, newAddress);
            setDirecciones((prev) => [...prev, dir]);
            setSelectedDireccion(dir.id);
            setShowNewAddress(false);
            setNewAddress({ direccion: "", ciudad: "", referencia: "" });
            toast.success("Dirección agregada");
        } catch (err) {
            toast.error(getApiErrorMessage(err, "No se pudo guardar la dirección"));
        } finally {
            setLoading(false);
        }
    };

    const handleConfirm = async () => {
        if (!selectedDireccion) {
            toast.error("Selecciona una dirección");
            return;
        }
        if (!selectedMetodo) {
            toast.error("Selecciona un método de pago");
            return;
        }
        setLoading(true);
        try {
            const venta = await ventaService.generarDesdeCarrito(user.id);
            await ventaService.pagar(venta.id, selectedMetodo);
            await clearCart();
            setCompletedVenta(venta);
            toast.success("¡Compra realizada con éxito!");
        } catch (err) {
            toast.error(getApiErrorMessage(err, "No se pudo procesar la compra"));
        } finally {
            setLoading(false);
        }
    };

    if (completedVenta) {
        return (
            <div className="bg-white rounded-3xl p-8 md:p-10 shadow-sm border border-slate-100 text-center space-y-6">
                <div className="w-20 h-20 bg-success/10 text-success rounded-full flex items-center justify-center mx-auto">
                    <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="3" strokeLinecap="round" strokeLinejoin="round"><path d="M20 6 9 17l-5-5" /></svg>
                </div>
                <div>
                    <h2 className="text-3xl font-black text-slate-950 uppercase italic tracking-tighter">
                        ¡Compra Exitosa!
                    </h2>
                    <p className="text-slate-500 mt-2">Tu pedido #{completedVenta.id} ha sido confirmado.</p>
                </div>
                <div className="bg-slate-50 rounded-2xl p-6 text-left space-y-3">
                    <p className="text-[10px] font-black uppercase tracking-widest text-slate-400">Resumen</p>
                    {completedVenta.detalles.map((d) => (
                        <div key={d.idDetalle} className="flex justify-between text-sm">
                            <span className="text-slate-700">{d.producto.nombre} x{d.cantidad}</span>
                            <span className="font-black text-slate-900">{formatPrice(d.subtotal)}</span>
                        </div>
                    ))}
                    <div className="pt-3 border-t border-slate-200 flex justify-between font-black text-slate-950">
                        <span>Total</span>
                        <span>{formatPrice(completedVenta.total)}</span>
                    </div>
                </div>
                <div className="flex gap-3">
                    <a href="/" className="flex-1 btn btn-ghost rounded-2xl">Volver al Inicio</a>
                    <a href="/mis-compras" className="flex-1 btn btn-primary rounded-2xl">Mis Compras</a>
                </div>
            </div>
        );
    }

    return (
        <div className="space-y-8">
            <ol className="flex items-center gap-4 text-xs">
                <Step n={1} active={step === "address"} done={selectedDireccion !== null} label="Dirección" />
                <span className="text-slate-300">—</span>
                <Step n={2} active={step === "payment"} done={selectedMetodo !== null} label="Pago" />
                <span className="text-slate-300">—</span>
                <Step n={3} active={step === "review"} done={false} label="Confirmar" />
            </ol>

            {step === "address" && (
                <div className="bg-white rounded-3xl p-6 md:p-8 shadow-sm border border-slate-100 space-y-6">
                    <h2 className="text-xl font-black text-slate-950 uppercase italic tracking-tighter">
                        Dirección de Envío
                    </h2>
                    {direcciones.length > 0 && (
                        <div className="space-y-3">
                            {direcciones.map((d) => (
                                <label
                                    key={d.id}
                                    className={`block p-4 rounded-2xl border-2 cursor-pointer transition-all ${
                                        selectedDireccion === d.id
                                            ? "border-primary bg-primary/5"
                                            : "border-slate-200 hover:border-slate-300"
                                    }`}
                                >
                                    <input
                                        type="radio"
                                        name="direccion"
                                        value={d.id}
                                        checked={selectedDireccion === d.id}
                                        onChange={() => setSelectedDireccion(d.id)}
                                        className="hidden"
                                    />
                                    <p className="font-bold text-slate-900">{d.direccion}</p>
                                    <p className="text-sm text-slate-500">{d.ciudad}</p>
                                    {d.referencia && (
                                        <p className="text-xs text-slate-400 mt-1">Ref: {d.referencia}</p>
                                    )}
                                </label>
                            ))}
                        </div>
                    )}

                    {!showNewAddress ? (
                        <button
                            onClick={() => setShowNewAddress(true)}
                            className="btn btn-ghost rounded-2xl"
                        >
                            + Agregar nueva dirección
                        </button>
                    ) : (
                        <div className="space-y-3 p-4 bg-slate-50 rounded-2xl">
                            <input
                                type="text"
                                placeholder="Dirección (calle y número)"
                                className="input input-bordered w-full"
                                value={newAddress.direccion}
                                onChange={(e) => setNewAddress({ ...newAddress, direccion: e.target.value })}
                            />
                            <input
                                type="text"
                                placeholder="Ciudad"
                                className="input input-bordered w-full"
                                value={newAddress.ciudad}
                                onChange={(e) => setNewAddress({ ...newAddress, ciudad: e.target.value })}
                            />
                            <input
                                type="text"
                                placeholder="Referencia (opcional)"
                                className="input input-bordered w-full"
                                value={newAddress.referencia}
                                onChange={(e) => setNewAddress({ ...newAddress, referencia: e.target.value })}
                            />
                            <div className="flex gap-2">
                                <button
                                    onClick={handleCreateAddress}
                                    disabled={loading}
                                    className="btn btn-primary rounded-2xl"
                                >
                                    {loading ? "Guardando..." : "Guardar"}
                                </button>
                                {direcciones.length > 0 && (
                                    <button
                                        onClick={() => setShowNewAddress(false)}
                                        className="btn btn-ghost rounded-2xl"
                                    >
                                        Cancelar
                                    </button>
                                )}
                            </div>
                        </div>
                    )}

                    <button
                        onClick={() => setStep("payment")}
                        disabled={!selectedDireccion}
                        className="w-full btn btn-primary rounded-2xl"
                    >
                        Continuar al Pago
                    </button>
                </div>
            )}

            {step === "payment" && (
                <div className="bg-white rounded-3xl p-6 md:p-8 shadow-sm border border-slate-100 space-y-6">
                    <h2 className="text-xl font-black text-slate-950 uppercase italic tracking-tighter">
                        Método de Pago
                    </h2>
                    <div className="space-y-3">
                        {metodosPago.length === 0 ? (
                            <p className="text-slate-500">No hay métodos de pago disponibles.</p>
                        ) : (
                            metodosPago.map((m) => (
                                <label
                                    key={m.id}
                                    className={`block p-4 rounded-2xl border-2 cursor-pointer transition-all ${
                                        selectedMetodo === m.id
                                            ? "border-primary bg-primary/5"
                                            : "border-slate-200 hover:border-slate-300"
                                    }`}
                                >
                                    <input
                                        type="radio"
                                        name="metodo"
                                        value={m.id}
                                        checked={selectedMetodo === m.id}
                                        onChange={() => setSelectedMetodo(m.id)}
                                        className="hidden"
                                    />
                                    <p className="font-bold text-slate-900">{m.nombre}</p>
                                </label>
                            ))
                        )}
                    </div>
                    <div className="flex gap-3">
                        <button
                            onClick={() => setStep("address")}
                            className="btn btn-ghost rounded-2xl"
                        >
                            Atrás
                        </button>
                        <button
                            onClick={() => setStep("review")}
                            disabled={!selectedMetodo}
                            className="flex-1 btn btn-primary rounded-2xl"
                        >
                            Revisar Compra
                        </button>
                    </div>
                </div>
            )}

            {step === "review" && (
                <div className="bg-white rounded-3xl p-6 md:p-8 shadow-sm border border-slate-100 space-y-6">
                    <h2 className="text-xl font-black text-slate-950 uppercase italic tracking-tighter">
                        Confirma tu Compra
                    </h2>

                    <div>
                        <p className="text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2">Productos</p>
                        <div className="space-y-3">
                            {items.map((it) => (
                                <div key={it.id} className="flex gap-3 items-center">
                                    <img src={it.image} alt={it.name} className="w-12 h-12 object-cover rounded-xl" />
                                    <div className="flex-1 min-w-0">
                                        <p className="text-sm font-bold text-slate-900 truncate">{it.name}</p>
                                        <p className="text-xs text-slate-500">x{it.quantity}</p>
                                    </div>
                                    <p className="text-sm font-black text-slate-900">{formatPrice(it.price * it.quantity)}</p>
                                </div>
                            ))}
                        </div>
                    </div>

                    <div>
                        <p className="text-[10px] font-black uppercase tracking-widest text-slate-400 mb-2">Total a pagar</p>
                        <p className="text-3xl font-black text-slate-950 italic">{formatPrice(total)}</p>
                    </div>

                    <div className="flex gap-3">
                        <button
                            onClick={() => setStep("payment")}
                            className="btn btn-ghost rounded-2xl"
                        >
                            Atrás
                        </button>
                        <button
                            onClick={handleConfirm}
                            disabled={loading}
                            className="flex-1 btn btn-primary rounded-2xl"
                        >
                            {loading ? <span className="loading loading-spinner loading-sm" /> : "Confirmar y Pagar"}
                        </button>
                    </div>
                </div>
            )}

            <div className="bg-white rounded-3xl p-6 shadow-sm border border-slate-100 space-y-4">
                <p className="text-[10px] font-black uppercase tracking-widest text-slate-400">Resumen</p>
                <div className="flex justify-between text-sm">
                    <span>Subtotal ({items.length} productos)</span>
                    <span className="font-black">{formatPrice(total)}</span>
                </div>
                <div className="flex justify-between text-sm">
                    <span>Envío</span>
                    <span className="text-emerald-500 font-black">Gratis</span>
                </div>
                <div className="pt-4 border-t border-slate-100 flex justify-between items-center">
                    <span className="text-lg font-black uppercase italic">Total</span>
                    <span className="text-2xl font-black text-slate-950 italic">{formatPrice(total)}</span>
                </div>
            </div>
        </div>
    );
}

function Step({ n, active, done, label }: { n: number; active: boolean; done: boolean; label: string }) {
    return (
        <li className={`flex items-center gap-2 ${active ? "text-primary" : done ? "text-success" : "text-slate-400"}`}>
            <span className={`w-6 h-6 rounded-full flex items-center justify-center text-[10px] font-black ${
                active ? "bg-primary text-primary-content" : done ? "bg-success text-white" : "bg-slate-200"
            }`}>
                {done ? "✓" : n}
            </span>
            <span className="font-bold uppercase tracking-widest text-[10px]">{label}</span>
        </li>
    );
}
