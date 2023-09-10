import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Filmeinfo } from 'src/app/model/filmeInfo.model';
import { LocacaoInsert } from 'src/app/model/locacaoInsert.model';
import { UserInfo } from 'src/app/model/userInfo.model';
import { FilmeService } from 'src/app/service/filme.service';
import { LocacaoService } from 'src/app/service/locacao.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-locacaocri',
  templateUrl: './locacaocri.component.html',
  styleUrls: ['./locacaocri.component.css']
})
export class LocacaocriComponent implements OnInit {

  formasPagamento: any[] = [
    { label: 'Cartão', value: 0 },
    { label: 'Boleto', value: 1 },
    { label: 'Pix', value: 2 }
  ];
  
  users: UserInfo[] = [];

  filmes: Filmeinfo[] = [];

  locacao: LocacaoInsert = {
    qtd: '',
    formaPagamento: '',
    user: {
      id: ''
    },
    filme: {
      id: ''
    }

  }

  constructor(private filmeService: FilmeService, private route: Router,
    private userService: UserService, private locacaoService: LocacaoService) { }

  ngOnInit(): void {
    this.listaUser();
    this.listFilmes();
  }

  listFilmes(): void {
    this.filmeService.findAllDrop().subscribe(data => {
      this.filmes = data;
    })
  }

  listaUser(): void {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    })
  }

  create(): void {
    this.locacaoService.create(this.locacao).subscribe(data => {
      this.locacao = data;
      this.route.navigate(['/locacao']);
    })
  }

}
