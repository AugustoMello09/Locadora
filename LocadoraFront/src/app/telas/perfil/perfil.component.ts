import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  tokenData: any;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.tokenData = this.authService.getTokenData();
  }

  sair(): void {
    this.authService.sairDaConta();
  }

}
