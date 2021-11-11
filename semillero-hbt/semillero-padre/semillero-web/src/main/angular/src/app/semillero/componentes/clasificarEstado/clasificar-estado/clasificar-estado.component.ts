import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from 'src/app/semillero/servicios/gestionar-comic.service';


@Component({
  selector: 'app-clasificar-estado',
  templateUrl: './clasificar-estado.component.html',
  styleUrls: ['./clasificar-estado.component.css']
})
export class ClasificarEstadoComponent implements OnInit {

  public gestionarComicForm : FormGroup;

  public comicDTO : ComicDTO;

  public comicDTOInfo : ComicDTO;

  public nombre : string;

  public listaComicsActivos : Array<ComicDTO>;
  public listaComicsInactivos : Array<ComicDTO>;
  
  public mostrarItem : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  public idComic: number; 

  // en el constructor se inyectan utilidades, clases o servicios

  constructor(private fb : FormBuilder, private router : Router,
     private gestionComicsService: GestionarComicService) { 
    this.gestionarComicForm = this.fb.group({
      estadoEnum : ["ACTIVO"]
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.mostrarItem = false;
    this.comicDTO = new ComicDTO();
    this.listaComicsActivos = new Array<ComicDTO>();
    this.listaComicsInactivos = new Array<ComicDTO>();
    console.log("CLASIFICAR ESTADO");

  }

  public consultarComicsPorTamanioNombre(){

    this.submitted = true;
    if (this.gestionarComicForm.invalid) {
      return;
    }
    let estadoEnum : string = this.gestionarComicForm.controls.estadoEnum.value;
    this.gestionComicsService.consultarComicsPorEstado(estadoEnum).subscribe(data =>
      {
        if (data.exitoso ) {
          this.listaComicsActivos = data.comicsActivos;
          
          this.submitted = false; 
        }else{
          this.mensajeEjecucion = data.mensajeEjecucion;
          console.log(data.mensajeEjecucion);
        }
        
      }, error => {
        console.log(error);
        
      }
    )
  }

  public crearComic() : void {
    this.submitted = true;
    if (this.gestionarComicForm.invalid) {
      return;
    }

    this.comicDTO = new ComicDTO();
    this.comicDTO.nombre = this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.editorial = this.gestionarComicForm.controls.editorial.value;
    this.comicDTO.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
    this.comicDTO.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTO.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTO.precio = this.gestionarComicForm.controls.precio.value;
    this.comicDTO.autores = this.gestionarComicForm.controls.autores.value;
    this.comicDTO.color = this.gestionarComicForm.controls.color.value;
    this.comicDTO.estadoEnum = "ACTIVO";
    this.comicDTO.cantidad = this.gestionarComicForm.controls.cantidad.value;

  }

  public cerrar() : void {
    this.mostrarItem = false;
  }

  get f() {
    return this.gestionarComicForm.controls;
  }


}
