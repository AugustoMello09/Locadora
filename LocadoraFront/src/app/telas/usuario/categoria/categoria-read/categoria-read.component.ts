import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { CategoriaService } from 'src/app/service/categoria.service';

@Component({
  selector: 'app-categoria-read',
  templateUrl: './categoria-read.component.html',
  styleUrls: ['./categoria-read.component.css']
})
export class CategoriaReadComponent implements OnInit {

  isAdmin: boolean = false;

  categoriasPaged: any;

  constructor(private categoriaService: CategoriaService, private auth: AuthService,  private route: Router) { }

  ngOnInit(): void {
    const userRoles = this.auth.hasAdminRole();
    this.isAdmin = userRoles.includes('ROLE_ADMIN');
    this.readCategorias();
  }

  public readCategorias() {
    this.categoriaService.findAllPaged(0, 10).subscribe( 
     res => {
      this.categoriasPaged = res.content;
      }, err => {
        console.log(err)
      }
    )
  }
  
  voltar(): void {
    if (this.isAdmin) {
      this.route.navigate(['/homeAdm'])
    } else {
      this.route.navigate(['/lobby'])
    }
  }

}
