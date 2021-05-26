import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { JWT } from 'src/app/model/jwt';
import { LoginData } from 'src/app/model/loginData';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData: LoginData;
  jwt: JWT | null={value:''};

  constructor(private userService: UserService,private app:AppComponent,private router: Router) {
    this.loginData = new LoginData('milosevoic123','admin');
  }

  ngOnInit(): void {
  }

  loginn(): void {
    this.userService.login(this.loginData)
      .subscribe(res => {
        this.jwt=res.body==null ? {value:''}:res.body;
        localStorage.setItem('loggedUser', JSON.stringify({
            username: this.loginData.username,
            roles: this.getRoles(this.jwt.value),
            token: this.jwt
          }));
        this.router.navigate(['/home']);
      });
  }

  getRoles(token: string) {
    let jwtData = token.split('.')[1];
    let decodedJwtJsonData = window.atob(jwtData);
    let decodedJwtData = JSON.parse(decodedJwtJsonData);
    console.log(JSON.stringify(decodedJwtData))

    return [decodedJwtData.role];
  }


}
