import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { ProveedorService } from '../../services/proveedor.service';
import { Proveedor } from '../../models/proveedor';

@Component({
  selector: 'app-proveedores',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './proveedores.html',
  styleUrls: ['./proveedores.css']
})
export class Proveedores implements OnInit {

  proveedores: Proveedor[] = [];

  constructor(
    private proveedorService: ProveedorService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.proveedorService.listar().subscribe({
      next: (data) => {
        this.proveedores = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error al listar proveedores:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo cargar la lista de proveedores';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }
}