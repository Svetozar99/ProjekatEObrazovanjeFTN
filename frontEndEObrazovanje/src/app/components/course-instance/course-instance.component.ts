import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Student } from 'src/app/model/student';
import { CoursesService } from '../courses/courses.service';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-course-instance',
  templateUrl: './course-instance.component.html',
  styleUrls: ['./course-instance.component.css']
})
export class CourseInstanceComponent implements OnInit {

  courseInstance: CourseInstance;
  coursesSpecifications:CourseSpecification[] = [];
  startDate:string='';
  endDate:string='';
  courseSpecificationCode:string = '';
  students:Student[] = [];

  constructor(private courseService:CoursesService, private userService:UserService,private route: ActivatedRoute,private location: Location) {
    this.courseInstance = new CourseInstance({
      id:0,
      startDate:new Date(),
      endDate:new Date(),
      courseSpecificationDTO: new CourseSpecification({id:0,title:'',ects:0,code:''})
    });
   }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => 
        this.courseService.getCourseInstance(+params['id']))) // convert to number
      .subscribe(res => {
        this.courseInstance = res.body==null ? this.courseInstance:res.body;
        this.startDate = new Date(this.courseInstance.startDate).toISOString().substring(0, 10);
        this.endDate = new Date(this.courseInstance.endDate).toISOString().substring(0, 10);
        this.courseSpecificationCode = this.courseInstance.courseSpecificationDTO.code;
        this.userService.getStudents(this.courseInstance).
          subscribe(res =>{
            this.students = [];
            this.students = res.body==null ? []:res.body;
            console.log('Students: '+JSON.stringify(this.students))
            this.courseService.getCoursesSpecifications().
              subscribe(res =>{
                this.coursesSpecifications = [];
                this.coursesSpecifications = res.body==null ? []:res.body;
              });
          });
        }
      );
  }

  edit() {
    if(this.courseSpecificationCode===''){
      alert('--Choose a course specification--')
    }else{
      this.courseInstance.courseSpecificationDTO=this.coursesSpecifications.filter(cs=>cs.code===this.courseSpecificationCode)[0];
      this.courseInstance.startDate=new Date(this.startDate);
      this.courseInstance.endDate=new Date(this.endDate);
      this.courseService.editCourseInstance(this.courseInstance).subscribe();
    }
  }

  clear(){
    this.courseSpecificationCode='';
  }

}
