import { api } from "./api";
import type { Producto } from "./types";

export const productService = {
    listar: () => api.get<Producto[]>("/producto/listar"),

    listarCompletos: () => api.get<Producto[]>("/producto/completo"),

    porMarca: (idMarca: number) => api.get<Producto[]>(`/producto/por-marca/${idMarca}`),

    obtener: (id: number) => api.get<Producto>(`/producto/${id}`),
};
