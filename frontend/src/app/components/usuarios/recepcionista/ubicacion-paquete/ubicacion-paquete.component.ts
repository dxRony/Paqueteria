import { Component } from '@angular/core';
import { FormGroup, FormsModule, Validators } from '@angular/forms';
import { PaqueteService } from '../../../../services/paquete.service';
import { Paquete } from '../../../../model/paquete';

@Component({
  selector: 'app-ubicacion-paquete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './ubicacion-paquete.component.html',
  styleUrl: './ubicacion-paquete.component.css'
})
export class UbicacionPaqueteComponent {

  constructor(private paqueteService: PaqueteService) {
    this.paquete = new Paquete();

  }
  paquete!: Paquete;
  paqueteBuscado!: Paquete;

  formData = {
    ipPaquete: ''
  }

  form = new FormGroup({
    idPaquete: new FormGroup('', Validators.required)
  });

  onSubmit() {
    console.log('contenido' + this.formData);
  }

  obtenerPaquete() {
    console.log('entrando a obtenerPaquete()')
    this.paquete.id = parseInt(this.formData.ipPaquete);

    console.log('id: ' + this.paquete.id)
    this.paqueteService.getPaqueteById(this.paquete.id).subscribe
      (paquete => {
        if (paquete) {
          this.paqueteBuscado = paquete;
          this.formData.ipPaquete = '';
          alert('el paquete con id: ' + this.paqueteBuscado.id + ', se encuentra en el punto de control: ' + this.paqueteBuscado.idPuntoDeControlActual + ', de la ruta: ' + this.paqueteBuscado.idRuta);

        } else {
          alert('error al buscar el paquete- else');
        }
      }, error => {
        alert('error al buscar el paquete- error');
      }
      );
  }
}
