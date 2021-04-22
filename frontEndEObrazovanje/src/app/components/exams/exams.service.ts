import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Exam } from "src/app/model/exam";
import { JWT } from "src/app/model/jwt";

@Injectable()
export class ExamsService{
    private examsUrl = 'api/exam';

    private jwt: JWT={value:''};

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    // getExams(): Observable<HttpResponse<Exam[]>> {
    //     var j = localStorage.getItem('jwt')
    //     this.jwt = j==null? {value:''}:{value:j};

    //     var headers = {'X-Auth-Token':this.jwt.value};
    //     return this.http.get<Exam[]>(this.examsUrl, {observe:'response', headers:headers});
    // }

    getStudentExams(): Observable<HttpResponse<Exam[]>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};

        // const url = `${this.examsUrl}`;
        return this.http.get<Exam[]>(this.examsUrl, {observe: 'response', headers:headers});
    } 

    getExam(id: number): Observable<HttpResponse<Exam>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};

        var headers = {'X-Auth-Token': this.jwt.value};
        const url = `${this.examsUrl}/${id}`;
        return this.http.get<Exam>(url, {observe: 'response', headers:headers});
    }
}