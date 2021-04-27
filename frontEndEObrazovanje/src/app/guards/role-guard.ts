import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from "../services/authentication.service";

@Injectable({
	providedIn: 'root'
})
export class RoleGuard implements CanActivate{

    constructor(
		// public auth: AuthenticationService,
		public router: Router
	) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const expectedRoles: string = route.data.expectedRoles;
		const token = localStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();

		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}

		const info = jwt.decodeToken(token);
		const roles: string[] = expectedRoles.split('|', 2);
        console.log("Roles: "+JSON.stringify(roles));

		// if (roles.indexOf(info.role[0].authority) === -1) {
		// 	this.router.navigate(['/home']);
		// 	return false;
		// }
		return true;
    }
}
