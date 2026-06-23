import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:8080/usuario';

  constructor(private http: HttpClient) {}

  private isBrowser(): boolean {
    return typeof window !== 'undefined';
  }

  login(correo: string, password: string): Observable<Usuario> {
    return this.http.post<Usuario>(`${this.url}/login`, {
      correo,
      password
    },
    { withCredentials: true });
  }

  setUsuario(usuario: Usuario): void {
    if (!this.isBrowser()) return;
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }

  getUsuario(): Usuario | null {
    if (!this.isBrowser()) return null;

    const data = localStorage.getItem('usuario');
    return data ? JSON.parse(data) : null;
  }

  logout(): void {
    if (!this.isBrowser()) return;
    localStorage.removeItem('usuario');
  }

  isLogged(): boolean {
    return this.getUsuario() !== null;
  }

  isAdmin(): boolean {
    const user = this.getUsuario();
    return user?.rol?.id === 1;
  }

  isCliente(): boolean {
    const user = this.getUsuario();
    return user?.rol?.id === 2;
  }

  getRol(): string | null {
    const user = this.getUsuario();
    return user ? user.rol.nombre : null;
  }
}