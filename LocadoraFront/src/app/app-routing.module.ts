import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { InicioComponent } from './telas/inicio/inicio.component';
import { LoginComponent } from './telas/login/login.component';
import { StepOneComponent } from './telas/registro/step-one/step-one.component';
import { StepTwoComponent } from './telas/registro/step-two/step-two.component';
import { StepThreeComponent } from './telas/registro/step-three/step-three.component';
import { HomeUsuComponent } from './telas/usuario/home-usu/home-usu.component';
import { FilmeComponent } from './telas/usuario/filme/filme.component';
import { FilmeinfoComponent } from './telas/usuario/filme/filmeinfo/filmeinfo.component';
import { ReservaComponent } from './telas/usuario/reserva/reserva.component';
import { ReservainfoComponent } from './telas/usuario/reserva/reservainfo/reservainfo.component';
import { CreateComponent } from './telas/usuario/reserva/create/create.component';
import { CategoriaReadComponent } from './telas/usuario/categoria/categoria-read/categoria-read.component';
import { CategoriaFilmeComponent } from './telas/usuario/categoria/categoria-filme/categoria-filme.component';


import { UserGuard } from './user.guard';

const routes: Routes = [
  {path: '', component: HomeComponent },
  {path: 'inicio', component: InicioComponent },
  {path: 'login', component: LoginComponent },
  {path: 'stepOne', component: StepOneComponent },
  {path: 'stepTwo', component: StepTwoComponent },
  {path: 'stepThree', component:  StepThreeComponent },
  
  { path: 'lobby', component: HomeUsuComponent, canActivate : [UserGuard] },
  { path: 'lobbyFilme', component: FilmeComponent, canActivate : [UserGuard] },
  { path: 'informacao/:id', component: FilmeinfoComponent, canActivate : [UserGuard] },
  { path: 'reservas', component: ReservaComponent, canActivate : [UserGuard] },
  { path: 'reserva/:id', component: ReservainfoComponent, canActivate : [UserGuard] },
  { path: 'reservar/:idEstoque', component: CreateComponent, canActivate : [UserGuard] },
  { path: 'lobbyCategorias', component: CategoriaReadComponent, canActivate : [UserGuard] },
  { path: 'categoriaByFilmes/:id', component: CategoriaFilmeComponent, canActivate : [UserGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
