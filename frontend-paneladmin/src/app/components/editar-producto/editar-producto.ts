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
    estado:1
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productoService: ProductoService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarProducto();
  }

  cargarProducto() {
    this.productoService.listar().subscribe(data => {

      const prod = data.find(p => p.id_producto == this.id);

      if (prod) {
        this.producto = {
          nombre: prod.nombre,
          descripcion: prod.descripcion,
          precio: prod.precio,
          stock: prod.stock
        };

        this.cd.detectChanges();

      }
    });
  }

  actualizar() {

    /*const data = {
      nombre: this.producto.nombre,
      descripcion: this.producto.descripcion,
      precio: this.producto.precio,
      stock: this.producto.stock
    };*/

    this.productoService.actualizar(this.id, this.producto).subscribe(() => {
      alert('Producto actualizado correctamente');
      this.router.navigateByUrl('/productos');
    });
  }
}
