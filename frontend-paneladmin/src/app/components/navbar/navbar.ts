import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {

  constructor(
    public authService: AuthService,
    private router: Router
  ) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  getNombreCompleto(): string {
    const user = this.authService.getUsuario();
    return user ? `${user.nombre} ${user.apellido}` : '';
  }

  getIniciales(): string {
    const user = this.authService.getUsuario();
    if (!user) return 'AD';
    const nombre = user.nombre?.charAt(0) || '';
    const apellido = user.apellido?.charAt(0) || '';
    return (nombre + apellido).toUpperCase() || 'AD';
  }
}