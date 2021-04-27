import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { JWT } from '../model/jwt';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor {

  private jwt: JWT={value:''};

  constructor(private inj: Injector)  { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //let authenticationService:AuthenticationService = this.inj.get(AuthenticationService); 
    var j = localStorage.getItem('jwt')
    this.jwt = j==null ? this.jwt:{value:j};
    req = req.clone({
      setHeaders: {
        'X-Auth-Token': this.jwt.value
      }
    });

    return next.handle(req);

  }
}
