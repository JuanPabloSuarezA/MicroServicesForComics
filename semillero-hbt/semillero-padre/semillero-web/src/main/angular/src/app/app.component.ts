import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private miObjeto : any;
  constructor(private router : Router) {
    
  }  

  ngOnInit(): void {
    // luego de la renderizacion se redirecciona al componente de bienvenida en home
    this.router.navigate(['bienvenida']);    
     
  }
}
