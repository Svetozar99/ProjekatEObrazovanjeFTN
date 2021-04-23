import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Exam } from 'src/app/model/exam';
import { User } from 'src/app/model/user';
import { ExamsService } from './exams.service'

@Component({
  selector: 'app-exams',
  templateUrl: './exams.component.html',
  styleUrls: ['./exams.component.css']
})
export class ExamsComponent implements OnInit {

  user: User = { id:0, firstName:"", lastName:"", userName:"",password:"", roles:[]};
  exams: Exam[] | null = [];

  subscription: Subscription;

  constructor(private examService:ExamsService, private router: Router, private route: ActivatedRoute) {
    this.subscription = examService.RegenerateData$.subscribe(() =>
    this.getStudentExams());
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
    this.router.navigate(['/exam-detail', exam.id]);
  }
}
