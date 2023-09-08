import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdmGuard implements CanActivate {
   
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      const userRole = this.authService.hasAdminRole();
      const hasAdminAuthority = Array.isArray(userRole) && userRole.includes('ROLE_ADMIN');
      if (hasAdminAuthority) {
        return true;
      } else {
        alert('Acesso negado, Não tem acesso para acessar esse recurso!!');
        this.router.navigate(['']);
        return false; 
      }
    }
  
}
