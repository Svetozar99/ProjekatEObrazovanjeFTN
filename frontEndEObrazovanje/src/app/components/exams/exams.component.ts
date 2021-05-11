import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { AppComponent } from 'src/app/app.component';
import { Exam } from 'src/app/model/exam';
import { User } from 'src/app/model/user';
import { ExamsService } from './exams.service'

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent implements OnInit {

  // user: string | null = localStorage.getItem('loggedUser');
  exams: Exam[] | null = [];

  public role?: string = undefined;
  subscription: Subscription;


  constructor(private examService:ExamsService, private router: Router, private route: ActivatedRoute, app: AppComponent) {
    this.subscription = examService.RegenerateData$.subscribe(() =>
    this.getStudentExams());
    this.role = app.role;
  }

  ngOnInit(): void {
        this.examService.getStudentExams()
        .subscribe(res => {
          this.exams = res.body;
          }
        );
  }

  getStudentExams(){
    this.examService.getStudentExams().subscribe(
      response => {
        this.exams = response.body
      });
  }

  gotToViewExam(exam: Exam):void{
    this.router.navigate(['/exam-detail/student/', exam.id]);
  }
}
