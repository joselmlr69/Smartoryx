import { api } from "./api";
import type { Categoria } from "./types";

export const categoriaService = {
    listar: () => api.get<Categoria[]>("/categoria/listar"),
    obtener: (id: number) => api.get<Categoria>(`/categoria/${id}`),
};
