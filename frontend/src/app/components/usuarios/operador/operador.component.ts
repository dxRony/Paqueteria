import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ProcesarPaqueteComponent } from './procesar-paquete/procesar-paquete.component';

@Component({
  selector: 'app-operador',
  standalone: true,
  imports: [ProcesarPaqueteComponent],
  templateUrl: './operador.component.html',
  styleUrl: './operador.component.css'
})

export class OperadorComponent {


  @Input() isLoggedIn: any;
  @Output() estadoCambiado: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() empleadoLogeado: any;
  valorSeleccionado=0;

  getSelect(event : any):void{
    this.valorSeleccionado = parseInt(event.target.value);
  }

  cerrarSesion() {
    this.isLoggedIn = false;
    this.estadoCambiado.emit(this.isLoggedIn);
  }

}
