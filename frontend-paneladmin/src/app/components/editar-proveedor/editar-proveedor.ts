import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-editar-proveedor',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './editar-proveedor.html',
  styleUrls: ['./editar-proveedor.css']
})
export class EditarProveedor implements OnInit {

  id: number = 0;

  proveedor: any = {
    nombre: '',
    ruc: '',
    telefono: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private proveedorService: ProveedorService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarProveedor();
  }

  cargarProveedor() {
    this.proveedorService.obtener(this.id).subscribe({
      next: (data) => {
        this.proveedor = {
          nombre: data.nombre,
          ruc: data.ruc,
          telefono: data.telefono
        };
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar proveedor:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo cargar el proveedor';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }

  actualizar() {
    const data = {
      nombre: this.proveedor.nombre,
      ruc: this.proveedor.ruc,
      telefono: this.proveedor.telefono
    };

    this.proveedorService.actualizar(this.id, data).subscribe({
      next: () => {
        alert('Proveedor actualizado correctamente');
        this.router.navigateByUrl('/proveedores');
      },
      error: (err) => {
        console.error('Error al actualizar proveedor:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo actualizar el proveedor';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }
}