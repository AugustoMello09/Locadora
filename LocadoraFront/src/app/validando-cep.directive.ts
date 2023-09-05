import { Directive } from '@angular/core';
import { AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors } from '@angular/forms';
import { ConsultaCepService } from './service/consulta-cep.service';
import { Observable, map } from 'rxjs';

@Directive({
  selector: '[appValidandoCep]',
  providers: [{
    provide: NG_ASYNC_VALIDATORS,
    useExisting: ValidandoCepDirective,
    multi: true
  }]
})
export class ValidandoCepDirective implements AsyncValidator{

  constructor(private consultaCep: ConsultaCepService) { }
  
  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    const cep = control.value;
    return this.consultaCep.getConsultaCep(cep).pipe(map(
      (result: any) => result.erro ? {'validadorCep': true} : null
    ))
  }

}
