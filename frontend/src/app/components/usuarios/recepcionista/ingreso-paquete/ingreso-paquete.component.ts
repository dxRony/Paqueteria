import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { PaqueteService } from '../../../../services/paquete.service';
import { Paquete } from '../../../../model/paquete';

@Component({
  selector: 'app-ingreso-paquete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './ingreso-paquete.component.html',
  styleUrl: './ingreso-paquete.component.css'
})
export class IngresoPaqueteComponent {

  constructor(private paqueteService: PaqueteService) {
    this.paquete = new Paquete();

  }

  paquete!: Paquete;
  paqueteIngresado!: Paquete;

  formData = {
    nitCliente: '',
    idDestino: '',
    peso: ''
  }

  form = new FormGroup({
    nitCliente: new FormControl('', Validators.required),
    idDestino: new FormControl('', Validators.required),
    peso: new FormGroup('', Validators.required)
  });

  onSubmit() {
    console.log('contenido: ' + this.formData);
  }

  ingresarPaquete() {
    this.paquete.nitCliente = parseInt(this.formData.nitCliente);
    this.paquete.idDestino = parseInt(this.formData.idDestino);
    this.paquete.peso = parseInt(this.formData.peso);

    this.paqueteService.crearPaquete(this.paquete).subscribe
      (createdPaquete => {
        if (createdPaquete) {
          this.paqueteIngresado = createdPaquete;
          alert('paquete creado correctamente');
          this.formData.idDestino = '';
          this.formData.nitCliente = '';
          this.formData.peso = '';
        } else {
          alert('error al crear el paquete');
        }
      }, error => {
        alert('error al crear el paquete');
      }
      );
  }
}
