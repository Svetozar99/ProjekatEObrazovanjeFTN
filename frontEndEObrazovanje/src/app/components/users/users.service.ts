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
        // console.log("jwt: "+JSON.stringify(this.jwt));
        var headers = {'X-Auth-Token': this.jwt.value};
        return this.http.get<User[]>(this.usersUrl, {observe: 'response',headers:headers});
    }

    login(loginData: LoginData): Observable<HttpResponse<JWT>> {
        return this.http.post<JWT>(this.loginUrl, loginData, {observe: 'response'});
    }

    getUser(id: number): Observable<HttpResponse<User>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        // console.log("jwt: "+JSON.stringify(this.jwt));
        var headers = {'X-Auth-Token': this.jwt.value};
        const url = `${this.usersUrl}/${id}`;
        return this.http.get<User>(url, {observe: 'response',headers:headers});
    }

    getUnassignedRoles(username: string): Observable<HttpResponse<Role[]>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        console.log("jwt: "+JSON.stringify(this.jwt));
        var headers = {'X-Auth-Token': this.jwt.value};
        const url = `${this.usersUrl}/${username}/unassigned-roles`;
        return this.http.get<Role[]>(url, {observe: 'response',headers:headers});
    }

    editUser(user: User): Observable<HttpResponse<User>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        // console.log("jwt: "+JSON.stringify(this.jwt));
        var headers = {'X-Auth-Token': this.jwt.value};
        return this.http.put<User>(this.usersUrl, user, {observe: 'response',headers:headers});
    }

    addUser(user: User): Observable<HttpResponse<User>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        
        var headers = {'X-Auth-Token': this.jwt.value};
        return this.http.post<User>(this.signUp, user, {observe: 'response',headers:headers});
    }

    deleteUser(userId: number): Observable<HttpResponse<any>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};
        var headers = {'X-Auth-Token': this.jwt.value};
        const url = `${this.usersUrl}/${userId}`;
        return this.http.delete<any>(url, {observe: 'response',headers:headers});
    }

    // getUserRoles(userId: number): Observable<HttpResponse<Role[]>> {
    //     const url = `${this.usersUrl}/${userId}/role`;
    //     return this.http.get<Role[]>(url, {observe: 'response'});
    // } 
}