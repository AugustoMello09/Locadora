import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Role } from '../model/role.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findAll(): Observable<Role[]>{
    const url = `${this.baseUrl}/roles`
    return this.http.get<Role[]>(url);
  }
}
