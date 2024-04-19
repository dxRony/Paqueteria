import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { PaqueteService } from '../../../../services/paquete.service';
import { Paquete } from '../../../../model/paquete';

@Component({
  selector: 'app-procesar-paquete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './procesar-paquete.component.html',
  styleUrl: './procesar-paquete.component.css'
})
export class ProcesarPaqueteComponent {

  constructor(private paqueteService: PaqueteService) {
    this.paquete = new Paquete;
  }

  paquete!: Paquete;

  formData = {
    idPaquete: '',
    tiempo: ''
  }

  form = new FormGroup({
    idPaquete: new FormControl('', Validators.required),
    tiempo: new FormControl('', Validators.required)
  });

  onSubmit() {
    console.log('contenido: ' + this.formData);
  }

  operarPaquete() {
    this.paquete.id = parseInt(this.formData.idPaquete);
    this.paquete.tiempo = parseInt(this.formData.tiempo);

    this.paqueteService.operarPaquete(this.paquete.id, this.paquete.tiempo).
      subscribe(
        (data) => {
          alert('paquete operado');
          this.formData.idPaquete = '';
          this.formData.tiempo = '';
        }, (error) => {
          alert('no se pudo operar el paquete');
        }
      );
  }
}
