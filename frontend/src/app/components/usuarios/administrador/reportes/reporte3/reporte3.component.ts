import { Component } from '@angular/core';
import { Paquete } from '../../../../../model/paquete';
import { FacturaService } from '../../../../../services/factura.service';
import { Factura } from '../../../../../model/factura';

@Component({
  selector: 'app-reporte3',
  standalone: true,
  imports: [],
  templateUrl: './reporte3.component.html',
  styleUrl: './reporte3.component.css'
})
export class Reporte3Component {

  constructor(private facturaService: FacturaService) {

  }

  facturas: any;

  ngOnInit() {
    this.getPaquetes();
  }

  getPaquetes() {
    this.facturaService.getFacturas().subscribe(
      (facturas: Factura[]) => {
        this.facturas = facturas;
        console.log('nice');
      }, (error) => {
        console.log('not found :c');
      }
    );
  }
}
