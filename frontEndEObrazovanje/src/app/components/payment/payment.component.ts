import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Payment } from 'src/app/model/payment';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  payments: Payment[] | null = [];

  // subscription: Subscription;


  constructor(private paymentService: PaymentService, private router: Router) {
    // this.subscription = paymentService.RegenerateData$.subscribe(() =>
    //   this.getPayments()
    // );
  }

  ngOnInit(): void {
    // this.getPayments();
  }

  // getPayments(){
  //   this.paymentService.getAccountPayments().subscribe(
  //     response => {
  //       this.payments = response.body;
  //     }
  //   )
  // }
}
