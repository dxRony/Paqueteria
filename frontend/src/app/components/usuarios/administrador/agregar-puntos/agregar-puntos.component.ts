import { Component } from '@angular/core';
import { PuntoDeControlService } from '../../../../services/PuntoDeControl.service';
import { PuntoDeControl } from '../../../../model/puntoDeControl';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-agregar-puntos',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './agregar-puntos.component.html',
  styleUrl: './agregar-puntos.component.css'
})
export class AgregarPuntosComponent {

  constructor(private puntoService: PuntoDeControlService) {
    this.puntoDeControl = new PuntoDeControl();
  };

  puntoDeControl !: PuntoDeControl;
  puntoDeControlCreado !: PuntoDeControl;

  formData = {
    idRuta: '',
    idEmpleado: '',
    tarifaOperacion: '',
    cantidadMaxima: ''
  }

  form = new FormGroup({
    idRuta: new FormControl('', Validators.required),
    idEmpleado: new FormControl('', Validators.required),
    tarifaOperacion: new FormControl(''),
    cantidadMaxima: new FormControl('', Validators.required)
  });

  onSubmit() {
    console.log('contenido: ' + this.formData);
  }

  crearPuntoDeControl() {
    console.log('entrando a crearPunto()');
    this.puntoDeControl.idRuta = parseInt(this.formData.idRuta);
    this.puntoDeControl.idEmpleado = parseInt(this.formData.idEmpleado);
    if (this.formData.tarifaOperacion == '') {
      console.log('usando tarifa global');
      this.puntoDeControl.tarifaOperacion = 0;
    } else {
      this.puntoDeControl.tarifaOperacion = parseInt(this.formData.tarifaOperacion);
    }
    this.puntoDeControl.cantidadMaximaPaquetes = parseInt(this.formData.cantidadMaxima);
    console.log('ruta:' + this.puntoDeControl.idRuta);
    console.log('empleado:' + this.puntoDeControl.idEmpleado);
    console.log('tarifa:' + this.puntoDeControl.tarifaOperacion);

    this.puntoService.crearPuntoDeControl(this.puntoDeControl).
      subscribe(createdPunto => {
        if (createdPunto) {
          this.puntoDeControlCreado = createdPunto;
          alert('Punto de control creado correctamente');
          this.formData.idRuta = '';
          this.formData.idEmpleado = '';
          this.formData.tarifaOperacion = '';
          this.formData.cantidadMaxima = '';
        } else {
          alert('error al crear el punto de control');
        }
      }, error => {
        alert('error al crear el punto de control');
      }
      );
  }
}
