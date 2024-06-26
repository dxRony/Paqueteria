import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Paquete } from "../model/paquete";

@Injectable({
    providedIn: 'root'
})


export class PaqueteService {
    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getPaquetes() {
        return this.http.get<Paquete[]>(`${this.url}/paquetes`);
    }

    getPaquetesSinRecoger(){
        return this.http.get<Paquete[]>(`${this.url}/paquetesSinRecoger`)
    }

    getPaqueteById(id: number) {
        return this.http.get<Paquete>(`${this.url}/paquetes/${id}`);
    }

    crearPaquete(paquete: Paquete) {
        return this.http.post<Paquete>(`${this.url}/paquetes`, paquete);
    }

    actualizarPaquete(paquete: Paquete) {
        return this.http.put<Paquete>(`${this.url}/paquetes`, paquete);
    }

    eliminarPaquete(id: number) {
        return this.http.delete<Paquete>(`${this.url}/paquetes/${id}`);
    }

    operarPaquete(idPaquete: number, tiempo: number) {
        return this.http.get<Paquete>(`${this.url}/operarPaquete?parametros=${idPaquete}_${tiempo}`);
    }

    retirarPaquete(idPaquete: number, fecha: string) {
        return this.http.get<Paquete>(`${this.url}/recogerPaquete?parametros=${idPaquete}_${fecha}`)
    }
}