import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

import { User } from '../../model/user';

@Injectable()
export class UserService {
    private usersUrl = 'api/users';

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getUsers(): Observable<HttpResponse<User[]>> {
        // var conf={
        //     method: 'GET',
        //     headers: { 'Content-Type': 'application/json',
        //                 'X-Auth-Token':'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicmJvcmljOTkiLCJjcmVhdGVkIjoxNjE4OTIzNjU2MjczLCJleHAiOjE2MTg5NDE2NTZ9.IF6a9XZbhtsVv-Q-s93U-QGvCVKlFlRvuyZlUeTCiC5MAazI0JtSz07JEHZi-UHO522IEV2l_PUxGqyuZ19YcA'},
        //     observe: 'response'
        //   };
        return this.http.get<User[]>(this.usersUrl, {observe: 'response'});
    }

//    getStudent(id: number): Observable<HttpResponse<Student>> {
//         const url = `${this.studentsUrl}/${id}`;
//         return this.http.get<Student>(url, {observe: 'response'});
//     }

//     addStudent(student: Student): Observable<HttpResponse<Student>> {
//         return this.http.post<Student>(this.studentsUrl, student, {observe: 'response'});
//     }

//     editStudent(student: Student): Observable<HttpResponse<Student>> {
//         return this.http.put<Student>(this.studentsUrl, student, {observe: 'response'});
//     }

//     deleteStudent(studentId: number): Observable<HttpResponse<any>> {
//         const url = `${this.studentsUrl}/${studentId}`;
//         return this.http.delete<any>(url, {observe: 'response'});
//     }

//     getStudentEnrollments(studentId: number): Observable<HttpResponse<Enrollment[]>> {
//         const url = `${this.studentsUrl}/${studentId}/courses`;
//         return this.http.get<Enrollment[]>(url, {observe: 'response'});
//     } 
}