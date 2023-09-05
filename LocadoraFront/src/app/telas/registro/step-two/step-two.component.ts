import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { Cidade } from 'src/app/model/cidade.model';
import { EstadoCidade } from 'src/app/model/cidadeEstado.model';
import { Estado } from 'src/app/model/estado.model';
import { CidadeService } from 'src/app/service/cidade.service';
import { ConsultaCepService } from 'src/app/service/consulta-cep.service';

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.css']
})
export class StepTwoComponent implements OnInit {

  
  selectedUf!: string;

  estadoLista: Estado[] = [];
  cidadeLista: Cidade[] = [];

  estadoCidadeSelecionada: EstadoCidade = {
    name: '',
    estadoId: {
      id: ''
    }
  }

  constructor(private consultaService: ConsultaCepService,
    private cidadeService: CidadeService, private router: Router) { }

  ngOnInit(): void {
    this.getBuscaEstado();
  }

  enviarDados(): void {
    const frontEndToDbMapping: { [key: string]: string } = {
      '11': '22',
      '12': '1',
      '13': '4',
      '14': '23',
      '15': '14',
      '16': '3',
      '17': '27',
      '21': '10',
      '22': '18',
      '23': '6',
      '24': '20',
      '25': '15',
      '26': '17',
      '27': '2',
      '28': '26',
      '29': '5',
      '31': '13',
      '32': '8',
      '33': '19',
      '35': '25',
      '41': '14',
      '42': '24',
      '43': '21',
      '50': '12',
      '51': '11',
      '52': '9',
      '53': '7',
    };
    this.estadoCidadeSelecionada.estadoId.id = frontEndToDbMapping[this.estadoCidadeSelecionada.estadoId.id];
    const cidadeNome = this.estadoCidadeSelecionada.name;
    this.cidadeService.findByCidade(cidadeNome).pipe(catchError(() => of(null))
    ).subscribe(resp => {
        if (resp) {
         const cidadeId = this.estadoCidadeSelecionada.id = resp.id;
          localStorage.setItem('cidadeId', cidadeId!);
          this.router.navigate(['/stepThree'])
        } else {
          this.cidadeService.create(this.estadoCidadeSelecionada).subscribe(resp => {
              const cidadeId = resp.id;
              localStorage.setItem('cidadeId', cidadeId!);
              this.router.navigate(['/stepThree'])
            },
          );
        }
      },
    );
  }

  getBuscaEstado(): void {
    this.consultaService.getConsultaEstados().subscribe(
      response => {
        const filtroEstadoId: Estado[] = response.map(estado => ({
          id: estado.id,
          nome: estado.nome
        }));
        this.estadoLista = filtroEstadoId;
      })
  }

  getBuscaCidade(event: any): void {
    const selectedUf = event.value;
    this.consultaService.getConsultaCidades(selectedUf).subscribe(response => {
      const cidadesFiltradas: Cidade[] = response.map(cidade => ({ nome: cidade.nome }));
      this.cidadeLista = cidadesFiltradas;
    });
  }

}
