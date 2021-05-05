import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public role?: string = undefined;

  title = 'frontEndEObrazovanje';
  collapsed = true;	

  loggedIn = false;

  constructor(private router: Router){}

  checkRole() {
		const item = localStorage.getItem('loggedUser');
		if (!item) {
			this.router.navigate(['login']);
      this.loggedIn = false;
			return;
		}
    this.loggedIn = true;

    const jwt: JwtHelperService = new JwtHelperService();
		this.role = jwt.decodeToken(item).roles[0].authority;
    console.log(this.role)
	}
}
