import { Component, OnInit } from '@angular/core';
import { CategoriaService } from 'src/app/service/categoria.service';

@Component({
  selector: 'app-categoria-read',
  templateUrl: './categoria-read.component.html',
  styleUrls: ['./categoria-read.component.css']
})
export class CategoriaReadComponent implements OnInit {

  categoriasPaged: any;

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
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

}
