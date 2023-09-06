import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Filme } from 'src/app/model/filme.model';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-filmeinfo',
  templateUrl: './filmeinfo.component.html',
  styleUrls: ['./filmeinfo.component.css']
})
export class FilmeinfoComponent implements OnInit {

  id_filme = '';

  filme: Filme = {
    nome: '',
    descricao: '',
    diretor: '',
    valorAluguel: '',
    estoque: {
      id: '',
      quantidadeFilmesDisponiveis: '',
      quantidadeReservadas: '',
      quantidadeReservadasOnline: '',
      status: ''
    }
  }
  
  constructor(private filmeService: FilmeService, private router: ActivatedRoute,
    private route: Router,) { }

  ngOnInit(): void {
    this.id_filme = this.router.snapshot.paramMap.get('id')!;
    this.findById();
  }

  findById(): void {
    this.filmeService.findById(this.id_filme).subscribe(resp => {
      this.filme = resp;
    })
  }

}
