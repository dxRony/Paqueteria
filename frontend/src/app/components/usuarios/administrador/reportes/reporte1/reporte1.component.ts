import { Component } from '@angular/core';
import { RutaService } from '../../../../../services/ruta.service';
import { Ruta } from '../../../../../model/ruta';

@Component({
  selector: 'app-reporte1',
  standalone: true,
  imports: [],
  templateUrl: './reporte1.component.html',
  styleUrl: './reporte1.component.css'
})
export class Reporte1Component {

  constructor(private rutaService: RutaService) {

  }

  rutas: any;

  ngOnInit() {

    this.getRutas();

  }

  getRutas() {
    this.rutaService.getRutas().subscribe(
      (rutas: Ruta[]) => {
        this.rutas = rutas;
        console.log('nice');
        console.log(rutas);
      }, (error) => {
        console.log('not found :c');
      }
    );
  }
}
