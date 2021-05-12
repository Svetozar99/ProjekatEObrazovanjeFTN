import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { Exam } from 'src/app/model/exam';
import { ExamDetail } from 'src/app/model/examDetail';
import { ExamPartStatus } from 'src/app/model/examPartStatus';
import { ExamPartType } from 'src/app/model/examPartType';
import { Student } from 'src/app/model/student';
import { User } from 'src/app/model/user';
import { ExamDetailService } from '../exam-detail/exam-detail.service';

@Component({
  selector: 'app-add-exam-part',
  templateUrl: './add-exam-part.component.html',
  styleUrls: ['./add-exam-part.component.css']
})
export class AddExamPartComponent implements OnInit {

  examDetail:ExamDetail;
  typeCode:string = '';

  constructor(private examDetailService:ExamDetailService,private location: Location) {
    this.examDetail=new ExamDetail(
      {
        id:0,
        date:new Date(),
        location:'',
        points:0,
        wonPoints:0,
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
    )
   }

  ngOnInit(): void {
  }

  add(): void {
    this.examDetail.examPartTypeDTO.code=this.typeCode;
    this.examDetail.examDTO.enrollmentDTO.courseInstanceDTO.id=this.examDetailService.getCourseId();
    console.log('ADD: '+JSON.stringify(this.examDetail));
    this.examDetailService.addExamPart(this.examDetail)
      .subscribe(() => {
        // this.userService.announceChange();
        this.goBack();
      });
  }

  goBack(): void {
    this.location.back();
  }

}
