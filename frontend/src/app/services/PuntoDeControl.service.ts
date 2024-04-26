import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PuntoDeControl } from "../model/puntoDeControl";

@Injectable({
    providedIn: 'root'
})

export class PuntoDeControlService {
    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getPuntosDeControl() {
        return this.http.get<PuntoDeControl[]>(`${this.url}/puntos`);
    }

    getPuntoDeControlById(id: number) {
        return this.http.get<PuntoDeControl>(`${this.url}/puntos/${id}`);
    }

    crearPuntoDeControl(puntoDeControl: PuntoDeControl) {
        return this.http.post<PuntoDeControl>(`${this.url}/puntos`, puntoDeControl);
    }

    actualizarPuntoDeControl(puntoDeControl: PuntoDeControl) {
        return this.http.put<PuntoDeControl>(`${this.url}/puntos`, puntoDeControl);
    }

    eliminarPuntoDeControl(idRuta: number, idPuntoDeControl: number) {
        return this.http.delete<PuntoDeControl>(`${this.url}/quitarPuntos?parametros=${idRuta}_${idPuntoDeControl}`);
    }
}