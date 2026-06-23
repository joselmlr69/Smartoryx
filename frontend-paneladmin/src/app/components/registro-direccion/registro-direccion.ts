import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

import { DireccionService } from '../../services/direccion.service';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-registro-direccion',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './registro-direccion.html',
  styleUrls: ['./registro-direccion.css'],
})
export class RegistroDireccion implements OnInit {

  nuevaDireccion: any = {
    direccion: '',
    ciudad: '',
    referencia: '',
  };

  usuarios: any[] = [];

  id_usuario: number = 0;

  constructor(
    private direccionService: DireccionService,
    private usuarioService: UsuarioService,
    private router: Router,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.listar().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  guardar() {
    this.direccionService.guardar(this.id_usuario, this.nuevaDireccion).subscribe({
      next: () => {
        alert('Dirección registrada correctamente');
        this.router.navigateByUrl('/direcciones');
      },
      error: () => {
        alert('Error al registrar dirección');
      }
    });
  }
}