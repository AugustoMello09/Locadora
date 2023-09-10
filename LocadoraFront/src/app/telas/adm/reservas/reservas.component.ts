import { Component, OnInit } from '@angular/core';
import { ReservaOnline } from 'src/app/model/reservaOnline.model';
import { ReservaService } from 'src/app/service/reserva.service';

@Component({
  selector: 'app-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {

  reservaPaged: ReservaOnline[] = [];

  constructor(private reservaService: ReservaService) { }

  ngOnInit(): void {
    this.list();
  }

  public list() {
    this.reservaService.findAll().subscribe(res => {
      this.reservaPaged = res;
    })
  }

}
