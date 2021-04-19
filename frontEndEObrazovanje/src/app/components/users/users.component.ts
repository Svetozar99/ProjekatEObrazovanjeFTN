import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[] = [
    {
      firstname:"Dejan",
      lastname:"Rakin",
      username:"rakin99"
    },
    {
        firstname:"Svetozar",
        lastname:"Brboric",
        username:"brboric99"
    }  
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
