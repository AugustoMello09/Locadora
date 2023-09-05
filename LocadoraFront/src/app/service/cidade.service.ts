import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { EstadoCidade } from '../model/cidadeEstado.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  create(cidade: EstadoCidade): Observable<EstadoCidade> {
    const url = `${this.baseUrl}/cidades`
    return this.http.post<EstadoCidade>(url, cidade);
  }

  findByCidade(name: any): Observable<EstadoCidade> {
    const url = `${this.baseUrl}/cidades/local/${name}`;
    return this.http.get<EstadoCidade>(url);
  }
}
