import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const adminGuard: CanActivateFn = (route, state) => {

  const authService = inject(AuthService);
  const router = inject(Router);

  const user = authService.getUsuario();

  if (!user) {
    router.navigate(['/login']);
    return false;
  }

  if (user.rol.id !== 1) {
    router.navigate(['/productos']);
    return false;
  }

  return true;
};

