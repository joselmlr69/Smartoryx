import { Component, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-registrar-categoria',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './registrar-categoria.html',
  styleUrls: ['./registrar-categoria.css']
})
export class RegistrarCategoria {

  categoria: any = {
    nombre: ''
  };

  constructor(
    private categoriaService: CategoriaService,
    private router: Router,
    private cd: ChangeDetectorRef
  ) {}

  guardar() {
    if (!this.categoria.nombre) {
      alert('El nombre es obligatorio');
      return;
    }

    this.categoriaService.registrar(this.categoria).subscribe({
      next: () => {
        alert('Categoría registrada correctamente');

        this.cd.detectChanges();

        this.router.navigateByUrl('/categorias');
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}