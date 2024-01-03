import { CanActivateChildFn, CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { AdminService } from '../services/admin.service';

export const authGuard: CanActivateFn = (route, state) => {
  return inject(AuthService).authenticate()
    ? true
    : inject(Router).createUrlTree(['/sign-in']);
};

export const authChildGuard: CanActivateChildFn = (route, state) => {
  return inject(AuthService).authenticate()
    ? true
    : inject(Router).createUrlTree(['/sign-in']);
};

export const adminGuard: CanActivateFn = (router, state) => {
  return inject(AdminService).authenticate()
    ? true
    : inject(Router).createUrlTree(['/admin-sign-in']);
};
