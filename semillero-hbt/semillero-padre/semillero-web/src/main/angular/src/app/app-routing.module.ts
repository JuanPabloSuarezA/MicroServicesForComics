import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClasificarEstadoComponent } from './semillero/componentes/clasificarEstado/clasificar-estado/clasificar-estado.component';
import { GestionarComicComponent } from './semillero/componentes/gestionarComic/gestionar-comic/gestionar-comic.component';
import { GestionarCompraComicComponent } from './semillero/componentes/gestionarCompraComic/gestionar-compra-comic/gestionar-compra-comic.component';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { ListarComicComponent } from './semillero/componentes/listarComic/listar-comic/listar-comic.component';
// se agregan las rutas para el enrutamiento de los componentes creados
const routes: Routes = [
  { path: 'bienvenida', component: BienvenidaComponent, data : null },
  { path: 'gestionar-comic', component: GestionarComicComponent, data : null },
  { path: 'listar-comic', component: ListarComicComponent, data : null },
  { path:  'app-gestionar-compra-comic', component: GestionarCompraComicComponent, data : null }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
