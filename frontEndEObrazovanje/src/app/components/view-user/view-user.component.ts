import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from '../users/users.service';

import {switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  user: User;
  mode: string = '';

  constructor(private userService: UserService, private route: ActivatedRoute) {
    this.user = new User({
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      roles:[],
    });

    this.mode = 'ADD'
  }

  ngOnInit() {
    if (this.route.snapshot.params['id']) {
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
          this.userService.getUser(+params['id']))) // convert to number
        .subscribe(res => {
          this.user = res.body==null ? this.user:res.body;
          }
        );
    } 
  }

}
