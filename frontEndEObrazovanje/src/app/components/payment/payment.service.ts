import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Payment } from "src/app/model/payment";
import { AuthenticationService } from "src/app/services/authentication.service";

@Injectable()
export class PaymentService {
    private paymentsUrl = 'api/payment';
    private accountUrl = 'api/account';

    constructor(private http: HttpClient,private authS:AuthenticationService) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getAccountPayments(numberPage:number): Observable<HttpResponse<Payment[]>> {
        return this.http.get<Payment[]>(`${this.paymentsUrl}?sort=datePayment,desc&page=${numberPage}&size=5`, {observe: 'response'});
    }

    getNumberPage(): Observable<HttpResponse<number>> {
        console.log("\ngetNumberPage")
        var user = this.authS.getLoggedUser();
        var username = JSON.stringify(user.sub).split('"')[1];
        return this.http.get<number>(`${this.paymentsUrl}/number-payments?username=${username}`, {observe: 'response'});
      }

    addAccountPayment(payment: Payment): Observable<HttpResponse<Payment>>{
        return this.http.post<Payment>(this.paymentsUrl, payment, {observe:'response'});
    }

    getStudentPayments(username:string): Observable<HttpResponse<Payment[]>>{
        var url = `${this.paymentsUrl}/for-student/${username}`;
        return this.http.get<Payment[]>(url, {observe: 'response'});
    }
}