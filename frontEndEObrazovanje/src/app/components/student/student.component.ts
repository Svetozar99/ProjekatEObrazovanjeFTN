import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Student } from 'src/app/model/student';
import { StudentService } from './student.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  students: Student[] | null =[];

  subscription: Subscription;

  constructor(private studentService: StudentService, private router: Router) { 
    this.subscription = studentService.RegenerateData$.subscribe(()=>
      this.getStudents()
    );
  }

  ngOnInit(): void {
    this.getStudents();
  }


  getStudents(){
    this.studentService.getStudents().subscribe(
      response => {
        this.students = response.body;
      }
    )
  }
  goToViewStudent(s:Student): void{
    this.router.navigate(['/student-detail', s.id])
  }

  deleteStudent(s: Student): void{
    this.studentService.deleteStudent(s.id==undefined ? 0:s.id).subscribe(
      () => this.getStudents()
    )
  }

}
