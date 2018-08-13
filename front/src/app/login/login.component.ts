import {Component, NgZone, OnInit} from '@angular/core';
import '../../assets/login-animation.js';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from "@angular/router";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  email: string;
  password: string;
  private error: any;
  private response: Object;

  constructor(private http: HttpClient,private router: Router,private zone:NgZone) {
  }


  ngOnInit(){


  }

  ngAfterViewInit() {
    (window as any).initialize();
  }
  LoadLogin:boolean;


  login() {
    const _headers = new HttpHeaders();
      const headers = _headers.append('Authorization', 'Basic ' + btoa(this.email + ':' + this.password))
     .append('Content-Type', 'application/json')
     .append('Access-Control-Allow-Origin', 'http://localhost:4200');
    this.http
        .get<any>('http://localhost/user/me', {headers: headers}).subscribe(data=> {
      this.response = data;
      if(this.response!=null){
        this.LoadLogin =false;
        this.router.navigate(['inicio'])
      }
    },
        err=>{
          this.error = err;
      if(this.error){
        this.LoadLogin =false;
        this.router.navigate(['/login_error'])
      }



  })}}





