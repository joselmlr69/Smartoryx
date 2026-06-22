import { api } from "./api";
import type { Marca } from "./types";

export const marcaService = {
    listar: () => api.get<Marca[]>("/marca/listar"),

    obtener: (id: number) => api.get<Marca>(`/marca/${id}`),
};
