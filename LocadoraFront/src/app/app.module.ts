import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';

import { AuthService } from './service/auth.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { InicioComponent } from './telas/inicio/inicio.component';
import { LoginComponent } from './telas/login/login.component';
import { StepOneComponent } from './telas/registro/step-one/step-one.component';
import { StepTwoComponent } from './telas/registro/step-two/step-two.component';
import { StepThreeComponent } from './telas/registro/step-three/step-three.component';
import { ValidandoCepDirective } from './validando-cep.directive';
import { HomeUsuComponent } from './telas/usuario/home-usu/home-usu.component';
import { HeaderComponent } from './telas/usuario/templates/header/header.component';
import { FooterComponent } from './telas/usuario/templates/footer/footer.component';
import { FilmeComponent } from './telas/usuario/filme/filme.component';
import { FilmeinfoComponent } from './telas/usuario/filme/filmeinfo/filmeinfo.component';
import { ReservaComponent } from './telas/usuario/reserva/reserva.component';
import { CreateComponent } from './telas/usuario/reserva/create/create.component';
import { ReservainfoComponent } from './telas/usuario/reserva/reservainfo/reservainfo.component';
import { CategoriaReadComponent } from './telas/usuario/categoria/categoria-read/categoria-read.component';
import { CategoriaFilmeComponent } from './telas/usuario/categoria/categoria-filme/categoria-filme.component';
import { SidebarComponent } from './telas/adm/template/sidebar/sidebar.component';
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
import { PerfilComponent } from './telas/perfil/perfil.component';

import { MessagesModule } from 'primeng/messages';
import { PasswordModule } from 'primeng/password';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';
import { InputNumberModule } from 'primeng/inputnumber';
import { SidebarModule } from 'primeng/sidebar';
import { TooltipModule } from 'primeng/tooltip';
import { ProgressSpinnerModule } from 'primeng/progressspinner';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    InicioComponent,
    LoginComponent,
    StepOneComponent,
    StepTwoComponent,
    StepThreeComponent,
    ValidandoCepDirective,
    HomeUsuComponent,
    HeaderComponent,
    FooterComponent,
    FilmeComponent,
    FilmeinfoComponent,
    ReservaComponent,
    CreateComponent,
    ReservainfoComponent,
    CategoriaReadComponent,
    CategoriaFilmeComponent,
    SidebarComponent,
    HomeAdmComponent,
    AdministracaoComponent,
    UserupComponent,
    FilmesComponent,
    FilmeupComponent,
    EstoqueComponent,
    EstoquecreComponent,
    FilmescreComponent,
    CategoriasComponent,
    CategoriaupComponent,
    CategoriadelComponent,
    LocacaoComponent,
    LocacaocriComponent,
    ReservasComponent,
    MultaComponent,
    PerfilComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MessagesModule,
    PasswordModule,
    FormsModule,
    HttpClientModule,
    InputTextModule,
    DropdownModule,
    TableModule,
    CalendarModule,
    InputNumberModule,
    SidebarModule,
    TooltipModule,
    ProgressSpinnerModule

  ],
  providers: [
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass:  AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
