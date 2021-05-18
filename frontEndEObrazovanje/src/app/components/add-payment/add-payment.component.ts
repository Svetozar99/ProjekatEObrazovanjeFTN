import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Account } from 'src/app/model/accounts';
import { Payment } from 'src/app/model/payment';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { PaymentService } from '../payment/payment.service';

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {

  payment: Payment;

  constructor(
    private paymentService:PaymentService,
    private location: Location,
    private route: ActivatedRoute) {
      this.payment = new Payment(
        {
          id:0,
          currency:'',
          amount:0,
          date:new Date(),
          urgently:false,
          note:'',
          accountDTO:new Account({
            id:0,
            amount:0,
            studentDTO:new Student({
              id:0,
              cardNumber:"",
              userDTO:new User({
                id:0,
                firstName:"",
                lastName:"",
                userName:"",
                password:"",
                roles:[],
              })
            }),
            payments:[]
          })
        }
      )
      
    }

  ngOnInit(): void {
  }

  addPayment(){
    this.paymentService.addAccountPayment(this.payment).subscribe(() => {this.goBack() });
  }

  goBack(): void {
    this.location.back();
  }
}
