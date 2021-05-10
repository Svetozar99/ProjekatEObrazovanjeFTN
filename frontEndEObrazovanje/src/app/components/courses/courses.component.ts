import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CoursesService } from './courses.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {

  coursesIntances: CourseInstance[] | null = [];

  subscription: Subscription;

  constructor(private courseService: CoursesService) { 
    this.subscription = courseService.RegenerateData$.subscribe(() => 
      this.getCoursesInstances()
    );
  }

  ngOnInit(): void {
    this.getCoursesInstances();
  }

  getCoursesInstances(){
    this.courseService.getCoursesInstances().subscribe(
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

}
