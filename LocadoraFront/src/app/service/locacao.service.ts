import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Locacao } from '../model/locacao.model';
import { Observable } from 'rxjs';
import { LocacaoInsert } from '../model/locacaoInsert.model';

@Injectable({
  providedIn: 'root'
})
export class LocacaoService {

  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findAll(): Observable<Locacao[]> {
    const url = `${this.baseUrl}/locacao`
    return this.http.get<Locacao[]>(url);
  }

  findById(id: any): Observable<Locacao> {
    const url = `${this.baseUrl}/locacao/${id}`
    return this.http.get<Locacao>(url);
  }

  create(locacao: LocacaoInsert): Observable<LocacaoInsert> {
    const url = `${this.baseUrl}/locacao`
    return this.http.post<LocacaoInsert>(url, locacao);
  }
}
