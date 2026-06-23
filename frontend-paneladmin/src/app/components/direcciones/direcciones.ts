import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { DireccionService } from '../../services/direccion.service';
import { Direccion } from '../../models/direccion';

@Component({
  selector: 'app-direcciones',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './direcciones.html',
  styleUrls: ['./direcciones.css']
})
export class Direcciones implements OnInit {

  direcciones: Direccion[] = [];

  constructor(
    private direccionService: DireccionService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.direccionService.listar().subscribe({
      next: (data) => {
        this.direcciones = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  eliminar(id: number) {
    if (confirm('¿Eliminar dirección?')) {
      this.direccionService.eliminar(id).subscribe(() => {
        this.listar();
      });
    }
  }
}
