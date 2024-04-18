import { Component } from '@angular/core';
import { Ruta } from '../../../../model/ruta';
import { EmpleadoService } from '../../../../services/empleado.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RutaService } from '../../../../services/ruta.service';

@Component({
  selector: 'app-crear-ruta',
  standalone: true,
  imports: [],
  templateUrl: './crear-ruta.component.html',
  styleUrl: './crear-ruta.component.css'
})
export class CrearRutaComponent {

  constructor(private rutaService: RutaService) {
    this.ruta = new Ruta();
   }

  ruta !: Ruta;
  formData = {
    idDestino: ''
  };

  form = new FormGroup({
    idDestino: new FormControl('', Validators.required)
  });

  onSubmit() {
    console.log('destino: ' + this.formData);
  }

  crearRuta() {
    console.log('entrando a crearRuta()');
    this.ruta.idDestino = parseInt(this.formData.idDestino);
    console.log('id del destino:' + this.ruta.idDestino);
    this.rutaService.crearRuta(this.ruta).
    subscribe( rutaCreada => {
      if(rutaCreada){
        console.log('ruta creada');

      } else {
        console.log('error al crear ruta');
      }
    }, error => {
      console.log('no se creo la ruta')
    } 
    );
  
  }

}
