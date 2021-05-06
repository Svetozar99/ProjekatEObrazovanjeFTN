import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) {
    this.user = new User({
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[],
    });
   }

  ngOnInit(): void {
    var storage = localStorage.getItem('loggedUser');
    var user = JSON.parse(storage==null?'':storage)
    console.log("User: "+user.username);
    this.userService.getLoggedUser().subscribe(res => {
      this.user = res.body==null ? this.user:res.body;
      });
  }

}
