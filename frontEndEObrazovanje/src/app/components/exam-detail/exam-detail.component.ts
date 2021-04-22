import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Exam } from 'src/app/model/exam';
import { ExamsService } from '../exams/exams.service';

@Component({
  selector: 'app-exam-detail',
  templateUrl: './exam-detail.component.html',
  styleUrls: ['./exam-detail.component.css']
})
export class ExamDetailComponent implements OnInit {

  exam: Exam;
  mode: string = '';

  constructor(private examService: ExamsService, private route: ActivatedRoute) { 
    this.exam = new Exam({
      id:0,
      enrollmentDTO:{
        id:0,
        studentDTO:'',
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
    });
    this.mode = 'ADD'
  }

  ngOnInit() {
    if(this.route.snapshot.params['id']){
      this.mode = 'EDIT';
      this.route.params.pipe(switchMap((params: Params) => 
        this.examService.getExam(+params['id'])))
        .subscribe(res => {
          this.exam = res.body==null ? this.exam:res.body;
        });
    }
  }

}
