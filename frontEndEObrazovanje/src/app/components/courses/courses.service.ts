import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';
import { Enrollment } from 'src/app/model/enrollment';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private coursesInstanceUrl = 'api/course-instance';

  private coursesSpecificationUrl = 'api/course-specfication';

  private enrolmentUrl = 'api/enrollment';

  constructor(private http: HttpClient) { }

  private RegenerateData = new Subject<void>();

  RegenerateData$ = this.RegenerateData.asObservable();

  announceChange() {
      this.RegenerateData.next();
  }

  getCoursesInstances():Observable<HttpResponse<CourseInstance[]>> {
    return this.http.get<CourseInstance[]>(this.coursesInstanceUrl, {observe: 'response'});
  }

  getCoursesSpecifications():Observable<HttpResponse<CourseSpecification[]>> {
    return this.http.get<CourseSpecification[]>(this.coursesSpecificationUrl, {observe: 'response'});
  }

  getCourseInstance(id: number): Observable<HttpResponse<CourseInstance>> {
    const url = `${this.coursesInstanceUrl}/${id}`;
    return this.http.get<CourseInstance>(url, {observe: 'response'});
  }

  getCourseSpecification(id: number): Observable<HttpResponse<CourseSpecification>> {
    const url = `${this.coursesSpecificationUrl}/${id}`;
    return this.http.get<CourseSpecification>(url, {observe: 'response'});
  }

  editCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
    return this.http.put<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  editCourseSpecification(courseSpecification: CourseSpecification): Observable<HttpResponse<CourseSpecification>> {
    return this.http.put<CourseSpecification>(this.coursesSpecificationUrl, courseSpecification, {observe: 'response'});
  }

  addCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
      return this.http.post<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  addCourseSpecification(courseSpecification: CourseSpecification): Observable<HttpResponse<CourseSpecification>> {
    return this.http.post<CourseSpecification>(this.coursesSpecificationUrl, courseSpecification, {observe: 'response'});
  }

  addEnrollment(enrolment: Enrollment): Observable<HttpResponse<Enrollment>> {
    return this.http.post<Enrollment>(this.enrolmentUrl, enrolment, {observe: 'response'});
  }

  deleteEnrollment(enrolment: Enrollment): Observable<HttpResponse<Enrollment>> {
    const url = `${this.enrolmentUrl}/${enrolment.courseInstanceDTO.id}/${enrolment.studentDTO.cardNumber}`
    return this.http.delete<Enrollment>(url, {observe: 'response'});
  }

  deleteCourseInstance(courseInstanceId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesInstanceUrl}/${courseInstanceId}`;
    return this.http.delete<any>(url, {observe: 'response'});
  }

  deleteCourseSpecification(courseSpecificationId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesSpecificationUrl}/${courseSpecificationId}`;
    return this.http.delete<any>(url, {observe: 'response'});
  }
}