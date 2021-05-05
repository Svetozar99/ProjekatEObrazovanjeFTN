import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

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
    localStorage.removeItem('loggedUser');
    this.router.navigate(['login']);
  }

  checkRole() {
    console.log("Checkrole!")
		const item = localStorage.getItem('loggedUser');
    console.log("Logged in: "+this.loggedIn)
		if (!item) {
			this.router.navigate(['login']);
      this.loggedIn = false;
			return;
		}
    this.loggedIn = true;
	}
}
