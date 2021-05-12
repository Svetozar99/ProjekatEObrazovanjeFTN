import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ExamDetail } from "src/app/model/examDetail";
import { JWT } from "src/app/model/jwt";

@Injectable()
export class ExamDetailService{
    private examDetailUrl = 'api/exam-part';
    private courseId:number = 0;

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getExamParts(courseId: number,role:string|undefined): Observable<HttpResponse<ExamDetail[]>>{
        var url = ``;
        this.courseId = courseId;
        if(role==='ROLE_ADMINISTRATOR'){
            url = `${this.examDetailUrl}/course-instance/${courseId}`;
        }else if(role === 'ROLE_STUDENT'){
            url = `${this.examDetailUrl}/student/${courseId}`;
        }
        return this.http.get<ExamDetail[]>(url, {observe: 'response'});
    }
    
    addExamPart(examPart: ExamDetail): Observable<HttpResponse<ExamDetail>> {
        return this.http.post<ExamDetail>(this.examDetailUrl, examPart, {observe: 'response'});
    }

    getCourseId(){
        return this.courseId;
    }
}