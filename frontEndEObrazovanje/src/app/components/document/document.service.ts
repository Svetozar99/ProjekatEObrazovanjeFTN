import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Document } from 'src/app/model/document';
import { Url } from 'src/app/model/url';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private documentURL = 'api/document';

  constructor(private http: HttpClient) { }

  private RegenerateData = new Subject<void>();

  RegenerateData$ = this.RegenerateData.asObservable();

  announceChange(){
      this.RegenerateData.next();
  }

  addFile(formData: FormData): Observable<HttpResponse<Url>> {
    console.log("Saljem fajl!")
    const url = `${this.documentURL}/add-file`
    return this.http.post<Url>(url, formData, {observe: 'response'});
  }

  addDocument(document: Document): Observable<HttpResponse<Document>> {
    console.log("Saljem fajl!")
    const url = `${this.documentURL}`
    return this.http.post<Document>(url, document, {observe: 'response'});
  }
}
