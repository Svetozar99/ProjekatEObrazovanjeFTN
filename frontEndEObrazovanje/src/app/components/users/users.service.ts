import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

import { User } from '../../model/user';
import { LoginData } from 'src/app/model/loginData';
import { JWT } from 'src/app/model/jwt';
import { Role } from 'src/app/model/role';
import { CourseInstance } from 'src/app/model/courseInstance';
import { Student } from 'src/app/model/student';
import { ChangePass } from 'src/app/model/changePass';

@Injectable()
export class UserService {
    private usersUrl = 'api/users';
    private loginUrl = 'api/login';
    private signUp = 'api/signup';
    private studentURL = 'api/student'

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getUsers(): Observable<HttpResponse<User[]>> {
        return this.http.get<User[]>(this.usersUrl, {observe: 'response'});
    }

    getCourseInstanceStudents(courseInstance:CourseInstance): Observable<HttpResponse<Student[]>> {
        const url = `${this.studentURL}/course-instance/${courseInstance.id}`
        return this.http.get<Student[]>(url, {observe: 'response'});
    }

    getCourseInstanceOtherStudents(courseInstance:CourseInstance): Observable<HttpResponse<Student[]>> {
        const url = `${this.studentURL}/course-instance/other-students/${courseInstance.id}`
        return this.http.get<Student[]>(url, {observe: 'response'});
    }

    login(loginData: LoginData): Observable<HttpResponse<JWT>> {
        return this.http.post<JWT>(this.loginUrl, loginData, {observe: 'response'});
    }

    getUser(id: number): Observable<HttpResponse<User>> {
        const url = `${this.usersUrl}/${id}`;
        return this.http.get<User>(url, {observe: 'response'});
    }

    getStudent(id: number): Observable<HttpResponse<Student>> {
        const url = `${this.studentURL}/${id}`;
        return this.http.get<Student>(url, {observe: 'response'});
    }

    getLoggedUser(): Observable<HttpResponse<User>> {
        const url = `${this.usersUrl}/loggedUser`;
        return this.http.get<User>(url, {observe: 'response'});
    }

    getUnassignedRoles(username: string): Observable<HttpResponse<Role[]>> {
        const url = `${this.usersUrl}/${username}/unassigned-roles`;
        return this.http.get<Role[]>(url, {observe: 'response'});
    }

    editUser(user: User): Observable<HttpResponse<User>> {
        return this.http.put<User>(this.usersUrl, user, {observe: 'response'});
    }

    addUser(user: User): Observable<HttpResponse<User>> {
        return this.http.post<User>(this.signUp, user, {observe: 'response'});
    }

    deleteUser(userId: number): Observable<HttpResponse<any>> {
        const url = `${this.usersUrl}/${userId}`;
        return this.http.delete<any>(url, {observe: 'response'});
    }

    changePass(changePass: ChangePass): Observable<HttpResponse<User>>{
        const url = `api/change-password`;
        return this.http.post<User>(url, changePass, {observe: 'response'});
    }
    // getUserRoles(userId: number): Observable<HttpResponse<Role[]>> {
    //     const url = `${this.usersUrl}/${userId}/role`;
    //     return this.http.get<Role[]>(url, {observe: 'response'});
    // } 
}