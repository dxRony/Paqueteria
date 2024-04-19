import { Component } from '@angular/core';
import { FormGroup, FormsModule, Validators } from '@angular/forms';
import { PaqueteService } from '../../../../services/paquete.service';
import { Paquete } from '../../../../model/paquete';

@Component({
  selector: 'app-retiro-paquete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './retiro-paquete.component.html',
  styleUrl: './retiro-paquete.component.css'
})
export class RetiroPaqueteComponent {

  constructor(private paqueteService: PaqueteService) {
    this.paquete = new Paquete();
  }
  paquete!: Paquete;

  formData = {
    idPaquete: '',
    fecha: ''
  }

  form = new FormGroup({
    idPaquete: new FormGroup('', Validators.required),
    fecha: new FormGroup('', Validators.required)
  });

  onSubmit() {
    console.log('contenido' + this.formData);
  }

  retirarPaquete() {
    const idPaquete = parseInt(this.formData.idPaquete);
    const fecha = this.formData.fecha;
    console.log(fecha);

    this.paqueteService.retirarPaquete(idPaquete, fecha).subscribe(
      (data) => {
        alert('paquete retirado');
        this.formData.fecha = '';
        this.formData.idPaquete = '';
      }, error => {
        alert('error al retirar el paquete');
      }
    );
  }
}
