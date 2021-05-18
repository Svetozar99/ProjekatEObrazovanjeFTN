import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';
import { Student } from 'src/app/model/student';
import { Teacher } from 'src/app/model/teacher';
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
  studentCardNumber:string = '';
  otherStudents:Student[] = [];
  editInstance:boolean=false;
  enrollment:Enrollment;
  teachers: Teacher[] = [];
  teacherUsername:string = '';

  constructor(private courseService:CoursesService, private userService:UserService,private route: ActivatedRoute,private router: Router) {
    this.courseInstance = new CourseInstance({
      id:0,
      startDate:new Date(),
      endDate:new Date(),
      courseSpecificationDTO: new CourseSpecification({id:0,title:'',ects:0,code:''})
    });
    this.courseService.getCoursesSpecifications().subscribe(res =>
      {
        this.coursesSpecifications = res.body==null ? []:res.body;
      });
    this.userService.getTeachers().subscribe(res =>
      {
        this.teachers = res.body==null ? []:res.body;
      });
    this.enrollment=new Enrollment(
      {
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
        courseInstanceDTO:this.courseInstance
    });
   }

  ngOnInit(): void {
    this.route.params.pipe(switchMap((params: Params) => 
        this.courseService.getCourseInstance(+params['id']))) // convert to number
      .subscribe(res => {
        this.courseInstance = res.body==null ? this.courseInstance:res.body;
        // console.log('courseInstance: '+JSON.stringify(this.courseInstance));
        this.startDate = new Date(this.courseInstance.startDate).toISOString().substring(0, 10);
        this.endDate = new Date(this.courseInstance.endDate).toISOString().substring(0, 10);
        this.courseSpecificationCode = this.courseInstance.courseSpecificationDTO.code;
        this.getStudents(this.courseInstance);
        }
      );
  }

  submit() {
    if(this.courseSpecificationCode===''){
      alert('--Choose a course specification--')
    }else{
      this.editInstance = false;
      this.courseInstance.courseSpecificationDTO=this.coursesSpecifications.filter(cs=>cs.code===this.courseSpecificationCode)[0];
      this.courseInstance.startDate=new Date(this.startDate);
      this.courseInstance.endDate=new Date(this.endDate);
      this.courseService.editCourseInstance(this.courseInstance).subscribe();
    }
  }

  cancel(){
    this.editInstance = false;
    this.courseSpecificationCode=this.courseInstance.courseSpecificationDTO.code;
  }

  edit(){
    this.editInstance = true;
    this.courseSpecificationCode='';
  }

  addStudent(){
    // console.log("Student cardNubmer: "+this.studentCardNumber);
    this.enrollment.studentDTO = this.otherStudents.filter(s=>this.studentCardNumber===s.cardNumber)[0];
    this.enrollment.courseInstanceDTO = this.courseInstance;
    this.courseService.addEnrollment(this.enrollment).subscribe(() => this.getStudents(this.courseInstance));
  }

  removeStudent(student:Student){
    var enrollment = new Enrollment({id:0,studentDTO:student,courseInstanceDTO:this.courseInstance});
    this.courseService.deleteEnrollment(enrollment).subscribe(()=>this.getStudents(this.courseInstance));
  }

  getStudents(courseInstance:CourseInstance){
    this.userService.getCourseInstanceStudents(courseInstance).
      subscribe(res =>{
        this.students = [];
        this.students = res.body==null ? []:res.body;
        this.userService.getCourseInstanceOtherStudents(courseInstance).
          subscribe(res =>{
            this.otherStudents = [];
            this.otherStudents = res.body==null ? []:res.body;
            // console.log('Other students: '+JSON.stringify(this.students));
          });
      });
  }

  gotToExamParts():void{
    this.router.navigate(['course-instance/exam-parts/', this.courseInstance.id]);
  }

  goToStudentDetail(cardNubmer:string):void{
    console.log("CardNumber: "+cardNubmer);
    console.log("Course id: "+this.courseInstance.id)
    this.router.navigate(['student-exam-detail/', this.courseInstance.id,cardNubmer]);
  }

}