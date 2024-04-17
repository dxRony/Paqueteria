/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.DestinoDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Destino;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class DestinoService {

    private DestinoDB destinoDB = new DestinoDB();

    public List<Destino> getEmpleados() {
        return destinoDB.getDestinos();
    }

    public Destino getDestinoById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Destino destino = destinoDB.getDestinoById(id);

        if (destino == null) {//si no existe el destino en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El destino no existe").build();
        }

        return destino;
    }

    public Destino crearDestino(Destino destino) throws PaqueteriaApiException {
        if (destino.getCuota() <= 0) {//valor negativo
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("La cuota no puede ser menor o igual a cero").build();
        }
        return destinoDB.crear(destino);
    }

    public Destino actualizarDestino(Destino destino) throws PaqueteriaApiException {

        if (destino == null) {//si no existe el destino en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El destino no existe").build();
        }
        return destinoDB.actualizar(destino);
    }

    public void eliminarDestino(int id) {
        destinoDB.eliminar(id);
    }

}
