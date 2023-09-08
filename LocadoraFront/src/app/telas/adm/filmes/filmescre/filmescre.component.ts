import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'primeng/api';
import { Categoria } from 'src/app/model/categoria.model';
import { FilmeInsert } from 'src/app/model/filmeInsert.model';
import { CategoriaService } from 'src/app/service/categoria.service';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-filmescre',
  templateUrl: './filmescre.component.html',
  styleUrls: ['./filmescre.component.css']
})
export class FilmescreComponent implements OnInit {
  
  message1: Message[] = [];

  id_estoque = '';

  categorias: Categoria[] = [];

  categoriaEsco: Categoria = {
    id: '',
    nomeCategoria: ''
  }

  filme: FilmeInsert = {
    nome: '',
    descricao: '',
    diretor: '',
    valorAluguel: '',
    estoque: {
      id: ''
    },
    categoria: {
      id: ''
    }
  }
  
  constructor(private router: ActivatedRoute, private categoriaService: CategoriaService,
    private filmeService: FilmeService, private route: Router) { }

  ngOnInit(): void {
    this.id_estoque = this.router.snapshot.paramMap.get('id')!;
    this.filme.estoque.id = this.id_estoque;
    this.list();
  }

  onSubmit(): void {
    this.filmeService.create(this.filme).subscribe(result => {
      this.filme = result;
      this.route.navigate(['/filmes'])
    }, err => {
        for (const error of err.error.errors) {
          this.addMessage(error.fieldName, error.message); 
        }
    })
  }

  addMessage(fieldName: string, errorMessage: string): void {
    this.message1 = [{ severity: 'error', summary: 'Erro', detail: `${fieldName}: ${errorMessage}` }];
  }

  list(): void {
    this.categoriaService.findAll().subscribe(data => {
      this.categorias = data;
   })
  }

}
