import { Component } from '@angular/core';
import { SistemaService } from '../../../../services/sistema.service';
import { Sistema } from '../../../../model/sistema';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { PuntoDeControl } from '../../../../model/puntoDeControl';
import { PuntoDeControlService } from '../../../../services/PuntoDeControl.service';

@Component({
  selector: 'app-modificar-precio',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './modificar-precio.component.html',
  styleUrl: './modificar-precio.component.css'
})
export class ModificarPrecioComponent {

  constructor(private sistemaService: SistemaService, private puntoService: PuntoDeControlService) {
    this.sistema = new Sistema();
    this.puntoDeControl = new PuntoDeControl();
  }

  sistema!: Sistema;
  puntoDeControl!: PuntoDeControl;

  formData1 = {
    tarifaOperacionGlobal: ''
  }

  formData2 = {
    precioPLibra: ''
  }

  formData3 = {
    idPuntoDeControl: '',
    tarifaOperacion: ''
  }

  form1 = new FormGroup({
    tarifaOperacionGlobal: new FormControl('', Validators.required)
  });

  form2 = new FormGroup({
    precioPLibra: new FormControl('', Validators.required)
  });

  form3 = new FormGroup({
    idPuntoDeControl: new FormControl('', Validators.required),
    tarifaOperacion: new FormControl('', Validators.required)
  });

  onSubmit1() {
    console.log('contenido' + this.formData1.tarifaOperacionGlobal);
  }

  onSubmit2() {
    console.log('contenido' + this.formData2.precioPLibra);
  }

  onSubmit3() {
    console.log('contenido' + this.formData3.idPuntoDeControl, this.formData3.tarifaOperacion);
  }

  cambiarTarifaOperacionGlobal() {
    console.log('entrando a tarifa global');

    this.sistema.tarifaOperacionGlobal = parseInt(this.formData1.tarifaOperacionGlobal);

    this.sistemaService.actualizarSistema(this.sistema).
      subscribe(
        (data) => {
          alert('Tarifa de operacion global del sistema cambiada');
          this.formData1.tarifaOperacionGlobal = '';
          this.formData2.precioPLibra = '';
          this.formData3.idPuntoDeControl = '';
          this.formData3.tarifaOperacion = '';
          this.sistema.tarifaOperacionGlobal = 0;
        }, (error) => {
          alert('error al cambiar la tarifa...');
        }
      );
  }

  cambiarPrecioPLibra() {
    console.log('entrando a precio x libra');
    this.sistema.precioPLibra = parseInt(this.formData2.precioPLibra);

    this.sistemaService.actualizarSistema(this.sistema).
      subscribe(
        (data) => {
          alert('Precio por libra del sistema actualizado');
          this.formData1.tarifaOperacionGlobal = '';
          this.formData2.precioPLibra = '';
          this.formData3.idPuntoDeControl = '';
          this.formData3.tarifaOperacion = '';
          this.sistema.precioPLibra = 0;
        }, (error) => {
          alert('error al cambiar precio  por libra...');
        }
      );
  }

  cambiarTarifaOperacion() {
    console.log('entrado a tarifa local');
    this.puntoDeControl.id = parseInt(this.formData3.idPuntoDeControl);
    this.puntoDeControl.tarifaOperacion = parseInt(this.formData3.tarifaOperacion);

    this.puntoService.actualizarPuntoDeControl(this.puntoDeControl).
      subscribe
      (
        (data) => {
          alert('Tarifa de operacion actualizada');
          this.formData1.tarifaOperacionGlobal = '';
          this.formData2.precioPLibra = '';
          this.formData3.idPuntoDeControl = '';
          this.formData3.tarifaOperacion = '';
        }, (error) => {
          alert('error al cambiar la tarifa de operacion');
        }
      );
  }
}
