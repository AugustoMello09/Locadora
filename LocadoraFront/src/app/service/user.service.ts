import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';
import { UserU } from '../model/userUtil.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  create(user: User): Observable<User> {
    const url = `${this.baseUrl}/users`
    return this.http.post<User>(url, user);
  }

  findAllPaged(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    params = params.set('sort', 'name,desc');
    return this.http.get<any>(this.baseUrl + '/users', { params });
  }

  cargo(id: any, cargo: UserU): Observable<UserU> {
    const url = `${this.baseUrl}/users/${id}`
    return this.http.patch<UserU>(url, cargo);
  }

  findById(id: any): Observable<UserU> {
    const url = `${this.baseUrl}/users/${id}`
    return this.http.get<UserU>(url);
  }
}
