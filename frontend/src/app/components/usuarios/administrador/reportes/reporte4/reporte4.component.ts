import { Component } from '@angular/core';
import { RutaService } from '../../../../../services/ruta.service';
import { Ruta } from '../../../../../model/ruta';

@Component({
  selector: 'app-reporte4',
  standalone: true,
  imports: [],
  templateUrl: './reporte4.component.html',
  styleUrl: './reporte4.component.css'
})
export class Reporte4Component {

  constructor(private rutaService:RutaService){

  }

  rutas :any;
  
  ngOnInit(){
    this.getReporte4();
  }

  getReporte4(){
    this.rutaService.getReporte4().subscribe(
      (rutas:Ruta[]) => {
        this.rutas= rutas;
        console.log('nice');
      }, (error) => {
        console.log('not found :c');
      }
    );
  }
}
