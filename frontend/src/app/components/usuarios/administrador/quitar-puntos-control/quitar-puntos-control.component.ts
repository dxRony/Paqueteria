import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { PuntoDeControl } from '../../../../model/puntoDeControl';
import { PuntoDeControlService } from '../../../../services/PuntoDeControl.service';

@Component({
  selector: 'app-quitar-puntos-control',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './quitar-puntos-control.component.html',
  styleUrl: './quitar-puntos-control.component.css'
})
export class QuitarPuntosControlComponent {

  constructor(private puntoService: PuntoDeControlService) {
    this.puntoDeControl = new PuntoDeControl();
  }
  onSubmit() {
    console.log('contenido: ' + this.formData);
  }

  puntoDeControl!: PuntoDeControl;

  formData = {
    idRuta: '',
    idPuntoDeControl: ''
  }

  form = new FormGroup({
    idRuta: new FormControl('', Validators.required),
    idPuntoDeControl: new FormControl('', Validators.required)
  });


  quitarPuntoDeControl() {
    console.log('entrando a quitarPunto()');
    this.puntoDeControl.idRuta = parseInt(this.formData.idRuta);
    this.puntoDeControl.id = parseInt(this.formData.idPuntoDeControl);
    console.log('ruta' + this.puntoDeControl.idRuta);
    console.log('punto de control' + this.puntoDeControl.id);

    this.puntoService.eliminarPuntoDeControl(this.puntoDeControl.idRuta, this.puntoDeControl.id).subscribe(
      (data) => {
        alert('punto eliminado');
        this.formData.idPuntoDeControl = '';
        this.formData.idRuta = '';
      },
      (error) => {
        alert('error al eliminar punto');
      }
    );
  }
}
