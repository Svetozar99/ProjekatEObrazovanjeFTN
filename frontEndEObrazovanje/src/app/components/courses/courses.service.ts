import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';
import { CourseSpecification } from 'src/app/model/courseSpecification';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private coursesInstanceUrl = 'api/course-instance';

  private coursesSpecificationUrl = 'api/course-specfication';

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

  editCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
    return this.http.put<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  addCourseInstance(courseInstance: CourseInstance): Observable<HttpResponse<CourseInstance>> {
      return this.http.post<CourseInstance>(this.coursesInstanceUrl, courseInstance, {observe: 'response'});
  }

  deleteCourseInstance(courseInstanceId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesInstanceUrl}/${courseInstanceId}`;
    return this.http.delete<any>(url, {observe: 'response'});
  }
}
