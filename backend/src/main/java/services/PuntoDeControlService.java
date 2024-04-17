/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.PuntoDeControlDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.PuntoDeControl;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class PuntoDeControlService {

    private PuntoDeControlDB puntoDB = new PuntoDeControlDB();

    public List<PuntoDeControl> getPuntosDeControl() {
        return puntoDB.getPuntosDeControl();
    }

    public PuntoDeControl getPuntoDeControlById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        PuntoDeControl punto = puntoDB.getPuntoDeControlById(id);

        if (punto == null) {//si no existe el punto en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El punto no existe").build();
        }

        return punto;
    }

    public PuntoDeControl crearPuntoDeControl(PuntoDeControl punto) throws PaqueteriaApiException {
        if (punto.getIdRuta() <= 0 || punto.getIdEmpleado() <= 0 || punto.getCantidadMaximaPaquetes() <= 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();
        }
        return puntoDB.crear(punto);
    }

    public PuntoDeControl actualizarPuntoDeControl(PuntoDeControl punto) throws PaqueteriaApiException {

        if (punto == null) {//si no existe el punto en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El punto no existe").build();
        }
        return puntoDB.actualizar(punto);
    }

    public void eliminarPuntoDeControl(int id) {
        puntoDB.eliminar(id);
    }

    public void eliminarPuntoDeControl2(int id, int ruta) {
        puntoDB.eliminar2(id, ruta);
    }
}
