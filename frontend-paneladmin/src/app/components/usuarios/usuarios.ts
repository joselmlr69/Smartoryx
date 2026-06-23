import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario';

@Component({
  selector: 'app-usuarios',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './usuarios.html',
  styleUrls: ['./usuarios.css'],
})
export class Usuarios implements OnInit {

  usuarios: Usuario[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listar();
  }

  listar() {
    this.usuarioService.listar().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  eliminar(id?: number) {
    if (!id) return;

    if (confirm('¿Eliminar usuario?')) {
      this.usuarioService.eliminar(id).subscribe(() => {
        this.listar();
        this.cd.detectChanges();
      });
    }
  }
}
