import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MensajeDTO } from '../../dto/Mensaje-DTO';

/**
 * @description Componente menu, el cual contiene la logica para direccionar a los modulos
 * desarrollados
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
/**
 * @description componente para gestionar enrutamientos del menu
 * @author Juan <jpsa4000@hotmail.com>
 * @see CU1098 Gestionar Comic 
 */
@Component({
  selector: 'home-page',
  templateUrl: './menu-component.html',
})
export class MenuComponent implements OnInit {

  /**
   * Constructor de la clase
   * @param router permite direccionar a otros componentes
   * se inyecta el router para acceder a metodos como navigate
   * para navegar a otra vista 
   */
  constructor(private router: Router) {

  }

  /**
   * Evento angular que se ejecuta al iniciar el componente
   */
  ngOnInit(): void {

  }

  /**
   * @description Metodo encargado de direccionar al componente de gestionar comic
   * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
   */
  public navegarGestionarComic(): void {
    //Se envia un mensaje vacio en la navegacion a gestionar comic para no mostrar el componente alert
    let mensaje = new MensajeDTO();
    mensaje.mensajeEjecucion = "";
    this.router.navigate(['gestionar-comic' , mensaje]);
  }

  /**
   * @description Metodo encargado de direccionar al componente de gestionar comic
   * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
   */
  public navegarHome(): void {
    this.router.navigate(['bienvenida']);
  }

  public navegarGestionarCompra(): void {
    //WIP
  }
}