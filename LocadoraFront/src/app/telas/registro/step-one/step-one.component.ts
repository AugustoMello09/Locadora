import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'primeng/api';
import { User } from 'src/app/model/user.model';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.css']
})
export class StepOneComponent implements OnInit {

  message1: Message[] = [];

  user: User = {
    name: '',
    email: '',
    password: '',
    cpf: '',
    roles: [
      { id: 1 } 
    ]
  }

  constructor(private authService: AuthService, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  create(): void {
    this.userService.create(this.user).subscribe((res) => {
      const userId = res.id;
      localStorage.setItem('userId', userId!);
      this.authService.tentarLogar(this.user.email, this.user.password).subscribe(response => {
        const token_access = JSON.stringify(response);
        localStorage.setItem('token_access', token_access);
        this.router.navigate(['/stepTwo']);
      })
    }, err => {
      for (const error of err.error.errors) {
        this.addMessage(error.fieldName, error.message); 
      }
    }); 
  }

  addMessage(fieldName: string, errorMessage: string): void {
    this.message1 = [{ severity: 'error', summary: 'Erro', detail: `${fieldName}: ${errorMessage}` }];
  }

}
