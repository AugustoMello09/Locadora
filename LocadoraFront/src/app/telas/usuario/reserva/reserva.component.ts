import { Component, OnInit } from '@angular/core';
import { ReservaService } from 'src/app/service/reserva.service';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent implements OnInit {

  reservaPaged: any;

  constructor(private reservaService: ReservaService) { }

  ngOnInit(): void {
    this.readReservas();
  }

  readReservas(): void {
    this.reservaService.findAllPaged(0, 20).subscribe( 
     res => {
        this.reservaPaged = res.content;
        console.log(this.reservaPaged);
      }, err => {
        console.log(err)
      }
    )
  }

}
