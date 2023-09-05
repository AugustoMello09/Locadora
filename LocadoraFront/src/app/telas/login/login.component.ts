import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'primeng/api';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  err: any;

  message1: Message[] = [];

  login = {
    email: '',
    password: ''
  }

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }
  
  onSubmit() {
    this.authService.tentarLogar(this.login.email, this.login.password)
      .subscribe(res => {
        const token_access = JSON.stringify(res);
        localStorage.setItem('token_access', token_access);

        const userRoles = res.userRole.map((role: any) => role.authority);
        if (userRoles.includes('ROLE_ADMIN')) {
          this.router.navigate(['']);
        } else {
          this.router.navigate(['/lobby']);
        }
      }, (err:  HttpErrorResponse) => {
        this.addMessage(err);
      })
  }

  addMessage(err: HttpErrorResponse) {
    if (err.status === 400) {
      this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Dados inválidos. Verifique o email e a senha.' }];
    } else {
      this.message1 = [{ severity: 'error', summary: 'Erro', detail: 'Ocorreu um erro na autenticação.' }];
    }
  }


}
