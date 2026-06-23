import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root',
})
export class CategoriaService {

  private url = 'http://localhost:8080/categoria';

  constructor(private http: HttpClient) {}

  // Este funciona sin credenciales porque en Spring es .permitAll()
  listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(`${this.url}/listar`);
  }

  // Si necesitas que solo usuarios logueados vean una categoría por ID, agrégale credentials
  obtener(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}/${id}`, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Envía la sesión para pasar el filtro .hasRole("ADMIN")
  registrar(categoria: Categoria): Observable<any> {
    return this.http.post(`${this.url}/agregar`, categoria, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Envía la sesión para pasar el filtro .hasRole("ADMIN")
  actualizar(id: number, categoria: Categoria): Observable<any> {
    return this.http.put(`${this.url}/${id}`, categoria, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Envía la sesión para pasar el filtro .hasRole("ADMIN")
  eliminar(id: number): Observable<any> {
    return this.http.delete(`${(`${this.url}/${id}`)}`, { withCredentials: true });
  }
}