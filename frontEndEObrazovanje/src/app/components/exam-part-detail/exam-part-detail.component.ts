import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { Exam } from 'src/app/model/exam';
import { ExamPart } from 'src/app/model/examPart';
import { ExamPartStatus } from 'src/app/model/examPartStatus';
import { ExamPartType } from 'src/app/model/examPartType';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { ExamPartStatusService } from 'src/app/services/exam-part-status.service';
import { ExamPartService } from '../exam-detail/exam-detail.service';

@Component({
  selector: 'app-exam-part-detail',
  templateUrl: './exam-part-detail.component.html',
  styleUrls: ['./exam-part-detail.component.css']
})
export class ExamPartDetailComponent implements OnInit {

  examParts: ExamPart[] = [];
  examPart:ExamPart;
  dateString = "";
  examPartStatuss:ExamPartStatus[] = [];
  edit:boolean = false;

  constructor(
    private route: ActivatedRoute,
    private examDetailService: ExamPartService,
    private examPartStatusService:ExamPartStatusService
  ) {
      this.examPart=new ExamPart(
        {
          id:0,
          date:new Date(),
          location:'',
          points:0,
          wonPoints:0,
          code:'',
          examDTO:new Exam({
            id:0,
            enrollmentDTO:new Enrollment({
              id:0,
              studentDTO:new Student({
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
              }),
              courseInstanceDTO:new CourseInstance({
                id: 0,
                startDate: new Date(),
                endDate: new Date(),
                courseSpecificationDTO:new CourseSpecification({
                  id:0,
                  title:'',
                  ects:0,
                  code:'',
                })
              })
            }),
            gradle:0,
            points:0,
          }),
          examPartTypeDTO:new ExamPartType({
            id:0,
            name:'',
            code:'',
          }),
          statusDTO:new ExamPartStatus({
            id:0,
            name:'',
            code:'',
          })
        }
      );
      this.examPartStatusService.getExamPartStatus().subscribe(res=>{
        this.examPartStatuss = res.body == null ? this.examPartStatuss:res.body;
      });
   }

  ngOnInit(): void {
    this.getExamPart();
  }

  change(examPart:ExamPart,event:Event){
    this.edit = true;
    const target= event.target as HTMLInputElement;
    if(target.type==='select-one'){
      examPart.statusDTO.code = target.value as string;
    }else if(target.type==='text'){
      examPart.wonPoints = target.value as unknown as number;
    }
  }

  submit(){
    var r:boolean=false;
    // if(this.edit){
    //   r = confirm("Going to another page will lose the previously entered data. \nSave previously entered data?");
    // }
    this.examDetailService.evaluationExamParts(this.examParts)
    .subscribe(res=>{
      console.log(JSON.stringify(res.body));
    });
  }

  getExamPart(){
    this.route.params.pipe(switchMap((params: Params) =>
    this.examDetailService.getExamPartByCode(params['code'])))
    .subscribe(res =>{
      this.examPart = res.body==null ? this.examPart:res.body;
      this.dateString = new Date(this.examPart.date).toISOString().substring(0, 16);
      this.examDetailService.getExamPartsByCode(this.examPart.code)
      .subscribe(res =>{
        this.examParts = res.body==null ? this.examParts:res.body;
      });
    });
  }
}
