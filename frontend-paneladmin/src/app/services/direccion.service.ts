import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Direccion } from '../models/direccion';

@Injectable({
  providedIn: 'root'
})
export class DireccionService {

  private api = 'http://localhost:8080/direccion';

  constructor(private http: HttpClient) {}

  listar(): Observable<Direccion[]> {
    return this.http.get<Direccion[]>(`${this.api}/listar`);
  }

  obtener(id: number): Observable<Direccion> {
    return this.http.get<Direccion>(`${this.api}/${id}`);
  }

  guardar(id_usuario: number, direccion: Direccion): Observable<any> {
    return this.http.post(`${this.api}/agregar/${id_usuario}`, direccion);
  }

  actualizar(id: number, direccion: Direccion): Observable<any> {
    return this.http.put(`${this.api}/${id}`, direccion);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.api}/${id}`);
  }
}