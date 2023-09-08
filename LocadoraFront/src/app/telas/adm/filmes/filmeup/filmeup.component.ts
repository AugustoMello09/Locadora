import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Message } from 'primeng/api';
import { Filme } from 'src/app/model/filme.model';
import { Filmeup } from 'src/app/model/filmeup.model';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-filmeup',
  templateUrl: './filmeup.component.html',
  styleUrls: ['./filmeup.component.css']
})
export class FilmeupComponent implements OnInit {

  message1: Message[] = [];

  id_filme = '';

  filme: Filmeup = {
    nome: '',
    descricao: '',
    diretor: '',
    valorAluguel: ''
  }

  constructor(private filmeService: FilmeService, private router: ActivatedRoute,private route: Router) { }

  ngOnInit(): void {
    this.id_filme = this.router.snapshot.paramMap.get('id')!;
    this.findById();
  }

  findById() {
    this.filmeService.findById(this.id_filme).subscribe(data => {
      this.filme = data;
    })
  }

  update() {
    this.filmeService.update(this.filme).subscribe(data => {
      this.filme = data;
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

}
