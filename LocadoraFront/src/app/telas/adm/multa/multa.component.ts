import { Component, OnInit } from '@angular/core';
import { Multa } from 'src/app/model/multa.model';
import { MultaService } from 'src/app/service/multa.service';

@Component({
  selector: 'app-multa',
  templateUrl: './multa.component.html',
  styleUrls: ['./multa.component.css']
})
export class MultaComponent implements OnInit {

  multaPaged: Multa[] = [];

  constructor(private multaService: MultaService) { }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.multaService.findAll().subscribe(multa => {
      this.multaPaged = multa;
    })
  }

}
