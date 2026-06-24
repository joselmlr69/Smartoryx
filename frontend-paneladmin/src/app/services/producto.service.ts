import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root',
})
export class ProductoService {
  private api = 'http://localhost:8080/producto';

  constructor(private http: HttpClient) {}

  listar(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.api}/listar`);
  }

  listarTodos(page: number = 0, size: number = 20): Observable<any> {
    return this.http.get<any>(`${this.api}/admin/listar-todos?page=${page}&size=${size}`, { withCredentials: true });
  }

  obtener(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.api}/${id}`);
  }

  guardar(producto: Producto): Observable<any> {
    return this.http.post(`${this.api}/agregar`, producto, { withCredentials: true });
  }

  eliminar(id: number) {
    return this.http.delete(`${this.api}/${id}`, { withCredentials: true });
  }

  actualizar(id: number, producto: any) {
    return this.http.put(`${this.api}/${id}`, producto, { withCredentials: true });
  }

  listarCategorias(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/categoria/listar');
  }

  listarMarcas(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/marca/listar');
  }

  listarProveedores(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/proveedor/listar', { withCredentials: true });
  }
}
