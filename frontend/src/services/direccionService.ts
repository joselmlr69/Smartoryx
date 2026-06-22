import { api } from "./api";
import type { Direccion } from "./types";

export const direccionService = {
    listarPorUsuario: (idUsuario: number) =>
        api.get<Direccion[]>(`/direccion/usuario/${idUsuario}`),

    agregar: (idUsuario: number, body: { direccion: string; ciudad: string; referencia?: string }) =>
        api.post<Direccion>(`/direccion/agregar/${idUsuario}`, body),

    actualizar: (id: number, body: { direccion: string; ciudad: string; referencia?: string }) =>
        api.put<Direccion>(`/direccion/${id}`, body),

    eliminar: (id: number) => api.del<void>(`/direccion/${id}`),
};
