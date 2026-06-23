import { Usuario } from './usuario'; // Importa la interfaz del archivo de usuarios

export interface Direccion {
  id: number; // Autoincremental en BD
  direccion: string;
  ciudad: string;
  referencia: string;
  usuario?: Partial<Usuario>; // 🔥 CORREGIDO: Evita duplicar la interfaz usando Partial
}