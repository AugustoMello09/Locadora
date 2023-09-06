import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservaOnline } from '../model/reservaOnline.model';
import { ReservaOnlineInsert } from '../model/reservaOnlineInsert.model';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  baseUrl: string = environment.baseUrl;

  constructor(private http: HttpClient) { }

  findAllPaged(page: number, size: number): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    params = params.set('sort', 'id,desc');
    return this.http.get<any>(this.baseUrl + '/reservasOnline', { params });
  }

  findById(id: any): Observable<ReservaOnline>{
    const url = `${this.baseUrl}/reservasOnline/${id}`;
    return this.http.get<ReservaOnline>(url);
  }

  cancelar(id: any): Observable<ReservaOnline>{
    const url = `${this.baseUrl}/reservasOnline/cancelar/${id}`;
    return this.http.post<ReservaOnline>(url, id);
  }

  create(reserva: ReservaOnlineInsert): Observable<ReservaOnlineInsert>{
    const url = `${this.baseUrl}/reservasOnline`;
    return this.http.post<ReservaOnlineInsert>(url, reserva);
  }

  formatarData(data: any): string {
    const dateObj = new Date(data);
    const dia = dateObj.getDate().toString().padStart(2, '0');
    const mes = (dateObj.getMonth() + 1).toString().padStart(2, '0');
    const ano = dateObj.getFullYear().toString();

    return `${dia}/${mes}/${ano}`;
  }

}
