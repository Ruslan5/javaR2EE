import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {CreateUserComponent} from "./create-user/create-user.component";
import {UpdateUserComponent} from "./update-user/update-user.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./_auth/auth.guard";
import {InfoComponent} from "./info/info.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {RegistrationComponent} from "./registration/registration.component";

const routes: Routes = [
  {path: 'user', component: UsersListComponent, canActivate:[AuthGuard], data:{roles:['admin']}},
  {path: 'add-user', component: CreateUserComponent, canActivate:[AuthGuard], data:{roles:['admin']}},
  {path:'', redirectTo: 'users', pathMatch: 'full'},
  {path: 'update-user/:login', component: UpdateUserComponent, canActivate:[AuthGuard], data:{roles:['admin']}},
  {path: 'login', component: LoginComponent},
  {path: 'info', component: InfoComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'register', component: RegistrationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
