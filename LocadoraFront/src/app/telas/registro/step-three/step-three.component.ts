import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Message } from 'primeng/api';
import { Endereco } from 'src/app/model/endereco.model';
import { ConsultaCepService } from 'src/app/service/consulta-cep.service';
import { EnderecoService } from 'src/app/service/endereco.service';

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.css']
})
export class StepThreeComponent implements OnInit {

  message1: Message[] = [];

  endereco: Endereco = {
    logradouro: '',
    numero: '',
    complemento: '',
    bairro: '',
    cep: '',
    cidade: {
      id: ''
    },
    user: {
      id: ''
    }
   
  }

  constructor(private service: ConsultaCepService, private enderecoS: EnderecoService,private route: Router) { }

  ngOnInit(): void {
    const userId = localStorage.getItem('userId');
    const cidadeId = localStorage.getItem('cidadeId');
    this.endereco.user.id = userId!;
    this.endereco.cidade.id = cidadeId!;
  }

  onSubmit() {
    this.enderecoS.create(this.endereco).subscribe(response => {
      this.endereco = response;
      localStorage.removeItem('cidadeId');
      localStorage.removeItem('userId');
      this.route.navigate(['/login'])
    }, err => {
      for (const error of err.error.errors) {
        this.addMessage(error.fieldName, error.message); 
      }
    });
  }
 
  addMessage(fieldName: string, errorMessage: string): void {
    this.message1 = [{ severity: 'error', summary: 'Erro', detail: `${fieldName}: ${errorMessage}` }];
  }
  
  consultaCep(ev: any, f: NgForm) {
    const cep = ev.target.value;
    if (cep !== '') {
      this.service.getConsultaCep(cep).subscribe((response) =>
      {
        this.populandoEndereco(response, f);
      });
    }
  }

  populandoEndereco(dados: any, f: NgForm) {
    f.form.patchValue({
      numero: dados.numero,
      complemento: dados.complemento,
      bairro: dados.bairro,
      logradouro: dados.logradouro,
      name: dados.uf,
      localidade: dados.localidade
    })
  }

}
