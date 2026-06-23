import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-registro-proveedor',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './registro-proveedor.html',
  styleUrls: ['./registro-proveedor.css'],
})
export class RegistroProveedor implements OnInit {

  nuevoProveedor: any = {
    nombre: '',
    ruc: '',
    telefono: ''
  };

  constructor(
    private proveedorService: ProveedorService,
    private router: Router,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  guardar() {
    this.proveedorService.guardar(this.nuevoProveedor).subscribe({
      next: () => {
        alert('Proveedor registrado correctamente');
        this.router.navigateByUrl('/proveedores');
      },
      error: (err) => {
        console.error(err);
        alert('Error al registrar proveedor');
      }
    });
  }
}
