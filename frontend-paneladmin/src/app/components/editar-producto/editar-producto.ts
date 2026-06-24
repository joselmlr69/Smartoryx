import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ProductoService } from '../../services/producto.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-editar-producto',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './editar-producto.html',
  styleUrls: ['./editar-producto.css']
})
export class EditarProducto implements OnInit {

  id: number = 0;

  producto: any = {
    nombre: '',
    descripcion: '',
    precio: 0,
    stock: 0,
    estado: 1,
    imagenUrl: '',
    idCategoria: null,
    idMarca: null,
    idProveedor: null
  };

  categorias: any[] = [];
  marcas: any[] = [];
  proveedores: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productoService: ProductoService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarDatos();
    this.cargarProducto();
  }

  cargarDatos() {
    this.productoService.listarCategorias().subscribe({
      next: (data) => {
        this.categorias = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error('Error al cargar categorías:', err)
    });

    this.productoService.listarMarcas().subscribe({
      next: (data) => {
        this.marcas = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error('Error al cargar marcas:', err)
    });

    this.productoService.listarProveedores().subscribe({
      next: (data) => {
        this.proveedores = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error('Error al cargar proveedores:', err)
    });
  }

  cargarProducto() {
    this.productoService.obtener(this.id).subscribe({
      next: (prod) => {
        this.producto = {
          nombre: prod.nombre,
          descripcion: prod.descripcion,
          precio: prod.precio,
          stock: prod.stock,
          estado: prod.estado,
          imagenUrl: prod.imagenUrl,
          idCategoria: prod.categoria?.id,
          idMarca: prod.marca?.id,
          idProveedor: prod.proveedor?.id
        };
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error al cargar producto:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo cargar el producto';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }

  actualizar() {
    const data = {
      nombre: this.producto.nombre,
      descripcion: this.producto.descripcion,
      precio: Number(this.producto.precio),
      stock: Number(this.producto.stock),
      estado: this.producto.estado != null ? Number(this.producto.estado) : null,
      imagenUrl: this.producto.imagenUrl || '',
      idCategoria: Number(this.producto.idCategoria),
      idMarca: Number(this.producto.idMarca),
      idProveedor: Number(this.producto.idProveedor)
    };

    this.productoService.actualizar(this.id, data).subscribe({
      next: () => {
        alert('Producto actualizado correctamente');
        this.router.navigateByUrl('/productos');
      },
      error: (err) => {
        console.error('Error al actualizar producto:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo actualizar el producto';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }
}
