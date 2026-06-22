import { useState } from "react";
import { api, getApiErrorMessage } from "../../services/api";
import type { Usuario } from "../../services/types";
import { login } from "../../store/authStore";
import { toast } from "sonner";

export default function RegisterForm() {
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [correo, setCorreo] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [fieldErrors, setFieldErrors] = useState<Record<string, string>>({});

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setFieldErrors({});
        try {
            await api.post<Usuario>("/usuario/agregar", {
                nombre,
                apellido,
                correo,
                password,
                idRol: 2,
            });
            await login(correo, password);
            toast.success("Cuenta creada. Bienvenido a Smartoryx");
            window.location.href = "/";
        } catch (err: any) {
            if (err?.errors) {
                setFieldErrors(err.errors);
            }
            toast.error(getApiErrorMessage(err, "No se pudo crear la cuenta"));
        } finally {
            setLoading(false);
        }
    };

    const inputClass = (field: string) =>
        `input input-bordered w-full rounded-xl bg-slate-50 border-slate-200 focus:border-primary focus:bg-white transition-all font-medium ${
            fieldErrors[field] ? "input-error" : ""
        }`;

    return (
        <form onSubmit={handleSubmit} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div className="form-control w-full">
                    <label className="label">
                        <span className="label-text font-bold text-[10px] uppercase tracking-widest text-slate-400">
                            Nombre
                        </span>
                    </label>
                    <input
                        type="text"
                        placeholder="Juan"
                        className={inputClass("nombre")}
                        value={nombre}
                        onChange={(e) => setNombre(e.target.value)}
                        required
                    />
                </div>
                <div className="form-control w-full">
                    <label className="label">
                        <span className="label-text font-bold text-[10px] uppercase tracking-widest text-slate-400">
                            Apellido
                        </span>
                    </label>
                    <input
                        type="text"
                        placeholder="Pérez"
                        className={inputClass("apellido")}
                        value={apellido}
                        onChange={(e) => setApellido(e.target.value)}
                        required
                    />
                </div>
            </div>

            <div className="form-control w-full">
                <label className="label">
                    <span className="label-text font-bold text-[10px] uppercase tracking-widest text-slate-400">
                        Correo Electrónico
                    </span>
                </label>
                <input
                    type="email"
                    placeholder="tu@correo.com"
                    className={inputClass("correo")}
                    value={correo}
                    onChange={(e) => setCorreo(e.target.value)}
                    required
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
                    placeholder="Mínimo 3 caracteres"
                    className={inputClass("password")}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    minLength={3}
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
                    "Crear Cuenta"
                )}
            </button>

            <p className="text-center text-sm text-slate-500">
                ¿Ya tienes cuenta?{" "}
                <a href="/login" className="text-primary font-bold hover:underline">
                    Inicia sesión
                </a>
            </p>
        </form>
    );
}
