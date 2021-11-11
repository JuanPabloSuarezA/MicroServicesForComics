import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule} from '@angular/http';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { APP_BASE_HREF } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MenuComponent } from './semillero/componentes/menu/menu-component';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { CrearPersonaComponent } from './semillero/componentes/crearPersona/crear-persona/crear-persona.component';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic/gestionar-comic.component';
import { ListarComicComponent } from './semillero/componentes/listarComic/listar-comic/listar-comic.component';
import { ClasificarEstadoComponent } from './semillero/componentes/clasificarEstado/clasificar-estado/clasificar-estado.component';
import { GestionarCompraComicComponent } from './semillero/componentes/gestionarCompraComic/gestionar-compra-comic/gestionar-compra-comic.component';

// DTOs
// import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic';
// export { ComicDTO } from './semillero/dto/comic.dto';
// export { ResultadoDTO } from './semillero/dto/resultado.dto';


//Se agregan los componentes creados 
@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    BienvenidaComponent,
    CrearPersonaComponent,
    GestionarComicComponent,
    ListarComicComponent,
    ClasificarEstadoComponent,
    GestionarCompraComicComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
  	{ provide: APP_BASE_HREF, useValue: '/SemilleroHBT' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { 

}
