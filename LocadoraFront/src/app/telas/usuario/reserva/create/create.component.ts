import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'primeng/api';
import { ReservaOnlineInsert } from 'src/app/model/reservaOnlineInsert.model';
import { ReservaService } from 'src/app/service/reserva.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  message1: Message[] = [];

  reservar: ReservaOnlineInsert = {
    qtdReservada: '',
    dataReserva: '',
    user: {
      id: ''
    },
    estoque: {
      id: ''
    }
  }

  constructor(private route: ActivatedRoute,
    private router: Router,
    private reservaService: ReservaService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('idEstoque');
    this.reservar.estoque.id = id!;
    const access_token = localStorage.getItem('token_access');
    if (access_token) {
      const parsedTokenData = JSON.parse(access_token);
      const userId = parsedTokenData.userId;
      this.reservar.user.id = userId;
    }
  }

  create(): void{
    this.reservar.dataReserva = this.reservaService.formatarData(this.reservar.dataReserva);
    this.reservaService.create(this.reservar).subscribe(res => {
      this.reservar = res;
      //this.router.navigate(['/reservas'])
    }, (err: HttpErrorResponse) => {
      this.addMessage(err);
      for (const error of err.error.errors) {
        this.addMessageDois(error.message); 
      }
    })
  }
  
  addMessage(err: HttpErrorResponse) {
    if (err.status === 400) {
      if (err.error && err.error.error === 'Bad Request') {
        this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Escolha uma data para reserva' }];
      } else if (err.error && err.error.error === 'Quantidade solicitada maior do que a disponível no estoque') {
        this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Quantidade solicitada maior do que a disponível no estoque' }];
      } else {
        this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Escolha uma data para reserva' }];
      }
    }
  }

  addMessageDois(errorMessage: string): void {
    this.message1 = [{ severity: 'error', summary: 'Erro', detail: `${errorMessage}` }];
  }

}
