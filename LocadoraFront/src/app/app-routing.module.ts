import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { InicioComponent } from './telas/inicio/inicio.component';
import { LoginComponent } from './telas/login/login.component';
import { StepOneComponent } from './telas/registro/step-one/step-one.component';
import { StepTwoComponent } from './telas/registro/step-two/step-two.component';
import { StepThreeComponent } from './telas/registro/step-three/step-three.component';

const routes: Routes = [
  {path: '', component: HomeComponent },
  {path: 'inicio', component: InicioComponent },
  {path: 'login', component: LoginComponent },
  {path: 'stepOne', component: StepOneComponent },
  {path: 'stepTwo', component: StepTwoComponent },
  {path: 'stepThree', component:  StepThreeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
