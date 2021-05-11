import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Exam } from 'src/app/model/exam';
import { ExamDetail } from 'src/app/model/examDetail';
import { ExamsService } from '../exams/exams.service';
import { ExamDetailService } from './exam-detail.service';

@Component({
  selector: 'app-exam-detail',
  templateUrl: './exam-detail.component.html',
  styleUrls: ['./exam-detail.component.css']
})
export class ExamDetailComponent implements OnInit {

  title: String = "";
  exam: Exam;
  examDetails: ExamDetail[] | null = [];
  mode: string = '';

  // subscription: Subscription;


  constructor(private examDetailService: ExamDetailService,private examService: ExamsService, private route: ActivatedRoute) { 
    this.exam = {
      id:0,
      enrollmentDTO:{
        id:0,
        studentDTO:{
          id:0,
          cardNumber:'',
          userDTO:{
            id:0,
            firstName:'',
            lastName:'',
            userName:'',
            password:'',
            roles:[]
          }
        },
        courseInstanceDTO:{
          id:0,
          startDate:new Date(),
          endDate:new Date(),
          courseSpecificationDTO:{
            id:0,
            title:'',
            ects:0,
            code:''
          }
        },
      },
      gradle:0,
      points:0
    }
    this.mode = 'ADD'
  }

  ngOnInit() {
    if(this.route.snapshot.params['examId']){
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) =>
      this.examService.getExam(+params['examId'])))
      .subscribe(res =>{
        this.exam = res.body==null ? this.exam:res.body;
        console.log(JSON.stringify(this.exam));
        this.examDetailService.getExamParts(this.exam.enrollmentDTO.courseInstanceDTO.id)
        .subscribe(res => {
          this.examDetails = res.body==null ? this.examDetails:res.body;
          
        });
      }
      );
        
    }
  }

  getExamParts(courseId: number){
    console.log('getExamParts...');
    this.examDetailService.getExamParts(courseId).subscribe(
      response => {
        this.examDetails = response.body
      }
    );
  }

}
