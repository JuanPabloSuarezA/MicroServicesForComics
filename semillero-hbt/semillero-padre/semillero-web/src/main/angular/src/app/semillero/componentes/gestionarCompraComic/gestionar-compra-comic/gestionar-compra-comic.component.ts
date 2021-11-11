import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ComicDTO } from "src/app/semillero/dto/comic-dto";
import { GestionarComicService } from "src/app/semillero/servicios/gestionar-comic.service";
import { MensajeDTO } from "src/app/semillero/dto/Mensaje-DTO";



@Component({
  selector: "app-gestionar-compra-comic",
  templateUrl: "./gestionar-compra-comic.component.html",
  styleUrls: ["./gestionar-compra-comic.component.css"],
})
//Componente para la compra de un comic
export class GestionarCompraComicComponent implements OnInit {
  
  //Atributo para almacenar el comic enviado
  public comicDTO: ComicDTO;
  //Atributo para gestionar los campos con sus validaciones
  public gestionarComicForm: FormGroup;

  //Atributo para mostrar mensajes de error

  public mostrarItem: boolean;

  //atributo para confirmar el envio de datos de formulario

  public submitted: boolean;

  //atributo para mostrar el mensaje de error del formulario

  public mensajeEjecucion: string;

  // en el construccion se inyectan los servicios
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private gestionComicsService: GestionarComicService
  ) {
    //Se agregan los campos a manejar, controlando la validacion obligatoria de asignar una cantidad
    this.gestionarComicForm = this.fb.group({
      nombre: [null],
      cantidad: [null, Validators.required],
    });
  }

  ngOnInit() {
    console.log("GestionarCompra");
    let comic: any = this.activatedRoute.snapshot.params;
    //Se recibe el comic enviado y se crea el comicDTO con los atributos 
    this.comicDTO = new ComicDTO();
    this.comicDTO.nombre = comic.nombre;
    this.comicDTO.editorial = comic.editorial;
    this.comicDTO.tematicaEnum = comic.tematicaEnum;
    this.comicDTO.coleccion = comic.coleccion;
    this.comicDTO.numeroPaginas = comic.numeroPaginas;
    this.comicDTO.precio = comic.precio;
    this.comicDTO.autores = comic.autores;
    this.comicDTO.color = comic.color;
    this.comicDTO.estadoEnum = comic.estadoEnum;
    this.comicDTO.cantidad = comic.cantidad;
    this.comicDTO.id = comic.id;
    this.submitted = false;
    this.mostrarItem = false;
    //Se asigna el nombre al form para mostrarlo en el label 
    this.f.nombre.setValue(this.comicDTO.nombre);
  }

  //Metodo para comprar un comic
  public comprarComic() {
    this.submitted = true;
    //Si es invalido se retorna
    if (this.gestionarComicForm.invalid) {
      return;
    }

    let cantidad: number = this.gestionarComicForm.controls.cantidad.value;

    //Solo se tiene en cuenta la parte entera
    cantidad = Math.trunc(cantidad);

    //Se valida que la cantidad sean mayor que cero

    if (cantidad <= 0) {
      this.mensajeEjecucion = "Debes ingresar un número mayor a 0";
      this.mostrarItem = true;
      return;
    }

    //Se carga la cantidad indicada

    this.comicDTO.cantidad = cantidad;

    //Se consume el servicio creado para comprar un comic enviando el comic con la nueva cantidad
    this.gestionComicsService.comprarComic(this.comicDTO).subscribe(
      (data) => {
        if (data.exitoso) {
          //Se realiza una redireccion enviando el mensaje de ejecucion en caso de exito
          console.log("exito");
          this.mensajeEjecucion = data.mensajeEjecucion;
          this.irAComponenteGestionarComic(this.mensajeEjecucion);
        } else {
          //Se muestra un mensaje de error por la falla en la compra
          this.mensajeEjecucion = data.mensajeEjecucion;
          this.mostrarItem = true;
          console.log(data.mensajeEjecucion);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public cerrar(): void {
    this.mostrarItem = false;
  }
  public cancelarCompra(): void {
    //Se redirige al componente de gestionar comic con un mensaje vacio
    this.cerrar();
    let mensaje = new MensajeDTO();
    mensaje.mensajeEjecucion = "";
    this.router.navigate(["gestionar-comic", mensaje]);
  }

  public irAComponenteGestionarComic(mensajeEjecucion: string): void {
    //Si el usuario cancela la compra se redirecciona a gestionar comic
    this.cerrar();
    let mensaje = new MensajeDTO();
    mensaje.mensajeEjecucion = mensajeEjecucion;
    this.router.navigate(["gestionar-comic", mensaje]);
  }

  get f() {
    return this.gestionarComicForm.controls;
  }
}
