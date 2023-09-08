import { Component, OnInit } from '@angular/core';
import { FilmeService } from 'src/app/service/filme.service';

@Component({
  selector: 'app-filmes',
  templateUrl: './filmes.component.html',
  styleUrls: ['./filmes.component.css']
})
export class FilmesComponent implements OnInit {

  filmePaged: any;

  constructor(private filmeService: FilmeService) { }

  ngOnInit(): void {
    this.readFilmes();
  }
  
  readFilmes() {
    this.filmeService.findAllPaged(0, 20).subscribe( 
     res => {
      this.filmePaged = res.content;
      }, err => {
        console.log(err)
      }
    )
  }

}
