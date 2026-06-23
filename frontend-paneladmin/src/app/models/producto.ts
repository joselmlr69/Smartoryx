export interface Categoria {
  id_categoria: number;
  nombre?: string;
}

export interface Marca {
  id_marca: number;
  nombre?: string;
}

export interface Proveedor {
  id_proveedor: number;
  nombre?: string;
  ruc?: string;
  telefono?: string;
}

export interface Producto {
  id_producto?: number; // Opcional porque al registrar no existe todavía
  nombre: string;
  descripcion: string;
  precio: number;
  stock: number;
  estado: number | null;
  imagen_url?: string; // 🔥 CORREGIDO: Faltaba este campo de la BD
  categoria: Categoria;
  marca: Marca;
  proveedor: Proveedor;
}