import { Component, OnInit } from '@angular/core';
import { Message } from 'primeng/api';
import { Categoria } from 'src/app/model/categoria.model';
import { CategoriaService } from 'src/app/service/categoria.service';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.css']
})
export class CategoriasComponent implements OnInit {

  categoriasPaged: any;

  message1: Message[] = [];

  categoria: Categoria = {
    nomeCategoria: ''
  }


  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.readCategorias();
  }

  readCategorias() {
    this.categoriaService.findAllPaged(0, 10).subscribe( 
     res => {
      this.categoriasPaged = res.content;
      }, err => {
        console.log(err)
      }
    )
  }
  
  create() {
    this.categoriaService.create(this.categoria).subscribe(res => {
      this.categoria = res;
    }, err => {
      for (const error of err.error.errors) {
        this.addMessage(error.message); 
      }
    })
  }

  addMessage(errorMessage: string): void {
    this.message1 = [{ severity: 'error', summary: 'Erro', detail: `${errorMessage}` }];
  }

}
