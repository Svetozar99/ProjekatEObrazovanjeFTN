import { Component, OnInit } from '@angular/core';
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

  constructor(private userService: UserService) {
    this.loginData = new LoginData('rakin99','student1');
  }

  ngOnInit(): void {
  }

  login(): void {
    this.userService.login(this.loginData)
      .subscribe(res => {
        console.log("--Login--")
        this.jwt=res.body==null ? {value:''}:res.body;
        localStorage.setItem('jwt', this.jwt.value);
        // this.userService.announceChange();
      });
  }

}
