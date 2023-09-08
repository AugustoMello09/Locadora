import { Injectable } from '@angular/core';
import { EstoqueInfo } from '../model/estoqueinfo.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Estoque } from '../model/estoque.model';

@Injectable({
  providedIn: 'root'
})
export class EstoqueService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findById(id: any): Observable<EstoqueInfo> {
    const url = `${this.baseUrl}/estoques/${id}`;
    return this.http.get<EstoqueInfo>(url);
  }

  create(estoque: Estoque): Observable<Estoque> {
    const url = `${this.baseUrl}/estoques`;
    return this.http.post<Estoque>(url, estoque);
  }

  findAll(): Observable<EstoqueInfo[]> {
    const url = `${this.baseUrl}/estoques`;
    return this.http.get<EstoqueInfo[]>(url);
  }
}
