import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComicDTO } from 'src/app/semillero/dto/comic-dto';
import { GestionarComicService } from 'src/app/semillero/servicios/gestionar-comic.service';
@Component({
  selector: 'app-listar-comic',
  templateUrl: './listar-comic.component.html',
  styleUrls: ['./listar-comic.component.css']
})
export class ListarComicComponent implements OnInit {

  public gestionarComicForm : FormGroup;

  public comicDTO : ComicDTO;

  public comicDTOInfo : ComicDTO;

  public nombre : string;

  public listaComicsMayor : Array<String>;
  public listaComicsMenor : Array<String>;
  
  public mostrarItem : boolean;

  public submitted : boolean;

  public mensajeEjecucion : string;

  public idComic: number; 

  // en el constructor se inyectan utilidades, clases o servicios

  constructor(private fb : FormBuilder, private router : Router,
     private gestionComicsService: GestionarComicService) { 
      this.gestionarComicForm = this.fb.group({
        lengthComic : [null, Validators.required],
      });
  }

  ngOnInit() {
    this.submitted = false;
    this.mostrarItem = false;
    this.comicDTO = new ComicDTO();
    this.listaComicsMayor = new Array<String>();
    this.listaComicsMenor = new Array<String>();
    console.log("GESTIONAR COMIC");
    this.idComic = null; 
  }

  public consultarComicsPorTamanioNombre(){

    this.submitted = true;
    if (this.gestionarComicForm.invalid) {
      return;
    }
    let lengthUser : number = this.gestionarComicForm.controls.lengthComic.value;
    this.gestionComicsService.consultarComicsPorTamanioNombre(lengthUser).subscribe(data =>
      {
        if (data.exitoso ) {
          this.listaComicsMayor = data.nombresMayorIgual;
          this.listaComicsMenor = data.nombresMenor; 
          
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

  public cerrar() : void {
    this.mostrarItem = false;
  }

  get f() {
    return this.gestionarComicForm.controls;
  }

}
