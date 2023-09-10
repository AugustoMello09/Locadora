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
import { HomeAdmComponent } from './telas/adm/home-adm/home-adm.component';
import { AdministracaoComponent } from './telas/adm/administracao/administracao.component';
import { UserupComponent } from './telas/adm/administracao/userup/userup.component';
import { FilmesComponent } from './telas/adm/filmes/filmes.component';
import { FilmeupComponent } from './telas/adm/filmes/filmeup/filmeup.component';
import { EstoqueComponent } from './telas/adm/estoque/estoque.component';
import { EstoquecreComponent } from './telas/adm/estoque/estoquecre/estoquecre.component';
import { FilmescreComponent } from './telas/adm/filmes/filmescre/filmescre.component';
import { CategoriasComponent } from './telas/adm/categorias/categorias.component';
import { CategoriaupComponent } from './telas/adm/categorias/categoriaup/categoriaup.component';
import { CategoriadelComponent } from './telas/adm/categorias/categoriadel/categoriadel.component';
import { LocacaoComponent } from './telas/adm/locacao/locacao.component';
import { LocacaocriComponent } from './telas/adm/locacao/locacaocri/locacaocri.component';
import { ReservasComponent } from './telas/adm/reservas/reservas.component';
import { MultaComponent } from './telas/adm/multa/multa.component';


import { UserGuard } from './user.guard';
import { AdmGuard } from './adm.guard';


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
  
  { path: 'homeAdm', component: HomeAdmComponent, canActivate : [AdmGuard] },
  { path: 'usuarios', component: AdministracaoComponent , canActivate : [AdmGuard] },
  { path: 'cargo/:id', component:  UserupComponent , canActivate : [AdmGuard] },
  { path: 'filmes', component:  FilmesComponent , canActivate : [AdmGuard] },
  { path: 'filmesup/:id', component:  FilmeupComponent , canActivate : [AdmGuard] },
  { path: 'estoque', component:  EstoqueComponent , canActivate : [AdmGuard] },
  { path: 'estoquecre', component:  EstoquecreComponent , canActivate : [AdmGuard] },
  { path: 'filmescre/:id', component:  FilmescreComponent , canActivate : [AdmGuard] },
  { path: 'categorias', component:  CategoriasComponent , canActivate : [AdmGuard] },
  { path: 'categoriasup/:id', component:  CategoriaupComponent  , canActivate : [AdmGuard] },
  { path: 'categoriasdel/:id', component:  CategoriadelComponent , canActivate : [AdmGuard] },
  { path: 'locacao', component:  LocacaoComponent , canActivate : [AdmGuard] },
  { path: 'locacaocri', component:  LocacaocriComponent , canActivate : [AdmGuard] },
  { path: 'reservasAdm', component:  ReservasComponent , canActivate : [AdmGuard] },
  { path: 'multas', component:  MultaComponent , canActivate : [AdmGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
