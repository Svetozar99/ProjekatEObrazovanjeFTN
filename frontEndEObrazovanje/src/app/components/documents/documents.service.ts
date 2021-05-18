import { formatDate } from "@angular/common";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Document } from "src/app/model/document";
import { Url } from "src/app/model/url";

@Injectable()
export class DocumentsService {
    private documentUrl = 'api/document';

    constructor(private http: HttpClient){ }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getStudentDocuments(): Observable<HttpResponse<Document[]>>{

        return this.http.get<Document[]>(this.documentUrl, {observe: 'response'});
    }

    getDocument(id: number): Observable<HttpResponse<Document>>{
        const url = `${this.documentUrl}/${id}`;
        return this.http.get<Document>(url, {observe:'response'});
    }

    addFile(formData: FormData): Observable<HttpResponse<Url>> {
        console.log("Saljem fajl!")
        const url = `${this.documentUrl}/add-file`
        return this.http.post<Url>(url, formData, {observe: 'response'});
    }

    downloadFile(u:string): Observable<Blob> {
        const formData = new FormData();
        formData.append("url", u);
        const url = `${this.documentUrl}/download`;
        return this.http.post(url,formData, {
            responseType: 'blob'
        })
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