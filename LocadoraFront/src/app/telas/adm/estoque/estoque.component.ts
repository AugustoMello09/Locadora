import { Component, OnInit } from '@angular/core';
import { EstoqueService } from 'src/app/service/estoque.service';

@Component({
  selector: 'app-estoque',
  templateUrl: './estoque.component.html',
  styleUrls: ['./estoque.component.css']
})
export class EstoqueComponent implements OnInit {

  estoquePaged: any;

  constructor(private estoqueService: EstoqueService) { }

  ngOnInit(): void {
    this.list();
  }

  list() {
    this.estoqueService.findAll().subscribe(res => {
      this.estoquePaged = res;
    })
  }

}
