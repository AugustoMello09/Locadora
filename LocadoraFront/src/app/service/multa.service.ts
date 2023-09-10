import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Multa } from '../model/multa.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MultaService {
  
  baseUrl: string = environment.baseUrl;
  
  constructor(private http: HttpClient) { }

  findAll(): Observable<Multa[]>{
    const url = `${this.baseUrl}/multas`
    return this.http.get<Multa[]>(url);
  }
}
