import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

import { ProductoService } from '../../services/producto.service';

@Component({
  selector: 'app-registro-producto',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './registro-producto.html',
  styleUrls: ['./registro-producto.css'],
})
export class RegistroProducto implements OnInit {
  nuevoProducto: any = {
    nombre: '',
    descripcion: '',
    precio: 0,
    stock: 0,
    estado: 1,
    imagenUrl: '',
    idCategoria: 1,
    idMarca: 1,
    idProveedor: 1,
  };

  categorias: any[] = [];
  marcas: any[] = [];
  proveedores: any[] = [];

  constructor(
    private productoService: ProductoService,
    private router: Router,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.cargarDatos();
  }

  cargarDatos() {
    this.productoService.listarCategorias().subscribe((data) => {
      this.categorias = data;
      this.cd.detectChanges();
    });

    this.productoService.listarMarcas().subscribe((data) => {
      this.marcas = data;
      this.cd.detectChanges();
    });

    this.productoService.listarProveedores().subscribe((data) => {
      this.proveedores = data;
      this.cd.detectChanges();
    });
  }

  guardar() {
    this.productoService.guardar(this.nuevoProducto).subscribe({
      next: () => {
        alert('Guardado correctamente');
        this.router.navigateByUrl('/productos');
      },
      error: (err) => {
        console.error('Error al guardar producto:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo guardar el producto';
        alert(`Error ${err?.status || ''}: ${msg}`);
      },
    });
  }
}
