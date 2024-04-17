/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.ColaEsperaDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ColaEspera;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class ColaEsperaService {

    private ColaEsperaDB colaEsperaDB = new ColaEsperaDB();

    public List<ColaEspera> getColas() {
        return colaEsperaDB.getColasEspera();
    }

    public List<ColaEspera> getColasByRuta(Integer ruta) {
        return colaEsperaDB.getColasEsperaByRuta(ruta);
    }

    public ColaEspera getColaById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        ColaEspera cola = colaEsperaDB.getColaEsperaById(id);

        if (cola == null) {//si no existe la cola en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La cola no existe").build();
        }

        return cola;
    }

    public ColaEspera getColaByIdPuntoDeControlActual(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        ColaEspera cola = colaEsperaDB.getColaEsperaByIdPuntoDeControlActual(id);

        if (cola == null) {//si no existe la cola en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La cola no existe").build();
        }

        return cola;
    }

    public ColaEspera getColaByName(String nombreCola) throws PaqueteriaApiException {
        if (nombreCola == null) { // si no hay nombre de la cola
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el nombre").build();
        }
        ColaEspera cola = colaEsperaDB.getColaEsperaByName(nombreCola);

        if (cola == null) {//si no existe            
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La cola no existe").build();
        }
        return cola;
    }

    public ColaEspera crearCola(ColaEspera cola) throws PaqueteriaApiException {
        if (cola.getIdPuntoDeControl() <= 0 || cola.getCantidadMaximaPaquetes() <= 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Los campos no pueden ser menores o iguales a cero").build();
        }
        return colaEsperaDB.crear(cola);
    }

    public ColaEspera actualizarCola(ColaEspera cola) throws PaqueteriaApiException {

        if (cola == null) {//si no existe la cola en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La cola no existe").build();
        }
        return colaEsperaDB.actualizar(cola);
    }

    public void eliminarColaByName(String nombre) {
        colaEsperaDB.eliminarByName(nombre);
    }

}
