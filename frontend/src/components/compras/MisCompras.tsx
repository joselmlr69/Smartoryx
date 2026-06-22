import { useStore } from "@nanostores/react";
import { useEffect, useState } from "react";
import { currentUser, loadSesion, authLoaded } from "../../store/authStore";
import { ventaService } from "../../services/ventaService";
import { getImageUrl, getApiErrorMessage } from "../../services/api";
import type { Venta } from "../../services/types";

function formatPrice(price: number) {
    return new Intl.NumberFormat("es-PE", { style: "currency", currency: "PEN" })
        .format(price)
        .replace("S/", "S/ ");
}

function formatDate(date: string | Date) {
    const d = typeof date === "string" ? new Date(date) : date;
    return d.toLocaleDateString("es-PE", {
        year: "numeric",
        month: "long",
        day: "numeric",
    });
}

export default function MisCompras() {
    const user = useStore(currentUser);
    const loaded = useStore(authLoaded);
    const [ventas, setVentas] = useState<Venta[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadSesion();
    }, []);

    useEffect(() => {
        if (!loaded) return;
        if (!user) {
            window.location.href = "/login?redirect=/mis-compras";
            return;
        }
        setLoading(true);
        ventaService
            .listarPorUsuario(user.id)
            .then(setVentas)
            .catch((err) => {
                console.error("Error cargando ventas:", getApiErrorMessage(err));
                setVentas([]);
            })
            .finally(() => setLoading(false));
    }, [user?.id, loaded]);

    if (!loaded || loading) {
        return (
            <div className="flex justify-center py-12">
                <span className="loading loading-spinner loading-lg text-primary" />
            </div>
        );
    }

    if (ventas.length === 0) {
        return (
            <div className="text-center py-16">
                <div className="w-24 h-24 bg-base-200 rounded-full flex items-center justify-center mx-auto mb-6">
                    <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" className="text-base-content/50"><path d="M6 2 3 6v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2V6l-3-4Z" /><path d="M3 6h18" /><path d="M16 10a4 4 0 0 1-8 0" /></svg>
                </div>
                <h2 className="text-2xl font-black text-slate-900 mb-2">Aún no tienes compras</h2>
                <p className="text-base-content/70 mb-6 max-w-md mx-auto">
                    Cuando completes una compra, aparecerá aquí con todos los detalles.
                </p>
                <a href="/" className="btn btn-primary rounded-2xl">
                    Explorar Catálogo
                </a>
            </div>
        );
    }

    return (
        <div className="space-y-6">
            {ventas.map((venta) => (
                <div
                    key={venta.id}
                    className="bg-white rounded-3xl p-6 shadow-sm border border-slate-100 space-y-4"
                >
                    <div className="flex items-center justify-between flex-wrap gap-3">
                        <div>
                            <p className="text-[10px] font-black uppercase tracking-widest text-slate-400">
                                Pedido
                            </p>
                            <p className="text-2xl font-black text-slate-950 italic">
                                #{venta.id}
                            </p>
                            <p className="text-xs text-slate-500 mt-1">
                                {formatDate(venta.fecha)}
                            </p>
                        </div>
                        <div className="text-right">
                            <span className="badge badge-success badge-lg font-black uppercase">
                                {venta.estado}
                            </span>
                            <p className="text-2xl font-black text-slate-950 mt-2 italic">
                                {formatPrice(venta.total)}
                            </p>
                        </div>
                    </div>

                    <div className="border-t border-slate-100 pt-4 space-y-3">
                        {venta.detalles.map((d) => (
                            <div key={d.idDetalle} className="flex gap-3 items-center">
                                <img
                                    src={getImageUrl(d.producto.imagenUrl)}
                                    alt={d.producto.nombre}
                                    className="w-14 h-14 object-cover rounded-xl bg-slate-100"
                                />
                                <div className="flex-1 min-w-0">
                                    <p className="font-bold text-slate-900 truncate">
                                        {d.producto.nombre}
                                    </p>
                                    <p className="text-xs text-slate-500">
                                        Cantidad: {d.cantidad} · {formatPrice(d.precio)} c/u
                                    </p>
                                </div>
                                <p className="text-sm font-black text-slate-900">
                                    {formatPrice(d.subtotal)}
                                </p>
                            </div>
                        ))}
                    </div>
                </div>
            ))}
        </div>
    );
}
