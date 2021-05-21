import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Student } from 'src/app/model/student';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StudentDetailComponent } from '../student-detail/student-detail.component';
import { CourseInstanceForStudentService } from './courses-for-student.service';

@Component({
  selector: 'app-courses-for-student',
  templateUrl: './courses-for-student.component.html',
  styleUrls: ['./courses-for-student.component.css']
})
export class CoursesForStudentComponent implements OnInit {

  courseInstances: CourseInstance[] | null = [];

  subscription: Subscription;

  @Input() student:Student = new Student({
    id:0,
    cardNumber: '',
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })

  constructor(private coursesService: CourseInstanceForStudentService, private auths: AuthenticationService ,private router: Router,private studentDetail: StudentDetailComponent) { 
    if(auths.getRole() === 'ROLE_ADMINISTRATOR'){
      this.student = studentDetail.student;
    }
    this.subscription = coursesService.RegenerateData$.subscribe(() =>
      // this.getPayments()
      this.getStudentCourses());
  }

  ngOnInit(): void {
    this.getStudentCourses();
  }

  getStudentCourses(){
    console.log('pogodjena ocekivana metoda');
    this.coursesService.getStudentCourses(this.student.userDTO.userName).subscribe(
      response => {
        this.courseInstances = response.body;
      }
    )
  }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }

  goToCourseInstance(courseInstance: CourseInstance): void {
    this.router.navigate(['/course-instance', courseInstance.id]);
  }
}
