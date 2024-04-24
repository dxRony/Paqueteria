import { Component } from '@angular/core';
import { FacturaService } from '../../../../../services/factura.service';
import { FacturaPaquete } from '../../../../../model/facturaPaquete';

@Component({
  selector: 'app-reporte2',
  standalone: true,
  imports: [],
  templateUrl: './reporte2.component.html',
  styleUrl: './reporte2.component.css'
})
export class Reporte2Component {

  constructor(private facturaService: FacturaService) {

  }

  facturas: any;

  ngOnInit(){
    this.getReporte2();

  }

  getReporte2(){
    this.facturaService.getReporte2().subscribe(
      (facturas:FacturaPaquete[]) =>{
        this.facturas = facturas;
        console.log('nice');
      }, (error) =>{
        console.log('F\'s')
      }
    );
  }

}
