import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ColaEspera } from "../model/colaEspera";

@Injectable({
    providedIn: 'root'
})


export class ColaEsperaService {

    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getColas() {
        return this.http.get<ColaEspera[]>(`${this.url}/colas`);
    }

    getColaById(id: number) {
        return this.http.get<ColaEspera>(`${this.url}/colas/${id}`);
    }

    crearCola(cola: ColaEspera) {
        return this.http.post<ColaEspera>(`${this.url}/colas`, cola);
    }

    actualizarCola(cola: ColaEspera) {
        return this.http.put<ColaEspera>(`${this.url}/colas`, cola);
    }

    eliminarCola(id: number) {
        return this.http.delete<ColaEspera>(`${this.url}/colas/${id}`);
    }
}