import { Component } from '@angular/core';
import { Ruta } from '../../../../model/ruta';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RutaService } from '../../../../services/ruta.service';

@Component({
  selector: 'app-crear-ruta',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './crear-ruta.component.html',
  styleUrl: './crear-ruta.component.css'
})
export class CrearRutaComponent {

  constructor(private rutaService: RutaService) {
    this.ruta = new Ruta();
  };

  ruta !: Ruta;
  rutaCreada !: Ruta;

  formData = {
    idDestino: ''
  };

  form = new FormGroup({
    idDestino: new FormControl('', Validators.required)
  });

  onSubmit() {
    console.log('contenido: ' + this.formData);
  }

  crearRuta() {
    console.log('entrando a crearRuta()');

    this.ruta.idDestino = parseInt(this.formData.idDestino);
    console.log('id del destino:' + this.ruta.idDestino);

    this.rutaService.crearRuta(this.ruta).
      subscribe(createdRuta => {
        if (createdRuta) {
          this.rutaCreada = createdRuta;
          console.log('ruta creada');
          alert('Ruta creada correctamente');
          this.formData.idDestino = '';
        } else {
          console.log('error al crear ruta');
          alert('error al crear ruta');
        }
      }, error => {
        console.log('idk');
        alert('la ruta no fue creada, corrobore la informacion ingresada');
      }
      );
  }
}
