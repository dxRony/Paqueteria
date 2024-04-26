/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.SistemaDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Sistema;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class SistemaService {

    private SistemaDB sistemaDB = new SistemaDB();

    public List<Sistema> getSistemas() {
        return sistemaDB.getSistemas();
    }

    public Sistema getSistemaById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Sistema sistema = sistemaDB.getSistemaById(id);

        if (sistema == null) {//si no existe el sistema en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El sistema no existe").build();
        }

        return sistema;
    }

    public Sistema crearSistema(Sistema sistema) throws PaqueteriaApiException {
        if (sistema.getPrecioPLibra() <= 0 || sistema.getTarifaOperacionGlobal() <= 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Los campos no pueden ser menores o iguales a cero").build();
        }
        return sistemaDB.crear(sistema);
    }

    public Sistema actualizarSistema(Sistema sistema) throws PaqueteriaApiException {

        sistema.setId(1);
        if (sistema.getPrecioPLibra() == 0) {
            Sistema sistemaTmp = sistemaDB.getSistemaById(sistema.getId());
            sistema.setPrecioPLibra(sistemaTmp.getPrecioPLibra());

        }
        if (sistema.getTarifaOperacionGlobal() == 0) {
            Sistema sistemaTmp = sistemaDB.getSistemaById(sistema.getId());
            sistema.setTarifaOperacionGlobal(sistemaTmp.getTarifaOperacionGlobal());
        }

        return sistemaDB.actualizar(sistema);
    }

    public void eliminarSistema(int id) {
        sistemaDB.eliminar(id);
    }

}
