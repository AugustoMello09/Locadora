import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-categoria-filme',
  templateUrl: './categoria-filme.component.html',
  styleUrls: ['./categoria-filme.component.css']
})
export class CategoriaFilmeComponent implements OnInit {

  id_categoria = '';

  filmePaged: any;

  constructor(private filmeService: FilmeService,
    private router: ActivatedRoute, private route: Router,) { }

  ngOnInit(): void {
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
}
