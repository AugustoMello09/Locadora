import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Endereco } from '../model/endereco.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {
  
  baseUrl: string = environment.baseUrl;
  
  constructor(private http: HttpClient) { }

  create(endereco: Endereco): Observable<Endereco>  {
    const url = `${this.baseUrl}/enderecos`
    return this.http.post<Endereco>(url, endereco);
  }
}
