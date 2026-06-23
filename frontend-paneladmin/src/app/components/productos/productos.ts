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

  constructor(
    private productoService: ProductoService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.productoService.listar().subscribe({
      next: (data) => {
        this.productos = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
      },
    });
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
