import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { Exam } from 'src/app/model/exam';
import { ExamPart } from 'src/app/model/examPart';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExamPartService } from '../exam-detail/exam-detail.service';
import { ExamsService } from '../exams/exams.service';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-student-exam-detail',
  templateUrl: './student-exam-detail.component.html',
  styleUrls: ['./student-exam-detail.component.css']
})
export class StudentExamDetailComponent implements OnInit {

  exam: Exam;
  examParts: ExamPart[] = [];
  role?: string = undefined;
  student:Student;
  editGradle:boolean=false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService:AuthenticationService,
    private examService:ExamsService,
    private examPartService:ExamPartService
    ) 
  {
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
    this.student=new Student({
      id:0,
      cardNumber:'',
      userDTO:new User({
        id:0,
        firstName:'',
        lastName:'',
        userName:'',
        password:'',
        roles:[],
      })
    })
   }

  ngOnInit(): void {
    if(this.route.snapshot.params['cardNumber']){
      this.route.params.pipe(switchMap((params: Params) =>
      this.examPartService.getExamPartsForStudent(+params['courseId'],params['cardNumber'])))
      .subscribe(res =>{
        this.examParts = res.body==null ? this.examParts:res.body;
        this.student = this.examParts[0].examDTO.enrollmentDTO.studentDTO;
        this.exam = this.examParts[0].examDTO;
      }
      );
    }
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

  edit(){
    this.editGradle = true;
  }

  submit(){
    this.examService.editExam(this.exam).subscribe(()=>{
      this.editGradle = false;
    });
  }

  cancel(){
    this.editGradle = false;
  }
}
