///<reference path="../../../node_modules/@types/selenium-webdriver/http.d.ts"/>
import { Component } from '@angular/core';
import '../../assets/login-animation.js';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {headersToString} from 'selenium-webdriver/http';

@Component({
  selector: 'app-root',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class AppComponent {
  email: string;
  password: string;

  constructor(private http: HttpClient) {
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
      console.log(data);
    });
  }
  }

