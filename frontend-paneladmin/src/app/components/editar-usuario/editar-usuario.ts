import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';

import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-editar-usuario',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './editar-usuario.html',
  styleUrls: ['./editar-usuario.css']
})
export class EditarUsuario implements OnInit {

  id: number = 0;

  usuario: any = {
    nombre: '',
    apellido: '',
    correo: '',
    password: '',
    estado: 1,
    rol: {
      id: null
    }
  };

  roles = [
    { id: 1, nombre: 'ADMIN' },
    { id: 2, nombre: 'CLIENTE' }
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarUsuario();
  }

  cargarUsuario() {
    this.usuarioService.obtener(this.id).subscribe({
      next: (data) => {
        this.usuario = data;
        this.cd.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

 actualizar() {
  const usuarioDTO = {
    nombre: this.usuario.nombre,
    apellido: this.usuario.apellido,
    correo: this.usuario.correo,
    password: this.usuario.password || "", // Vacío si no se cambia
    idRol: this.usuario.rol?.id            // El ID plano del rol
  };

  // 🔥 Asegúrate de llamar a .actualizar() y NO a .registrar()
  this.usuarioService.actualizar(this.id, usuarioDTO as any).subscribe({
    next: () => {
      alert('Usuario actualizado correctamente');
      this.router.navigateByUrl('/usuarios');
    },
    error: (err) => {
      console.error('Error en el componente:', err);
      alert('No se pudo actualizar el usuario.');
    }
  });
}
}