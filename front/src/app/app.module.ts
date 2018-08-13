import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { InicioComponent } from './inicio/inicio.component';
import { LoginerrorComponent } from './loginerror/loginerror.component';
import {AppRoutingModule} from "./app-routing.module";




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
    AppRoutingModule],

  providers: [],
  bootstrap: [HomeComponent]
})
export class AppModule { }
