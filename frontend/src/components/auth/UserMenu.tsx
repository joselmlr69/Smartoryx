import { useStore } from "@nanostores/react";
import { useEffect, useState } from "react";
import { currentUser, authLoaded, logout } from "../../store/authStore";

export default function UserMenu() {
    const user = useStore(currentUser);
    const loaded = useStore(authLoaded);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        const close = (e: MouseEvent) => {
            const target = e.target as HTMLElement;
            if (!target.closest("#user-menu-wrapper")) {
                setOpen(false);
            }
        };
        if (open) document.addEventListener("click", close);
        return () => document.removeEventListener("click", close);
    }, [open]);

    if (!loaded) {
        return (
            <div className="btn btn-ghost btn-circle skeleton w-10 h-10" aria-hidden />
        );
    }

    if (!user) {
        return (
            <div className="flex items-center gap-2">
                <a
                    href="/login"
                    className="hidden sm:inline-flex btn btn-outline btn-primary btn-sm rounded-xl font-bold uppercase tracking-widest text-[10px] hover:bg-primary hover:text-primary-content hover:border-primary"
                >
                    Ingresar
                </a>
                <a
                    href="/register"
                    className="btn btn-primary btn-sm rounded-xl font-bold uppercase tracking-widest text-[10px] hover:bg-accent hover:border-accent"
                >
                    Registro
                </a>
            </div>
        );
    }

    const initials = (user.nombre?.[0] || "") + (user.apellido?.[0] || "");

    return (
        <div id="user-menu-wrapper" className="relative">
            <button
                onClick={() => setOpen(!open)}
                className="btn btn-ghost btn-circle hover:bg-primary/10"
                aria-label="Menú de usuario"
            >
                <div className="w-9 h-9 rounded-full bg-primary text-primary-content flex items-center justify-center font-black text-xs uppercase">
                    {initials || "U"}
                </div>
            </button>

            {open && (
                <div className="absolute right-0 top-full mt-2 w-56 bg-base-100 rounded-2xl shadow-xl border border-base-200 py-2 z-50">
                    <div className="px-4 py-3 border-b border-base-200">
                        <p className="text-sm font-black text-slate-900 truncate">
                            {user.nombre} {user.apellido}
                        </p>
                        <p className="text-[10px] font-bold text-slate-400 truncate uppercase tracking-widest">
                            {user.correo}
                        </p>
                    </div>
                    <a
                        href="/mis-compras"
                        className="block px-4 py-3 text-sm font-bold text-slate-700 hover:bg-base-200 transition-colors"
                    >
                        Mis Compras
                    </a>
                    {user.rol?.nombre?.toUpperCase() === "ADMIN" && (
                        <a
                            href="/admin"
                            className="block px-4 py-3 text-sm font-bold text-slate-700 hover:bg-base-200 transition-colors"
                        >
                            Panel Admin
                        </a>
                    )}
                    <button
                        onClick={async () => {
                            await logout();
                            window.location.href = "/";
                        }}
                        className="w-full text-left px-4 py-3 text-sm font-bold text-error hover:bg-error/10 transition-colors"
                    >
                        Cerrar Sesión
                    </button>
                </div>
            )}
        </div>
    );
}
