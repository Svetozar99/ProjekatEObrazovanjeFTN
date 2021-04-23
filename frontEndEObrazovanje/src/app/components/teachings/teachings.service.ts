import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { JWT } from "src/app/model/jwt";
import { Teaching } from "src/app/model/teaching";

@Injectable()
export class TeachingsService {
    private teachingsUrl = "api/teaching"

    private jwt: JWT={value:''};

    constructor(private http: HttpClient){ }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange(){
        this.RegenerateData.next();
    }

    getTeachings(): Observable<HttpResponse<Teaching[]>> {
        var j = localStorage.getItem('jwt')
        this.jwt = j==null? {value:''}:{value:j};

        var headers = {'X-Auth-Token':this.jwt.value};
        return this.http.get<Teaching[]>(this.teachingsUrl, {observe: 'response', headers:headers});
    }
}