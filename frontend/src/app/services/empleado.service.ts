import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Empleado } from "../model/empleado";

@Injectable({
    providedIn: 'root'
})

export class EmpleadoService {

    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getEmpleados() {
        return this.http.get<Empleado[]>(`${this.url}/empleados`);
    }

    getEmpleadoById(id: number) {
        return this.http.get<Empleado>(`${this.url}/empleados/${id}`);
    }

    crearEmpleado(empleado: Empleado) {
        return this.http.post<Empleado>(`${this.url}/empleados`, empleado);
    }

    actualizarEmpleado(empleado: Empleado) {
        return this.http.put<Empleado>(`${this.url}/empleados`, empleado);
    }

    eliminarEmpleado(id: number) {
        return this.http.delete<Empleado>(`${this.url}/empleados/${id}`);
    }

    iniciarSesion(empleado:Empleado){
        return this.http.post<Empleado>(`${this.url}/login`, empleado);
    }
}