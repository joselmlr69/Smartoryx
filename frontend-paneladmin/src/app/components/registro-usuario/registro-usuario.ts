import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-registro-usuario',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './registro-usuario.html',
  styleUrls: ['./registro-usuario.css'],
})
export class RegistroUsuario implements OnInit {

  nuevoUsuario: any = {
    nombre: '',
    apellido: '',
    correo: '',
    password: '',
    estado: 1,
    rol: {
      id_rol: 2
    }
  };

  roles = [
    { id: 1, descripcion: 'ADMIN' },
    { id: 2, descripcion: 'CLIENTE' }
  ];

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  guardar() {
  const bodyRequest = {
    nombre: this.nuevoUsuario.nombre,
    apellido: this.nuevoUsuario.apellido,
    correo: this.nuevoUsuario.correo,
    password: this.nuevoUsuario.password, // Asegúrate de escribir una clave válida en el input
    idRol: Number(this.nuevoUsuario.rol?.id) // Convertir a número plano para cumplir con el @NotNull de Java
  };

  this.usuarioService.registrar(bodyRequest as any).subscribe({
    next: () => {
      alert('Usuario registrado con éxito 🎉');
      this.router.navigateByUrl('/usuarios');
    },
    error: (err) => {
      console.error(err);
      alert('Error 400: Verifica que la contraseña sea mayor a 3 caracteres y que el Rol esté seleccionado.');
    }
  });
}
}