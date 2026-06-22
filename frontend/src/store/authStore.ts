import { atom, computed } from "nanostores";
import { api } from "../services/api";
import type { Usuario } from "../services/types";

export const currentUser = atom<Usuario | null>(null);
export const authLoaded = atom<boolean>(false);

export async function loadSesion(): Promise<Usuario | null> {
    try {
        const user = await api.get<Usuario>("/usuario/sesion", { redirectOn401: false });
        currentUser.set(user);
        return user;
    } catch {
        currentUser.set(null);
        return null;
    } finally {
        authLoaded.set(true);
    }
}

export async function login(correo: string, password: string): Promise<Usuario> {
    const user = await api.post<Usuario>("/usuario/login", { correo, password });
    currentUser.set(user);
    return user;
}

export async function logout(): Promise<void> {
    try {
        await api.del("/usuario/logout");
    } finally {
        currentUser.set(null);
    }
}

export function isAdmin(): boolean {
    const u = currentUser.get();
    return u?.rol?.nombre?.toUpperCase() === "ADMIN";
}

export const isAuthenticated = computed(currentUser, (u) => u !== null);
