import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  correo = '';
  password = '';
  errorMsg = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ingresar() {
    this.errorMsg = '';
    this.authService.login(this.correo, this.password)
      .subscribe({
        next: (user) => {
          this.authService.setUsuario(user);
          this.router.navigate(['/dashboard']);
        },
        error: (err) => {
          console.error('Error login:', err);
          this.errorMsg = 'Credenciales incorrectas. Verifica tu correo y contraseña.';
        }
      });
  }
}