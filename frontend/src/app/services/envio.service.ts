import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Envio } from "../model/envio";

@Injectable({
    providedIn: 'root'
})

export class EnvioService {
    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getEnvios() {
        return this.http.get<Envio[]>(`${this.url}/envios`);
    }

    getEnvioById(id: number) {
        return this.http.get<Envio>(`${this.url}/envios/${id}`);
    }

    crearEnvio(envio: Envio) {
        return this.http.post<Envio>(`${this.url}/envios`, envio);
    }

    actualizarEnvio(envio: Envio) {
        return this.http.put<Envio>(`${this.url}/envio`, envio);
    }

    eliminarEnvio(id: number) {
        return this.http.delete<Envio>(`${this.url}/envio/${id}`);
    }

}