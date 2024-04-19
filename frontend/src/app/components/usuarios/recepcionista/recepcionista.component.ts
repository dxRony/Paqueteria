import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IngresoPaqueteComponent } from './ingreso-paquete/ingreso-paquete.component';
import { RetiroPaqueteComponent } from './retiro-paquete/retiro-paquete.component';
import { ListadoARecogerComponent } from './listado-arecoger/listado-arecoger.component';
import { UbicacionPaqueteComponent } from './ubicacion-paquete/ubicacion-paquete.component';
import { CrearClienteComponent } from './crear-cliente/crear-cliente.component';

@Component({
  selector: 'app-recepcionista',
  standalone: true,
  imports: [IngresoPaqueteComponent, RetiroPaqueteComponent, ListadoARecogerComponent, UbicacionPaqueteComponent,
    CrearClienteComponent],
  templateUrl: './recepcionista.component.html',
  styleUrl: './recepcionista.component.css'
})
export class RecepcionistaComponent {

  @Input() isLoggedIn: any;
  @Output() estadoCambiado: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() empleadoLogeado: any;
  valorSeleccionado = 0;

  getSelect(event: any): void {
    this.valorSeleccionado = parseInt(event.target.value)
  }
  cerrarSesion() {
    this.isLoggedIn = false;
    this.estadoCambiado.emit(this.isLoggedIn);
  }
}
