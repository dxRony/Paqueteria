import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Sistema } from "../model/sistema";

 
 @Injectable({
    providedIn: 'root'
})

export class SistemaService{

    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getSistemas() {
        return this.http.get<Sistema[]>(`${this.url}/sistemas`);
    }

    getSistemaById(id: number) {
        return this.http.get<Sistema>(`${this.url}/sistemas/${id}`);
    }

    crearSistema(sistema: Sistema) {
        return this.http.post<Sistema>(`${this.url}/sistemas`, sistema);
    }

    actualizarSistema(sistema: Sistema) {
        return this.http.put<Sistema>(`${this.url}/sistemas`, sistema);
    }

    eliminarSistema(id: number) {
        return this.http.delete<Sistema>(`${this.url}/sistemas/${id}`);
    }
}