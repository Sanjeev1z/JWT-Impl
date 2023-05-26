import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { ErrorComponent } from './error/error.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { authGuard } from './core/auth/auth.guard';

const routes: Routes = [
  { 
    path: 'home',
    component: HomeComponent
  },
  { 
    path: 'admin',
    component: AdminComponent,
    canActivate:[authGuard],
    data:{roles:['Admin']}
  },
  
  {
    path: 'user',
    component: UserComponent, 
    canActivate:[authGuard],
    data:{roles:['User']}
  },
  { 
    path: 'login', 
    component: LoginComponent 
  },  

  {
    path: 'error',
    component: ErrorComponent 
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
