import { useState } from "react";
import { login } from "../../store/authStore";
import { getApiErrorMessage } from "../../services/api";
import { toast } from "sonner";

interface Props {
    redirect?: string;
}

export default function LoginForm({ redirect }: Props) {
    const [correo, setCorreo] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        try {
            await login(correo, password);
            toast.success("Sesión iniciada");
            const target = redirect || "/";
            window.location.href = target;
        } catch (err) {
            toast.error(getApiErrorMessage(err, "Credenciales inválidas"));
        } finally {
            setLoading(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-6">
            <div className="form-control w-full">
                <label className="label">
                    <span className="label-text font-bold text-[10px] uppercase tracking-widest text-slate-400">
                        Correo Electrónico
                    </span>
                </label>
                <input
                    type="email"
                    placeholder="tu@correo.com"
                    className="input input-bordered w-full rounded-xl bg-slate-50 border-slate-200 focus:border-primary focus:bg-white transition-all font-medium"
                    value={correo}
                    onChange={(e) => setCorreo(e.target.value)}
                    required
                    autoComplete="email"
                />
            </div>

            <div className="form-control w-full">
                <label className="label">
                    <span className="label-text font-bold text-[10px] uppercase tracking-widest text-slate-400">
                        Contraseña
                    </span>
                </label>
                <input
                    type="password"
                    placeholder="••••••••"
                    className="input input-bordered w-full rounded-xl bg-slate-50 border-slate-200 focus:border-primary focus:bg-white transition-all font-medium"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    autoComplete="current-password"
                />
            </div>

            <button
                type="submit"
                disabled={loading}
                className="w-full bg-primary hover:bg-primary/80 text-white rounded-2xl py-5 font-black uppercase tracking-widest text-sm shadow-xl shadow-primary/20 transition-all flex items-center justify-center gap-3 disabled:opacity-50"
            >
                {loading ? (
                    <span className="loading loading-spinner loading-sm" />
                ) : (
                    "Iniciar Sesión"
                )}
            </button>

            <p className="text-center text-sm text-slate-500">
                ¿No tienes cuenta?{" "}
                <a href="/register" className="text-primary font-bold hover:underline">
                    Regístrate
                </a>
            </p>
        </form>
    );
}
