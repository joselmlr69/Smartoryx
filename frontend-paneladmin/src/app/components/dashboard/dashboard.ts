import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ProductoService } from '../../services/producto.service';
import { UsuarioService } from '../../services/usuario.service';
import { CategoriaService } from '../../services/categoria.service';
import { ProveedorService } from '../../services/proveedor.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class Dashboard implements OnInit {
  
  totalProductos = 0;
  totalUsuarios = 0;
  totalCategorias = 0;
  totalProveedores = 0;
  productosStockBajo: any[] = [];
  productosPorMarca: { marca: string; cantidad: number; porcentaje: number }[] = [];
  ultimosProductos: any[] = [];

  constructor(
    private productoService: ProductoService,
    private usuarioService: UsuarioService,
    private categoriaService: CategoriaService,
    private proveedorService: ProveedorService
  ) {}

  ngOnInit() {
    this.cargarEstadisticas();
  }

  cargarEstadisticas() {
    this.productoService.listar().subscribe({
      next: (productos) => {
        this.totalProductos = productos.length;
        this.productosStockBajo = productos.filter(p => p.stock < 10);
        this.ultimosProductos = productos.slice(-5).reverse();
        
        const marcasMap = new Map<string, number>();
        productos.forEach(p => {
          const marca = p.marca?.nombre || 'Sin marca';
          marcasMap.set(marca, (marcasMap.get(marca) || 0) + 1);
        });
        
        this.productosPorMarca = Array.from(marcasMap.entries()).map(([marca, cantidad]) => ({
          marca,
          cantidad,
          porcentaje: (cantidad / productos.length) * 100
        })).sort((a, b) => b.cantidad - a.cantidad);
      }
    });

    this.usuarioService.listar().subscribe({
      next: (usuarios) => {
        this.totalUsuarios = usuarios.length;
      }
    });

    this.categoriaService.listar().subscribe({
      next: (categorias) => {
        this.totalCategorias = categorias.length;
      }
    });

    this.proveedorService.listar().subscribe({
      next: (proveedores) => {
        this.totalProveedores = proveedores.length;
      }
    });
  }
}
