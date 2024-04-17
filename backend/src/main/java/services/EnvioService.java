/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.EnvioDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Envio;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class EnvioService {

    private EnvioDB envioDB = new EnvioDB();

    public List<Envio> getEnvios() {
        return envioDB.getEnvios();
    }

    public Envio getEnvioById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Envio envio = envioDB.getEnvioById(id);

        if (envio == null) {//si no existe el envio en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El envio no existe").build();
        }

        return envio;
    }

    public Envio crearEnvio(Envio envio) throws PaqueteriaApiException {
        if (envio.getIdPaquete() <= 0 || envio.getIdRuta() <= 0) {//campos nulos
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Los campos no pueden ser menores o iguales a cero").build();
        }
        return envioDB.actualizar(envio);
    }

    public Envio actualizarEnvio(Envio envio) throws PaqueteriaApiException {

        if (envio == null) {//si no existe el envio en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El envio no existe").build();
        }
        return envioDB.crear(envio);
    }

    public void eliminarEnvio(int id) {
        envioDB.eliminar(id);
    }

}
