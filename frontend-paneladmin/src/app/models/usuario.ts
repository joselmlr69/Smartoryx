export interface Rol {
  id: number;
  nombre: string;
}

export interface Usuario {
  id: number;
  nombre: string;
  apellido: string;
  correo: string;
  password?: string; // Opcional, ya que al listar o editar no siempre viaja la clave
  rol: Rol;
  estado: number | null;
}