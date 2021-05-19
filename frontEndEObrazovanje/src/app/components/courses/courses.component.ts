import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Teacher } from 'src/app/model/teacher';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { TeacherComponent } from '../teacher/teacher.component';
import { UserService } from '../users/users.service';
import { CoursesService } from './courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  @Input() teacher:Teacher = new Teacher({
    id:0,
    userDTO:{
      id:0,
      firstName:'',
      lastName:'',
      userName:'',
      password:'',
      roles:[]
    }
  })

  coursesIntances: CourseInstance[] | null = [];

  subscription: Subscription;

  constructor(
    private courseService: CoursesService,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService,
    private teacherC:TeacherComponent) { 
    console.log("Teacher: "+this.teacher.userDTO.firstName)
    this.subscription = courseService.RegenerateData$.subscribe(() => 
      this.getCoursesInstances()
    );
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.route.params.pipe(switchMap((params: Params) => 
      this.userService.getTeacher(+params['id']))) // convert to number
    .subscribe(res => {
      this.teacher = res.body==null ? this.teacher:res.body;
      this.getCoursesInstances();
      }
    );
    }else{
      this.getCoursesInstances();
    }
  }

  getCoursesInstances(){
    this.courseService.getCoursesInstances(this.teacher.userDTO.userName).subscribe(
      response => {
        console.log(response.body)
        this.coursesIntances = response.body
      });
  }

  deleteCourseInstance(courseInstance: CourseInstance): void {
    console.log("Brisem: "+JSON.stringify(courseInstance));
    this.courseService.deleteCourseInstance(courseInstance.id==undefined ? 0:courseInstance.id).subscribe(
      () => this.getCoursesInstances()
    );
  }

  goToCourseInstance(courseInstance: CourseInstance): void {
    this.router.navigate(['/course-instance', courseInstance.id]);
  }

  dateToString(date:Date):Date{
    var d = new Date(date);
    d.setHours(d.getHours()-1);
    return d;
  }

}
