import { Component, OnInit } from "@angular/core";
import { ComicDTO } from "src/app/semillero/dto/Comic-DTO";

@Component({
  selector: "crear-persona",
  templateUrl: "./crear-persona.component.html",
  styleUrls: ["./crear-persona.component.css"],
})
export class CrearPersonaComponent implements OnInit {
  public nombreInstructor: string = "pepito";

  public listaComics: Array<ComicDTO>;
  public urlImagen: string;

  constructor() {}

  ngOnInit() {
    this.urlImagen =
      "https://www.elempleo.com/sitios-empresariales/colombia/heinsohn-business-technology/img/elempleo-02.jpg";
    this.listaComics = new Array<ComicDTO>();

    this.nombreInstructor = "Diego Alvarez";
    let edadInstructor: number = 33;
    if (edadInstructor < 33) {
      let resultadoCalculo = 12;
      let resultado = edadInstructor + resultadoCalculo;
    }

    let concatenacion = "";

    for (let index = 0; index < this.nombreInstructor.length; index++) {
      //Las constantes se establecen en un archivo parametrizado en BD
      let element = this.nombreInstructor[index];
      concatenacion += element;
    }
    this.nombreInstructor = this.obtenerNombre();

    this.simularCrearComic();
  }

  public simularCrearComic(): void {
    let comic: any = {
      id: 1,
      nombre: "Superman",
      tematica: "AVENTURAS",
      precio: 200,
    };
    let comic2: any = {
      id: 2,
      nombre: "Dr. Strange",
      tematica: "AVENTURAS",
      precio: 500,
    };
    let comic3 = new ComicDTO();
    comic3.id = 3;
    comic3.nombre = "chapulin";
    comic3.tematicaEnum = "COMEDIA";
    comic3.precio = 200;
    this.listaComics.push(comic);
    this.listaComics.push(comic2);
    this.listaComics.push(comic3);
    this.listaComics.forEach((comic) => {
      console.log(
        "Comic con nombre: " + comic.nombre + " con precio de: " + comic.precio
      );
    });
  }

  public obtenerNombre(): string {
    this.nombreInstructor = "shakira"; //Variable global
    let nombreInstructor = "juanes"; //Variable local
    let apellidoInstructor = "Barrera";

    return nombreInstructor + apellidoInstructor;
  }
}
