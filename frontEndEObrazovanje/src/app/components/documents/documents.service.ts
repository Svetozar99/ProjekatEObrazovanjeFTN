import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Documentt } from "src/app/model/document";
import { JWT } from "src/app/model/jwt";

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

    getStudentDocuments(): Observable<HttpResponse<Documentt[]>>{
        var j = localStorage.getItem('jwt')
        this.jwt = j==null? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};

        return this.http.get<Documentt[]>(this.documentUrl, {observe: 'response', headers:headers});
    }

    getDocument(id: number): Observable<HttpResponse<Documentt>>{
        var j = localStorage.getItem('jwt')
        this.jwt = j==null ? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};
        const url = `${this.documentUrl}/${id}`;
        return this.http.get<Documentt>(url, {observe:'response', headers:headers});
    }
}