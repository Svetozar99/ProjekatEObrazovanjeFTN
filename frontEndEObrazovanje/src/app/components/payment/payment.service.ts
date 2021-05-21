import { HttpClient, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { Payment } from "src/app/model/payment";

@Injectable()
export class PaymentService {
    private paymentsUrl = 'api/payment';
    private accountUrl = 'api/account';

    constructor(private http: HttpClient) { }

    private RegenerateData = new Subject<void>();

    RegenerateData$ = this.RegenerateData.asObservable();

    announceChange() {
        this.RegenerateData.next();
    }

    getAccountPayments(): Observable<HttpResponse<Payment[]>> {
        return this.http.get<Payment[]>(this.paymentsUrl, {observe: 'response'});
    }

    addAccountPayment(payment: Payment): Observable<HttpResponse<Payment>>{
        return this.http.post<Payment>(this.paymentsUrl, payment, {observe:'response'});
    }

    getStudentPayments(username:string): Observable<HttpResponse<Payment[]>>{
        var url = `${this.paymentsUrl}/for-student/${username}`;
        return this.http.get<Payment[]>(url, {observe: 'response'});
    }
}