/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.ClienteDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cliente;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class ClienteService {

    private ClienteDB clienteDB = new ClienteDB();

    public List<Cliente> getClientes() {
        return clienteDB.getClientes();
    }

    public Cliente getClienteByNit(Integer nit) throws PaqueteriaApiException {
        if (nit == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Cliente cliente = clienteDB.getClienteByNit(nit);

        if (cliente == null) {//si no existe el cliente en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El cliente no existe").build();
        }
        return cliente;
    }

    public Cliente crearCliente(Cliente cliente) throws PaqueteriaApiException {
        if (cliente.getNombre() == null) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();

        }
        if (cliente.getNit() <= 0) {
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("El nit no puede ser menor o igual a cero").build();
        }
        cliente.setIdSistema(1);
        cliente.setActivo(true);
        return clienteDB.crear(cliente);
    }

    public Cliente actualizarCliente(Cliente cliente) throws PaqueteriaApiException {

        if (cliente == null) {//si no existe el cliente en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El cliente no existe").build();
        }
        return clienteDB.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        clienteDB.eliminar(id);
    }

}
