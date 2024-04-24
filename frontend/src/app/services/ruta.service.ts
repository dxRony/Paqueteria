import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Ruta } from "../model/ruta";

@Injectable({
    providedIn: 'root'
})

export class RutaService {
    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getRutas() {
        return this.http.get<Ruta[]>(`${this.url}/rutas`);
    }

    getReporte4(){
        return this.http.get<Ruta[]>(`${this.url}/reporte4`)
    }

    getRutaById(id: number) {
        return this.http.get<Ruta>(`${this.url}/rutas/${id}`);
    }

    crearRuta(ruta: Ruta) {
        return this.http.post<Ruta>(`${this.url}/rutas`, ruta);
    }

    actualizarRuta(ruta: Ruta) {
        return this.http.put<Ruta>(`${this.url}/rutas`, ruta);
    }

    desactivarRuta(idRuta: number) {
        return this.http.get<Ruta>(`${this.url}/desactivarRuta/${idRuta}`);
    }

    eliminarRuta(id: number) {
        return this.http.delete<Ruta>(`${this.url}/rutas/${id}`);
    }
}