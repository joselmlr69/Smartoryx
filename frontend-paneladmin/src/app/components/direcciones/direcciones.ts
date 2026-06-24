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
      error: (err) => {
        console.error('Error al listar direcciones:', err);
        const msg = err?.error?.message || err?.statusText || 'No se pudo cargar la lista de direcciones';
        alert(`Error ${err?.status || ''}: ${msg}`);
      }
    });
  }

  eliminar(id: number) {
    if (confirm('¿Eliminar dirección?')) {
      this.direccionService.eliminar(id).subscribe({
        next: () => {
          this.listar();
          this.cd.detectChanges();
        },
        error: (err) => {
          console.error('Error al eliminar dirección:', err);
          const msg = err?.error?.message || err?.statusText || 'No se pudo eliminar la dirección';
          alert(`Error ${err?.status || ''}: ${msg}`);
        }
      });
    }
  }
}
