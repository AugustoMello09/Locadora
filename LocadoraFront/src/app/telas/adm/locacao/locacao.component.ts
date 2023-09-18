import { Component, OnInit } from '@angular/core';
import { Locacao } from 'src/app/model/locacao.model';
import { LocacaoService } from 'src/app/service/locacao.service';

@Component({
  selector: 'app-locacao',
  templateUrl: './locacao.component.html',
  styleUrls: ['./locacao.component.css']
})
export class LocacaoComponent implements OnInit {

  locacaoPaged: Locacao[] = [];

  locacao: Locacao = {
      id:'',
      dataLocacao: '',
      dataDevolucao: '',
      dataMaxDevolucao: ''
  }

  constructor(private locacaoService: LocacaoService) { }

  ngOnInit(): void {
    this.lista();
  }

  lista(): void {
    this.locacaoService.findAll().subscribe(data => {
      this.locacaoPaged = data;
    })
  }

}
