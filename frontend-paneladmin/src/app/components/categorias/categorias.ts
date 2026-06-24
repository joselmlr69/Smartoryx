import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../models/categoria';

@Component({
  selector: 'app-categorias',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './categorias.html',
  styleUrls: ['./categorias.css'],
})
export class Categorias implements OnInit {

  categorias: Categoria[] = [];

  constructor(
    private categoriaService: CategoriaService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.categoriaService.listar().subscribe({
      next: (data) => {
        this.categorias = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}