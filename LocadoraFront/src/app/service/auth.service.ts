import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpParams } from '@angular/common/http';
import jwtDecode from 'jwt-decode';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl: string = environment.baseUrl;
  tokenUrl: string = environment.baseUrl + environment.obterTokenUrl;
  clientId: string = environment.clientId;
  clientSecret: string = environment.clientSecret;

  jwtHelper: JwtHelperService = new JwtHelperService();
  
  constructor(private http: HttpClient,  private router: Router) { }

  tentarLogar(email: string, password: string): Observable<any> {
    const params = new HttpParams()
      .set('username', email)
      .set('password', password)
      .set('grant_type', 'password');
    const headers = {
      'Authorization': 'Basic ' + btoa(`${this.clientId}:${this.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
    return this.http.post(this.tokenUrl, params.toString(), { headers });
  }

  isAuthenticated(): boolean {
    const token = this.obterToken();
    if (token) {
      const expired = this.jwtHelper.isTokenExpired(token)
      return !expired;
    }
    return false;
  }

  obterToken() {
    const tokenString = localStorage.getItem('token_access')
    if (tokenString) {
      const token = JSON.parse(tokenString).access_token
      return token;
    }
    return null;
  }

  hasAdminRole(): string[] { 
    const token = this.obterToken();
    if (token) {
      const decodedToken: any = jwtDecode(token);
      if (decodedToken.authorities && Array.isArray(decodedToken.authorities)) {
        const userRoles: string[] = decodedToken.authorities;
        return userRoles;
      }
    }
    return []; 
  }

  sairDaConta() {
    localStorage.removeItem('token_access');
    this.router.navigate(['/login']);
  }

  getTokenData() {
    const token =  this.obterToken();
    if (token) {
      const decodedToken = jwtDecode(token);
      return decodedToken;
    }
    return null;
  }
  
}
