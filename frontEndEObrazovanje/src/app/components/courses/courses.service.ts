import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CourseInstance } from 'src/app/model/courseInstance';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  private coursesInstanceUrl = 'api/course-instance';

  constructor(private http: HttpClient) { }

  private RegenerateData = new Subject<void>();

  RegenerateData$ = this.RegenerateData.asObservable();

  announceChange() {
      this.RegenerateData.next();
  }

  getCoursesInstances():Observable<HttpResponse<CourseInstance[]>> {
    return this.http.get<CourseInstance[]>(this.coursesInstanceUrl, {observe: 'response'});
  }

  deleteCourseInstance(courseInstanceId: number): Observable<HttpResponse<any>> {
    const url = `${this.coursesInstanceUrl}/${courseInstanceId}`;
    return this.http.delete<any>(url, {observe: 'response'});
}
}
