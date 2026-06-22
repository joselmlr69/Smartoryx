import { api } from "./api";
import type { Venta, Pago } from "./types";

export const ventaService = {
    listarPorUsuario: (idUsuario: number) =>
        api.get<Venta[]>(`/venta/usuario/${idUsuario}`),

    obtener: (id: number) => api.get<Venta>(`/venta/${id}`),

    generarDesdeCarrito: (idUsuario: number) =>
        api.post<Venta>(`/venta/generar-desde-carrito/${idUsuario}`),

    pagar: (idVenta: number, idMetodo: number) =>
        api.post<Pago>("/venta/pagar", { idVenta, idMetodo }),
};
