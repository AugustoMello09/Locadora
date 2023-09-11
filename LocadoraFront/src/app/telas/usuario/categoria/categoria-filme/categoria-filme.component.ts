import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-categoria-filme',
  templateUrl: './categoria-filme.component.html',
  styleUrls: ['./categoria-filme.component.css']
})
export class CategoriaFilmeComponent implements OnInit {

  isAdmin: boolean = false;

  id_categoria = '';

  filmePaged: any;

  constructor(private filmeService: FilmeService,
    private router: ActivatedRoute, private route: Router, private auth: AuthService) { }

  ngOnInit(): void {
    const userRoles = this.auth.hasAdminRole();
    this.isAdmin = userRoles.includes('ROLE_ADMIN');
    this.id_categoria = this.router.snapshot.paramMap.get('id')!;
    this.readFilmes();
  }
  
  readFilmes() {
    this.filmeService.findAll(this.id_categoria).subscribe( 
     (res) => {
      this.filmePaged = res;
      }, err => {
        console.log(err)
      }
    )
  }

  voltar(): void {
    if (this.isAdmin) {
      this.route.navigate(['/categorias'])
    } else {
      this.route.navigate(['/lobbyCategorias'])
    }
  }
}
