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
    this.router.navigate(['']);
  }

  home() {
    this.router.navigate(['/homeAdm']);
  }

  reservas() {
    this.router.navigate(['']);
  }

  filmes() {
    this.router.navigate(['']);
  }
  
  estoque() {
    this.router.navigate(['']);
  }

  multa() {
    this.router.navigate(['']);
  }

  users() {
    this.router.navigate(['/usuarios']);
  }

  locacao() {
    this.router.navigate([''])
  }

}
