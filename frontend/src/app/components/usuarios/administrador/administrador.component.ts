import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CrearRutaComponent } from './crear-ruta/crear-ruta.component';
import { AgregarPuntosComponent } from './agregar-puntos/agregar-puntos.component';
import { DesactivarRutaComponent } from './desactivar-ruta/desactivar-ruta.component';
import { DesactivarUsuarioComponent } from './desactivar-usuario/desactivar-usuario.component';
import { ModificarPrecioComponent } from './modificar-precio/modificar-precio.component';
import { QuitarPuntosControlComponent } from './quitar-puntos-control/quitar-puntos-control.component';
import { Reporte1Component } from './reportes/reporte1/reporte1.component';
import { Reporte2Component } from './reportes/reporte2/reporte2.component';
import { Reporte3Component } from './reportes/reporte3/reporte3.component';
import { Reporte4Component } from './reportes/reporte4/reporte4.component';
import { EmpleadoService } from '../../../services/empleado.service';

@Component({
  selector: 'app-administrador',
  standalone: true,
  imports: [CrearRutaComponent, AgregarPuntosComponent, QuitarPuntosControlComponent, DesactivarRutaComponent
    , DesactivarUsuarioComponent, ModificarPrecioComponent, Reporte1Component, Reporte2Component, Reporte3Component, Reporte4Component],
  templateUrl: './administrador.component.html',
  styleUrl: './administrador.component.css'
})
export class AdministradorComponent {


  @Input() isLoggedIn: any;
  @Output() estadoCambiado: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() empleadoLogeado: any;
  valorSeleccionado1 = 0;
  valorSeleccionado2 = 0;


  getSelect1(event: any): void {
    this.valorSeleccionado1 = parseInt(event.target.value);
  }

  getSelect2(event: any): void {
    this.valorSeleccionado2 = parseInt(event.target.value);
  }

  cerrarSesion() {
    this.isLoggedIn = false;
    this.estadoCambiado.emit(this.isLoggedIn);
  }

}
