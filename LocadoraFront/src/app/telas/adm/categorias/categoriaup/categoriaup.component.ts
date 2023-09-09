import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Categoria } from 'src/app/model/categoria.model';
import { CategoriaService } from 'src/app/service/categoria.service';

@Component({
  selector: 'app-categoriaup',
  templateUrl: './categoriaup.component.html',
  styleUrls: ['./categoriaup.component.css']
})
export class CategoriaupComponent implements OnInit {

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

  atualizar(): void {
    this.categoriaService.update(this.categoria).subscribe(data => {
      this.categoria = data;
      this.route.navigate(['/categorias']);
    })
  }

}
