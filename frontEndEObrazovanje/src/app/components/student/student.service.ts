import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Student } from "src/app/model/student";

@Injectable()
export class StudentService {
    private studentsUrl = 'api/student';

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getStudents(): Observable<HttpResponse<Student[]>>{
        return this.http.get<Student[]>(this.studentsUrl, {observe:'response'});
    }

    deleteStudent(stId: number): Observable<HttpResponse<any>>{
        const url = `${this.studentsUrl}/${stId}`;
        return this.http.delete<any>(url, {observe:'response'});
    }
}