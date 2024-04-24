import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Factura } from "../model/factura";
import { FacturaPaquete } from "../model/facturaPaquete";

@Injectable({
    providedIn: 'root'
})

export class FacturaService{
    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getFacturas() {
        return this.http.get<Factura[]>(`${this.url}/facturas`);
    }

    getReporte2(){
        return this.http.get<FacturaPaquete[]>(`${this.url}/reporte2`);
    }

    getFacturaById(id: number) {
        return this.http.get<Factura>(`${this.url}/facturas/${id}`);
    }

    crearDestino(factura: Factura) {
        return this.http.post<Factura>(`${this.url}/facturas`, factura);
    }

    actualizarFactura(factura: Factura) {
        return this.http.put<Factura>(`${this.url}/facturas`, factura);
    }

    eliminarFactura(id: number) {
        return this.http.delete<Factura>(`${this.url}/facturas/${id}`);
    }

}