import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Filme } from 'src/app/model/filme.model';
import { AuthService } from 'src/app/service/auth.service';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-filmeinfo',
  templateUrl: './filmeinfo.component.html',
  styleUrls: ['./filmeinfo.component.css']
})
export class FilmeinfoComponent implements OnInit {

  id_filme = '';

  isAdmin: boolean = false;

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
    private route: Router, private auth: AuthService) { }

  ngOnInit(): void {
    const userRoles = this.auth.hasAdminRole();
    this.isAdmin = userRoles.includes('ROLE_ADMIN');
    this.id_filme = this.router.snapshot.paramMap.get('id')!;
    this.findById();
  }

  findById(): void {
    this.filmeService.findById(this.id_filme).subscribe(resp => {
      this.filme = resp;
    })
  }

  reservarFilme(): void {
    if (this.filme.estoque.status === 'DISPONIVEL') {
      const estoqueId = this.filme.estoque.id;
      this.route.navigateByUrl(`/reservar/${estoqueId}`);
    }
  }

  voltar(): void {
    if (this.isAdmin) {
      this.route.navigate(['/filmes'])
    } else {
      this.route.navigate(['/lobbyFilme'])
    }
  }
}
