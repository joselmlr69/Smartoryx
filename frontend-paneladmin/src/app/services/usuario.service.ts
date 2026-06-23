import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  private url = 'http://localhost:8080/usuario';
  
  constructor(private http: HttpClient) {}

  listar(): Observable<Usuario[]> {
  return this.http.get<Usuario[]>(`${this.url}/listar`, { withCredentials: true });
}

obtener(id: number): Observable<Usuario> {
  return this.http.get<Usuario>(`${this.url}/${id}`, { withCredentials: true });
}

registrar(usuario: Usuario): Observable<any> {
  return this.http.post(`${this.url}/agregar`, usuario, { withCredentials: true });
}
actualizar(id: number, usuario: Usuario): Observable<any> {
  return this.http.put(`${this.url}/${id}`, usuario, {
    withCredentials: true // 👈 CLAVE
  });
}

 eliminar(id: number): Observable<any> {
  return this.http.delete(`${this.url}/${id}`, { withCredentials: true });
}

}
