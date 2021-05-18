import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Exam } from 'src/app/model/exam';
import { ExamPart } from 'src/app/model/examPart';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExamsService } from '../exams/exams.service';
import { ExamPartService } from './exam-detail.service';

@Component({
  selector: 'app-exam-detail',
  templateUrl: './exam-detail.component.html',
  styleUrls: ['./exam-detail.component.css']
})
export class ExamDetailComponent implements OnInit {

  title: String = "";
  exam: Exam;
  examDetails: ExamPart[] = [];
  mode: string = '';
  role?: string = undefined;
  courseId:number = 0;

  constructor(
      private examDetailService: ExamPartService,
      private examService: ExamsService, 
      private route: ActivatedRoute,
      private authenticationService:AuthenticationService,
      private router: Router
    ) { 
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
    this.role = this.authenticationService.getRole();
  }

  ngOnInit() {
    if(this.route.snapshot.params['examId']){
      this.route.params.pipe(switchMap((params: Params) =>
      this.examService.getExam(+params['examId'])))
      .subscribe(res =>{
        this.exam = res.body==null ? this.exam:res.body;
        this.getExamParts(this.exam.enrollmentDTO.courseInstanceDTO.id);
      }
      );
    }else if(this.route.snapshot.params['courseId']){
      this.route.params.pipe(switchMap((params: Params) =>
      this.examDetailService.getExamParts(+params['courseId'],this.role)))
      .subscribe(res =>{
        this.examDetails = res.body == null ? this.examDetails:res.body;
      }
      );
    }else if(this.route.snapshot.params['teacherId']){
      this.examDetailService.getExamPartsForTeacher().subscribe(res =>{
        this.examDetails = res.body == null ? this.examDetails:res.body;
      });
    }
  }

  getExamParts(courseId: number){
    // console.log('getExamParts...');
    this.examDetailService.getExamParts(courseId,this.role).subscribe(
      response => {
        // console.log('Exam details: '+JSON.stringify(response.body));
        this.examDetails = response.body == null ? this.examDetails:response.body;
      }
    );
  }

  dateToString(date:Date):Date{
    // console.log('Date: '+date);
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    // console.log(JSON.stringify(d.getHours()))
    // var dateString = new Date(date).toISOString();
    // var pos=dateString.indexOf('T');
    // var s=dateString.substring(0,pos)+' '+dateString.substring(pos+1,dateString.length-5);
    return d;
  }

  goToExamPart(examPart: ExamPart): void {
    this.router.navigate(['/add-exam-part', examPart.id]);
  }

  isChecked(id:number):string{
    var status = this.examDetails.filter(ed=>ed.id===id)[0].statusDTO.code;
    return status;
  }

  checkValue(examDetail:ExamPart){
    examDetail.statusDTO.code = examDetail.statusDTO.code === 'cr' ? 're':'cr';
    this.examDetailService.registeUnregisterExamPart(examDetail).subscribe();
  }
}
