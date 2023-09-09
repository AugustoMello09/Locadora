import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'primeng/api';
import { Categoria } from 'src/app/model/categoria.model';
import { CategoriaService } from 'src/app/service/categoria.service';

@Component({
  selector: 'app-categoriadel',
  templateUrl: './categoriadel.component.html',
  styleUrls: ['./categoriadel.component.css']
})
export class CategoriadelComponent implements OnInit {

  message1: Message[] = [];

  id_category = '';

  categoria: Categoria = {
    id: '',
    nomeCategoria: ''
  }

  constructor(private router: ActivatedRoute,
    private route: Router, private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.id_category = this.router.snapshot.paramMap.get('id')!;
    this.findById();
  }


  findById(): void {
    this.categoriaService.findById(this.id_category).subscribe(data => {
      this.categoria = data;
    })
  }

  cancelar(): void {
    this.categoriaService.delete(this.id_category).subscribe(() => {
      this.route.navigate(['/categorias']);
    }, (err: HttpErrorResponse) => {
      this.addMessage(err);
    })
  }

  addMessage(err: HttpErrorResponse) {
    if (err.status === 400) {
      this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Categoria não pode ser excluida porque está associado com filmes.' }];
    }
  }

}
