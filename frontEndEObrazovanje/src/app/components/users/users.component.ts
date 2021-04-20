import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { Subscription } from 'rxjs';
import { UserService } from './users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] | null = [];

  subscription: Subscription;

  constructor(private userService: UserService, private router: Router) { 
    this. subscription = userService.RegenerateData$.subscribe(() => 
      this.getUsers()
    );
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.userService.getUsers().subscribe(
      response => this.users = response.body);
  }

}
