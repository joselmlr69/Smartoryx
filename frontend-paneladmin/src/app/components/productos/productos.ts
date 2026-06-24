import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../models/producto';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './productos.html',
  styleUrls: ['./productos.css'],
})
export class Productos implements OnInit {
  productos: Producto[] = [];

  paginaActual: number = 0;
  totalPaginas: number = 0;
  totalElementos: number = 0;
  tamanioPagina: number = 20;

  constructor(
    private productoService: ProductoService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.productoService.listarTodos(this.paginaActual, this.tamanioPagina).subscribe({
      next: (data) => {
        this.productos = data.content || [];
        this.totalElementos = data.totalElements || 0;
        this.totalPaginas = data.totalPages || 0;
        this.paginaActual = data.number || 0;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo cargar la lista de productos';
        alert(`Error ${err?.status || ''}: ${msg}`);
      },
    });
  }

  cambiarPagina(pagina: number) {
    if (pagina < 0 || pagina >= this.totalPaginas) return;
    this.paginaActual = pagina;
    this.listar();
  }

  eliminar(id?: number) {
    if (!id) return;

    if (confirm('¿Eliminar producto?')) {
      this.productoService.eliminar(id).subscribe(() => {
        this.listar();
        this.cd.detectChanges();
      });
    }
  }
}
