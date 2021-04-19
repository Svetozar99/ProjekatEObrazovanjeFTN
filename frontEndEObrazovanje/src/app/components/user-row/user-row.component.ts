import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-row',
  templateUrl: './user-row.component.html',
  styleUrls: ['./user-row.component.css']
})
export class UserRowComponent implements OnInit {

  @Input()
  user: User;

  constructor() {
    this.user = {firstname:"",lastname:"",username:""}
   }

  ngOnInit() {
  }

}
