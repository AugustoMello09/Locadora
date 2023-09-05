import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Estado } from '../model/estado.model';
import { Observable } from 'rxjs';
import { Cidade } from '../model/cidade.model';

@Injectable({
  providedIn: 'root'
})
export class ConsultaCepService {

  base_url = 'https://viacep.com.br/ws/';

  url_estados = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados/';

  constructor(private http: HttpClient) { }

  getConsultaCep(cep: string) {
    return this.http.get(`${this.base_url}${cep}/json`);
   }
 
   getConsultaEstados(): Observable<Estado[]> {
     const url = `${this.url_estados}`
     return this.http.get<Estado[]>(url);
   }
 
   getConsultaCidades(selectedUf: string): Observable<Cidade[]> {
     const url = `${this.url_estados}${selectedUf}/municipios`
     return this.http.get<Cidade[]>(url);
   }
}
