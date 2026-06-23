import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

import { DireccionService } from '../../services/direccion.service';

@Component({
  selector: 'app-editar-direccion',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './editar-direccion.html',
  styleUrls: ['./editar-direccion.css']
})
export class EditarDireccion implements OnInit {

  id: number = 0;

  direccion: any = {
    direccion: '',
    ciudad: '',
    referencia: ''
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private direccionService: DireccionService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarDireccion();
  }

  cargarDireccion() {
    this.direccionService.obtener(this.id).subscribe({
      next: (data) => {
        this.direccion = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  actualizar() {
    this.direccionService.actualizar(this.id, this.direccion).subscribe(() => {
      alert('Dirección actualizada correctamente');
      this.router.navigateByUrl('/direcciones');
    });
  }
}