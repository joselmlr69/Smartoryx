export interface Categoria {
    id: number;
    nombre: string;
}

export interface Marca {
    id: number;
    nombre: string;
}

export interface Proveedor {
    id: number;
    nombre: string;
    ruc: string;
    telefono: string;
}

export interface Rol {
    id: number;
    nombre: string;
}

export interface ProductoSimple {
    id: number;
    nombre: string;
    precio: number;
    imagenUrl: string;
}

export interface Producto {
    id: number;
    nombre: string;
    descripcion: string;
    precio: number;
    stock: number;
    estado: number;
    imagenUrl: string;
    categoria: Categoria;
    marca: Marca;
    proveedor: Proveedor;
}

export interface UsuarioSimple {
    id: number;
    nombre: string;
    apellido: string;
    correo: string;
}

export interface Usuario {
    id: number;
    nombre: string;
    apellido: string;
    correo: string;
    rol: Rol;
}

export interface CarritoDetalle {
    idDetalle: number;
    producto: ProductoSimple;
    cantidad: number;
    subtotal: number;
}

export interface Carrito {
    id: number;
    usuario: UsuarioSimple;
    detalles: CarritoDetalle[];
    total: number;
}

export interface Direccion {
    id: number;
    direccion: string;
    ciudad: string;
    referencia: string;
}

export interface MetodoPago {
    id: number;
    nombre: string;
    estado: number;
}

export interface Pago {
    id: number;
    monto: number;
    metodoPago: MetodoPago;
}

export interface VentaDetalle {
    idDetalle: number;
    producto: ProductoSimple;
    cantidad: number;
    precio: number;
    subtotal: number;
}

export interface Venta {
    id: number;
    fecha: string;
    total: number;
    estado: string;
    usuario: UsuarioSimple;
    detalles: VentaDetalle[];
}

export interface Envio {
    id: number;
    estado: string;
    fechaEnvio: string;
}

export interface ApiError {
    status: number;
    message: string;
    errors?: Record<string, string>;
}
