import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-editar-categoria',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './editar-categorias.html',
  styleUrls: ['./editar-categorias.css']
})
export class EditarCategorias implements OnInit {

  id: number = 0;

  categoria: any = {
    nombre: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categoriaService: CategoriaService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarCategoria();
  }

  cargarCategoria() {
    this.categoriaService.obtener(this.id).subscribe({
      next: (data) => {
        this.categoria = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  actualizar() {
  // Usamos el ID recuperado en el ngOnInit para asegurar que viaje bien al backend
  this.categoriaService.actualizar(this.id, this.categoria).subscribe({
    next: () => {
      alert('Categoría actualizada correctamente');
      this.router.navigateByUrl('/categorias');
    },
    error: (err) => {
      console.error('Error al actualizar:', err);
      alert('No se pudo actualizar la categoría.');
      }
    });
  }

}
