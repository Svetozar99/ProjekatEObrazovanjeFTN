import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthenticationService } from "../services/authentication.service";

@Injectable({
	providedIn: 'root'
})
export class RoleGuard implements CanActivate{

    constructor(
		public auth: AuthenticationService,
		public router: Router
	) { }

    canActivate(route: ActivatedRouteSnapshot): boolean {
        const expectedRoles: string = route.data.expectedRoles;
		const token = localStorage.getItem('loggedUser');
		const jwt: JwtHelperService = new JwtHelperService();
		
		// console.log(JSON.stringify(expectedRoles) + "excetedRoles");
		console.log("token" + JSON.stringify(token));
		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}
		const info = jwt.decodeToken(token);
		console.log(JSON.stringify(info) + 'info');
		// const roles: string[] = expectedRoles.split(' ', 2);
        // console.log("Roles: "+JSON.stringify(roles));

		const roles: string[] = info.roles.split(" ");
		roles.forEach(role => {
			if(role==='ROLE_STUDENT'){
				this.router.navigate(['/home']);
			}
		});
		// if (roles.indexOf(info.roles[0].authority) === -1) {
		// 	this.router.navigate(['/home']);
		// 	return false;
		// }
		return true;
    }
}
