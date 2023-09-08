import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Categoria } from '../model/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findAllPaged(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    params = params.set('sort', 'nomeCategoria,desc');
    return this.http.get<any>(this.baseUrl + '/categorias', { params });
  }

  findAll(): Observable<Categoria[]> {
    const url = `${this.baseUrl}/categorias/lista`
    return this.http.get<Categoria[]>(url);
  }
}
