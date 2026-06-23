import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Proveedor } from '../models/proveedor';

@Injectable({
  providedIn: 'root',
})
export class ProveedorService {
  private api = 'http://localhost:8080/proveedor';

  constructor(private http: HttpClient) {}

  listar(): Observable<Proveedor[]> {
    // Si el listado es público en tu SecurityConfig, puede quedarse así. 
    // Si te da 403, agrégale también el withCredentials.
    return this.http.get<Proveedor[]>(`${this.api}/listar`, { withCredentials: true });
  }

  obtener(id: number): Observable<Proveedor> {
    return this.http.get<Proveedor>(`${this.api}/${id}`, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Añadido { withCredentials: true } para que Spring Security no te dé 403
  guardar(proveedor: any): Observable<any> {
    return this.http.post(`${this.api}/agregar`, proveedor, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Añadido { withCredentials: true }
  actualizar(id: number, proveedor: Proveedor): Observable<any> {
    return this.http.put(`${this.api}/${id}`, proveedor, { withCredentials: true });
  }

  // 🔥 CORREGIDO: Añadido { withCredentials: true }
  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.api}/${id}`, { withCredentials: true });
  }
}
