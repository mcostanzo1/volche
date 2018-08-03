import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import {RouterModule, Routes} from "@angular/router";
import { InicioComponent } from './inicio/inicio.component';
import { LoginerrorComponent } from './loginerror/loginerror.component';


const appRoutes: Routes = [
  { path: 'home',
    component: HomeComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
      ]
  },
  {
    path: 'inicio',
    component: InicioComponent
  },
  {
    path: 'login_error',
    component: LoginerrorComponent
  }]
;


@NgModule({
  declarations: [
    LoginComponent,
    HomeComponent,
    InicioComponent,
    LoginerrorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  exports:[RouterModule],
  providers: [],
  bootstrap: [HomeComponent]
})
export class AppModule { }
