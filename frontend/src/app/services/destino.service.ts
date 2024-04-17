import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Destino } from "../model/destino";

@Injectable({
    providedIn: 'root'
})

export class DestinoService {

    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getDestinos() {
        return this.http.get<Destino[]>(`${this.url}/destinos`);
    }

    getDestinoById(id: number) {
        return this.http.get<Destino>(`${this.url}/destinos/${id}`);
    }

    crearDestino(destino: Destino) {
        return this.http.post<Destino>(`${this.url}/destinos`, destino);
    }

    actualizarDestino(destino: Destino) {
        return this.http.put<Destino>(`${this.url}/destinos`, destino);
    }

    eliminarDestino(id: number) {
        return this.http.delete<Destino>(`${this.url}/destinos/${id}`);
    }
}