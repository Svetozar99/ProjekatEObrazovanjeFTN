import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

import { User } from '../../model/user';
import { LoginData } from 'src/app/model/loginData';
import { JWT } from 'src/app/model/jwt';

@Injectable()
export class UserService {
    private usersUrl = 'api/users';
    private loginUrl = 'api/login';

    private jwt: JWT={value:''};

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getUsers(): Observable<HttpResponse<User[]>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        console.log("jwt: "+JSON.stringify(this.jwt));
        var headers = {'X-Auth-Token': this.jwt.value};
        return this.http.get<User[]>(this.usersUrl, {observe: 'response',headers:headers});
    }

    login(loginData: LoginData): Observable<HttpResponse<JWT>> {
        return this.http.post<JWT>(this.loginUrl, loginData, {observe: 'response'});
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