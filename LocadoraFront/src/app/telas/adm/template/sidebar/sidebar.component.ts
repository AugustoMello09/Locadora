import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  sidebarVisible: boolean = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  categoria() {
    this.router.navigate(['/categorias']);
  }

  home() {
    this.router.navigate(['/homeAdm']);
  }

  reservas() {
    this.router.navigate(['/reservasAdm']);
  }

  filmes() {
    this.router.navigate(['/filmes']);
  }
  
  estoque() {
    this.router.navigate(['/estoque']);
  }

  multa() {
    this.router.navigate(['/multas']);
  }

  users() {
    this.router.navigate(['/usuarios']);
  }

  locacao() {
    this.router.navigate(['/locacao'])
  }

}
