import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Payment } from 'src/app/model/payment';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  payments: Payment[] | null = [];

  subscription: Subscription;

  @Input() student:Student = new Student({
    id:0,
    cardNumber: '',
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })

  constructor(private paymentService: PaymentService, private auths: AuthenticationService ,private router: Router,private studentDetail: StudentDetailComponent) {
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
    }
    this.subscription = paymentService.RegenerateData$.subscribe(() =>
      // this.getPayments()
      this.getStudentPayments());
  }

  ngOnInit(): void {
    this.getStudentPayments();
  }

  getPayments(){
    this.paymentService.getAccountPayments().subscribe(
      response => {
        this.payments = response.body;
      }
    )
  }

  getStudentPayments(){
    this.paymentService.getStudentPayments(this.student.userDTO.userName).subscribe(
      response => {
        this.payments = response.body;
      }
    )
  }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }
}
