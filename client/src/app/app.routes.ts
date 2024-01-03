import { Routes } from '@angular/router';
import { SignUpComponent } from './sign-up/sign-up.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { BankAccountComponent } from './bank-account/bank-account.component';
import { adminGuard, authChildGuard, authGuard } from './guards/auth.guard';
import { AdminSignInComponent } from './admin-sign-in/admin-sign-in.component';
import { AdminComponent } from './admin/admin.component';

export const routes: Routes = [
  {
    path: '',
    component: SignInComponent,
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard],
  },
  {
    path: 'sign-in',
    component: SignInComponent,
  },
  {
    path: 'admin-sign-in',
    component: AdminSignInComponent,
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [adminGuard],
  },
  {
    path: 'bank-accounts',
    canActivateChild: [authChildGuard],
    children: [
      {
        path: ':id',
        component: BankAccountComponent,
      },
    ],
  },
];
