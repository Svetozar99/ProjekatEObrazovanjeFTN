import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Teacher } from 'src/app/model/teacher';
import { UserService } from '../users/users.service';

@Component({
  selector: 'app-teachers',
  templateUrl: './teachers.component.html',
  styleUrls: ['./teachers.component.css']
})
export class TeachersComponent implements OnInit {

  teachers:Teacher[] | null = [];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.getTeachers();
  }

  getTeachers(){
    this.userService.getTeachers().subscribe(res=>{
      this.teachers = res.body;
    });
  }

  goToTeacher(teacher:Teacher){
    this.router.navigate(['/teacher', teacher.id]);
  }

}
