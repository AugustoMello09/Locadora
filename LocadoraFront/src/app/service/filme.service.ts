import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Filme } from '../model/filme.model';

@Injectable({
  providedIn: 'root'
})
export class FilmeService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findAllPaged(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    params = params.set('sort', 'id,desc');
    return this.http.get<any>(this.baseUrl + '/filmes/v1/', { params });
  }

  findById(id: any): Observable<Filme> {
    const url = `${this.baseUrl}/filmes/${id}`
    return this.http.get<Filme>(url);
  }
}
