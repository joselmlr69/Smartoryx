export interface Categoria {
  id: number;
  nombre?: string;
}

export interface Marca {
  id: number;
  nombre?: string;
}

export interface Proveedor {
  id?: number;
  nombre?: string;
  ruc?: string;
  telefono?: string;
}

export interface Producto {
  id?: number;
  nombre: string;
  descripcion: string;
  precio: number;
  stock: number;
  estado: number | null;
  imagenUrl?: string;
  categoria: Categoria;
  marca: Marca;
  proveedor: Proveedor;
}
