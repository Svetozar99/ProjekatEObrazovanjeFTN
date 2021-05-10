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
        // const expectedRoles: string = route.data.expectedRoles;
		console.log("Route: "+route);
		const token = localStorage.getItem('loggedUser');
		const jwt: JwtHelperService = new JwtHelperService();

		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}
		const info = jwt.decodeToken(token);
		console.log(JSON.stringify(info) + 'info');

		//const roles: string[] = expectedRoles.split('|', 2);
        //console.log("Roles: "+JSON.stringify(roles));
		// console.log(JSON.stringify(info.roles));
		if (info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "users") {
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "courses"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "add-course-instance"){
			this.router.navigate(['/home']);
			return false;
		}else if(info.roles[0].authority.indexOf("ROLE_ADMINISTRATOR") === -1 && route.url.toString() === "courses-specifications"){
			this.router.navigate(['/home']);
			return false;
		}
		return true;
    }
}
