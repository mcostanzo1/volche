import { Component } from '@angular/core';
import '../../assets/login-animation.js';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string;
  password: string;

  constructor(private http: HttpClient,private router: Router) {
  }

  ngAfterViewInit() {
    (window as any).initialize();
  }

  login() {
    console.log(`email: ${this.email} password: ${this.password}`);
    const _headers = new HttpHeaders();
    const headers = _headers.append('Authorization', 'Basic ' + btoa(this.email + ':' + this.password))
   .append('Content-Type', 'application/json')
   .append('Access-Control-Allow-Origin', '*');
    console.log(headers.getAll('Authorization'));
    this.http.get( 'http://localhost/incidencia/mis_incidencias', {headers: headers}).subscribe(data => {
      if(data!= null){
          this.router.navigate(['inicio'])
        }
    });
  }
  }

