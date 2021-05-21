import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { CourseInstance } from "src/app/model/courseInstance";

@Injectable()
export class CourseInstanceForStudentService {
    private courseInstancesForStudent = 'api/course-instance';

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getStudentCourses(username: String): Observable<HttpResponse<CourseInstance[]>>{
        var url = `${this.courseInstancesForStudent}/all/for-student/${username}`;
        return this.http.get<CourseInstance[]>(url, {observe: 'response'});
    }
}