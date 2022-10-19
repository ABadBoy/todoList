import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { TodoComponent } from './todo/todo.component';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path:'register',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'todo',component:TodoComponent},
  {path:'app',component:AppComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers:[
    {provide: LocationStrategy, useClass: HashLocationStrategy}
 ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
