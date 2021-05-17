import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ExamPart } from "src/app/model/examPart";

@Injectable()
export class ExamPartService{
    private examDetailUrl = 'api/exam-part';
    private courseId:number = 0;

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getExamParts(courseId: number,role:string|undefined): Observable<HttpResponse<ExamPart[]>>{
        console.log("Get exam parts!"+role)
        var url = ``;
        this.courseId = courseId;
        if(role==='ROLE_ADMINISTRATOR'){
            url = `${this.examDetailUrl}/course-instance/${courseId}`;
        }else if(role === 'ROLE_STUDENT'){
            url = `${this.examDetailUrl}/student/${courseId}`;
        }
        return this.http.get<ExamPart[]>(url, {observe: 'response'});
    }

    getExamPartsForStudent(courseId:number,cardNumber: string): Observable<HttpResponse<ExamPart[]>>{
        console.log('getExamPartsForStudent...')
        var url = `${this.examDetailUrl}/${courseId}/${cardNumber}`;
        return this.http.get<ExamPart[]>(url, {observe: 'response'});
    }
    
    addExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        return this.http.post<ExamPart>(this.examDetailUrl, examPart, {observe: 'response'});
    }

    editExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/one-exam-part`
        return this.http.put<ExamPart>(url, examPart, {observe: 'response'});
    }

    registeUnregisterExamPart(examPart: ExamPart): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/register-unregister-exam-part`
        return this.http.put<ExamPart>(url, examPart, {observe: 'response'});
    }

    getExamPart(id: number): Observable<HttpResponse<ExamPart>> {
        const url = `${this.examDetailUrl}/${id}`;
        return this.http.get<ExamPart>(url, {observe: 'response'});
    }

    getCourseId(){
        return this.courseId;
    }
}