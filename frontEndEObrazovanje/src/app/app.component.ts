import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontEndEObrazovanje';
  collapsed = true;	

  loggedIn = false;

  constructor(private router: Router){}

  logout(): void {
    localStorage.removeItem('jwt');
    this.loggedIn=false;
  }
}
