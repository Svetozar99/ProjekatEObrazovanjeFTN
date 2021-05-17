import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Account } from 'src/app/model/accounts';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-student-navbar',
  templateUrl: './student-navbar.component.html',
  styleUrls: ['./student-navbar.component.css']
})
export class StudentNavbarComponent implements OnInit {

  @Input() collapsed:boolean;

  constructor(private app:AppComponent, 
              private authenticationService: AuthenticationService,
              // private toastr: ToastrService,
              private router: Router) 
      {
        this.collapsed = app.collapsed;
      }

  logout(): void {
    this.router.navigate(['login']);
    localStorage.removeItem('loggedUser');
    this.authenticationService.logout().subscribe(
      result => {
          console.log('Uspesni logout!');
        // localStorage.removeItem('loggedUser');
        // this.toastr.success(result);
      },
      error => {
        console.log('Doslo je do greske!')
        // this.toastr.error(error.error);
      }
    );
  }

  ngOnInit(): void {
  }

}
