import { Injectable } from '@angular/core';
import { HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

import { User } from '../../model/user';
import { LoginData } from 'src/app/model/loginData';
import { JWT } from 'src/app/model/jwt';
import { Role } from 'src/app/model/role';

@Injectable()
export class UserService {
    private usersUrl = 'api/users';
    private loginUrl = 'api/login';
    private signUp = 'api/signup'

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getUsers(): Observable<HttpResponse<User[]>> {
        return this.http.get<User[]>(this.usersUrl, {observe: 'response'});
    }

    login(loginData: LoginData): Observable<HttpResponse<JWT>> {
        return this.http.post<JWT>(this.loginUrl, loginData, {observe: 'response'});
    }

    getUser(id: number): Observable<HttpResponse<User>> {
        // console.log("jwt: "+JSON.stringify(this.jwt));
        const url = `${this.usersUrl}/${id}`;
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

    // getUserRoles(userId: number): Observable<HttpResponse<Role[]>> {
    //     const url = `${this.usersUrl}/${userId}/role`;
    //     return this.http.get<Role[]>(url, {observe: 'response'});
    // } 
}