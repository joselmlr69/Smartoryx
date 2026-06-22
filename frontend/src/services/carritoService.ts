import { api } from "./api";
import type { Carrito } from "./types";

export const carritoService = {
    obtener: (idUsuario: number) => api.get<Carrito>(`/carrito/${idUsuario}`),

    agregar: (idUsuario: number, idProducto: number, cantidad: number) =>
        api.post<Carrito>(`/carrito/agregar/${idUsuario}`, { idProducto, cantidad }),

    eliminar: (idUsuario: number, idProducto: number) =>
        api.del<void>(`/carrito/eliminar/${idUsuario}/${idProducto}`),

    vaciar: (idUsuario: number) => api.del<void>(`/carrito/vaciar/${idUsuario}`),

    total: (idUsuario: number) => api.get<number>(`/carrito/total/${idUsuario}`),
};
