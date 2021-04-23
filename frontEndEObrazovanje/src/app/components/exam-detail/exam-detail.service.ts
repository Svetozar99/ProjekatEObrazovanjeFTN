import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ExamDetail } from "src/app/model/examDetail";
import { JWT } from "src/app/model/jwt";

@Injectable()
export class ExamDetailService{
    private examDetailUrl = 'api/exam-part/student';

    private jwt: JWT={value:''};

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getExamParts(courseId: number): Observable<HttpResponse<ExamDetail[]>>{
        var j = localStorage.getItem('jwt');
        this.jwt = j==null? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};
        const url = `${this.examDetailUrl}/${courseId}`;
        return this.http.get<ExamDetail[]>(url, {observe: 'response', headers:headers});
    }
    
}