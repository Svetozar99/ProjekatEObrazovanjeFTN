import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { Subscription } from 'rxjs';
import { UserService } from './users.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] | null = [];

  subscription: Subscription;

  constructor(private userService: UserService, private router: Router) { 
    this.subscription = userService.RegenerateData$.subscribe(() => 
      this.getUsers()
    );
  }

  ngOnInit(): void {
    this.getUsers();
  }

  getUsers(){
    this.userService.getUsers().subscribe(
      response => {
        // console.log(response)
        this.users = response.body
      });
  }

  deleteUser(user: User): void {
    console.log("Brisem: "+JSON.stringify(user));
    this.userService.deleteUser(user.id==undefined ? 0:user.id).subscribe(
      () => this.getUsers()
    );
  }

  goToViewUser(user: User): void {
    this.router.navigate(['/view-user', user.id]);
  }

}
