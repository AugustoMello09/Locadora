import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Estoque } from 'src/app/model/estoque.model';
import { EstoqueService } from 'src/app/service/estoque.service';

@Component({
  selector: 'app-estoquecre',
  templateUrl: './estoquecre.component.html',
  styleUrls: ['./estoquecre.component.css']
})
export class EstoquecreComponent implements OnInit {

  estoque: Estoque = {
    id: '',
    quantidade: '',
    status: '0'
  }
  
  constructor(private estoqueService: EstoqueService, private router: Router) { }

  ngOnInit(): void {
  }

  create(): void {
    this.estoqueService.create(this.estoque).subscribe(result => {
      this.estoque = result;
      const id = result.id
      this.router.navigate(['/filmescre', id])
    })
  }

}
