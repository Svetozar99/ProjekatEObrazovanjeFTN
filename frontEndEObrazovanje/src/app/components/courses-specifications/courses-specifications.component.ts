import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { CoursesService } from '../courses/courses.service';

@Component({
  selector: 'app-courses-specifications',
  templateUrl: './courses-specifications.component.html',
  styleUrls: ['./courses-specifications.component.css']
})
export class CoursesSpecificationsComponent implements OnInit {

  coursesSpecifications: CourseSpecification[] | null = [];

  subscription: Subscription;

  constructor(private courseService: CoursesService,private router: Router) {
    this.subscription = courseService.RegenerateData$.subscribe(() => 
      this.getCoursesSpecifications()
    );
   }

  ngOnInit(): void {
    this.getCoursesSpecifications();
  }

  getCoursesSpecifications(){
    this.courseService.getCoursesSpecifications().subscribe(
      response => {
        console.log(response.body)
        this.coursesSpecifications = response.body
      });
  }

  deleteCourseSpecification(courseSpecification: CourseSpecification): void {
    console.log("Brisem: "+JSON.stringify(courseSpecification));
    this.courseService.deleteCourseSpecification(courseSpecification.id==undefined ? 0:courseSpecification.id).subscribe(
      () => this.getCoursesSpecifications()
    );
  }

  goToViewCourseSpecification(courseSpecification: CourseSpecification): void {
    this.router.navigate(['/view-course-specification', courseSpecification.id]);
  }

}
