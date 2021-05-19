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

  teacher:Teacher = new Teacher({
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

  numberPages:number[] = [];
  numberPage:number = 0;
  btnAdd:boolean = true;

  coursesIntances: CourseInstance[] | null = [];

  subscription: Subscription;

  constructor(
    private courseService: CoursesService,
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService) { 
    this.subscription = courseService.RegenerateData$.subscribe(() => 
      this.getCoursesInstances()
    );
  }

  getNumberPages(mode:string){
    this.courseService.getNumberPage(mode,this.teacher.userDTO.userName).subscribe(res =>{
      const num = res.body == null ? 0:res.body;
      console.log("Num: "+num)
      var i = 1;
      for (let index = 0; index < num; index++) {
        this.numberPages.push(i);
        i++;
      }
    })
  }

  ngOnInit(): void {
    if (this.route.snapshot.params['id']) {
      this.btnAdd = false;
      this.route.params.pipe(switchMap((params: Params) => 
      this.userService.getTeacher(+params['id']))) // convert to number
    .subscribe(res => {
      this.teacher = res.body==null ? this.teacher:res.body;
      this.getNumberPages('TEACHER');
      this.getCoursesInstances();
      }
    );
    }else{
      this.getNumberPages('ADMIN');
      this.getCoursesInstances();
    }
  }

  getCoursesInstances(){
    this.courseService.getCoursesInstances(this.teacher.userDTO.userName,this.numberPage).subscribe(
      response => {
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

  increaseNumberPage(){
    if(this.numberPage < this.numberPages.length-1){
      this.numberPage=this.numberPage+1;
    }
    this.getCoursesInstances();
  }

  reduceNumberPage(){
    if(this.numberPage>=1){
      this.numberPage=this.numberPage-1;
    }
    this.getCoursesInstances();
  }

  setNumberPage(numberPage:number){
    this.numberPage = numberPage-1;
    this.getCoursesInstances();
  }

  isActive(num:number):boolean{
    if(this.numberPage===num){
      return true;
    }
    return false;
  }

}
