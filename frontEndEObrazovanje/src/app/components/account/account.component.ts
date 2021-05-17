import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Account } from 'src/app/model/accounts';
import { Student } from 'src/app/model/student';
import { PaymentService } from '../payment/payment.service';
import { UserService } from '../users/users.service';
import { AccountService } from './account.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  account: Account;

  constructor(private accountService:AccountService, private paymentService:PaymentService, private route: ActivatedRoute,private router: Router) {
    this.account = new Account({
      id:0,
      amount:0,
      studentDTO:{
        id:0,
        cardNumber:"",
        userDTO:{
          id:0,
          firstName:"",
          lastName:"",
          userName:"",
          password:"",
          roles:[]
        }
      },
      payments:[]
    });
  }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params:Params) =>
      this.accountService.getAccount(+params['id'])))
      .subscribe(res => {
        this.account = res.body==null ? this.account:res.body;
        this.paymentService.getAccountPayments().subscribe( res => 
          {
            this.account.payments = res.body==null ? this.account.payments:res.body;
          });
      })
  }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }
  
}
