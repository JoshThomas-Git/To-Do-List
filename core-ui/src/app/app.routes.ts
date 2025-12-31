import { RouterModule,Routes } from '@angular/router';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },

  {
    path: 'login',
    loadComponent: () =>
      import('./modules/login-register/login-register').then(m => m.LoginRegister),
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./modules/navbar/navbar').then(m => m.Navbar),
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      {
        path: 'home',
        loadComponent: () =>
          import('./modules/homepage/homepage').then(m => m.Homepage),
      },
    ],
  },
];
