import { api } from "./api";
import type { MetodoPago } from "./types";

export const metodoPagoService = {
    listar: () => api.get<MetodoPago[]>("/metodo-pago/listar"),
    obtener: (id: number) => api.get<MetodoPago>(`/metodo-pago/${id}`),
};
