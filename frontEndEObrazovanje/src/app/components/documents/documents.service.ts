import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Document } from "src/app/model/document";
import { JWT } from "src/app/model/jwt";
import { Url } from "src/app/model/url";

@Injectable()
export class DocumentsService {
    private documentUrl = 'api/document';

    private jwt: JWT={value:''};

    constructor(private http: HttpClient){ }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getStudentDocuments(): Observable<HttpResponse<Document[]>>{
        var j = localStorage.getItem('jwt')
        this.jwt = j==null? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};

        return this.http.get<Document[]>(this.documentUrl, {observe: 'response', headers:headers});
    }

    getDocument(id: number): Observable<HttpResponse<Document>>{
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};
        const url = `${this.documentUrl}/${id}`;
        return this.http.get<Document>(url, {observe:'response', headers:headers});
    }

    addFile(formData: FormData): Observable<HttpResponse<Url>> {
        console.log("Saljem fajl!")
        const url = `${this.documentUrl}/add-file`
        return this.http.post<Url>(url, formData, {observe: 'response'});
    }

    addDocument(document: Document): Observable<HttpResponse<Document>> {
        console.log("Saljem fajl!")
        const url = `${this.documentUrl}`
        return this.http.post<Document>(url, document, {observe: 'response'});
    }

    deleteDocument(documentId: number): Observable<HttpResponse<any>> {
        const url = `${this.documentUrl}/${documentId}`;
        return this.http.delete<any>(url, {observe: 'response'});
    }
}