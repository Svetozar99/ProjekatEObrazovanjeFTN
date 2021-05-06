import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { ToastrService } from 'ngx-toastr';
import { AppComponent } from 'src/app/app.component';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent implements OnInit {

  @Input() collapsed:boolean;

  constructor(private app:AppComponent, 
              private authenticationService: AuthenticationService,
		          // private toastr: ToastrService,
		          private router: Router
          ) {
    this.collapsed = app.collapsed;
   }

  logout(): void {
    this.router.navigate(['login']);
    this.authenticationService.logout().subscribe(
			result => {
				localStorage.removeItem('loggedUser');
				// this.toastr.success(result);
        this.app.loggedIn = false;
				this.router.navigate(['login']);
			},
			error => {
				// this.toastr.error(error.error);
			}
		);
  }

  ngOnInit(): void {
  }

}
