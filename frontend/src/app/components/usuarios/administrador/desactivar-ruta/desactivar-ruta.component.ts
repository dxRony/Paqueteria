import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';
import { RutaService } from '../../../../services/ruta.service';
import { Ruta } from '../../../../model/ruta';

@Component({
  selector: 'app-desactivar-ruta',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './desactivar-ruta.component.html',
  styleUrl: './desactivar-ruta.component.css'
})
export class DesactivarRutaComponent {

  constructor(private rutaService:RutaService){
    this.ruta = new Ruta();

    
  };

  ruta!: Ruta;

  formData ={
    idRuta :''
  }

  form = new FormGroup({
    idRuta : new FormControl('',Validators.required)
  });

  onSubmit(){
    console.log('contenido : ' + this.formData);
  }

  desactivarRuta(){
    console.log('entrando a desactivarRuta()');

    this.ruta.id = parseInt(this.formData.idRuta);

    console.log('ruta a desactivar: ' + this.ruta.id);

    this.rutaService.desactivarRuta(this.ruta.id).
    subscribe(
      (data) => {
        alert('ruta desactivada');
        this.formData.idRuta ='';
      },
      (error) => {
        alert('error al desactivar ruta');
      }
    );
  }


}
