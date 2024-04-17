import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Cliente } from "../model/cliente";

@Injectable({
    providedIn: 'root'
})

export class ClienteService {

    url: string = 'http://localhost:8080/PaqueteriaApi';

    constructor(private http: HttpClient) { }

    getClientes() { //listar a los clientes
        return this.http.get<Cliente[]>(`${this.url}/clientes`);
    }

    getClienteByNit(nit: number) {//obtener un cliente por el nit
        return this.http.get<Cliente>(`${this.url}/clientes/${nit}`);
    }

    crearCliente(cliente: Cliente) {
        return this.http.post<Cliente>(`${this.url}/clientes`, cliente);
    }

    actualizarCliente(cliente: Cliente) {
        return this.http.put<Cliente>(`${this.url}/clientes`, cliente);
    }

    eliminarCliente(nit: number) {
        return this.http.delete<Cliente>(`${this.url}/clientes/${nit}`);
    }

}