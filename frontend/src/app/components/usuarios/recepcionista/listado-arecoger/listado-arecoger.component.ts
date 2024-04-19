import { Component } from '@angular/core';
import { PaqueteService } from '../../../../services/paquete.service';
import { Paquete } from '../../../../model/paquete';

@Component({
  selector: 'app-listado-arecoger',
  standalone: true,
  imports: [],
  templateUrl: './listado-arecoger.component.html',
  styleUrl: './listado-arecoger.component.css'
})
export class ListadoARecogerComponent {

  constructor(private paqueteService: PaqueteService) {

  }

  paquetes: any;
  ngOnInit() {
    this.getPaquetesSinRecoger();
  }

  getPaquetesSinRecoger() {
    this.paqueteService.getPaquetesSinRecoger().subscribe(
      (paquetes: Paquete[]) => {
        this.paquetes = paquetes;
        console.log(paquetes);
      }, (error) => {
        console.log('error al obtener los paquetes sin recoger' + error);
      }
    );
  }
}
