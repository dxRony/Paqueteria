/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.PaqueteDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Paquete;
import model.Sistema;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class PaqueteService {

    private PaqueteDB paqueteDB = new PaqueteDB();
    private SistemaService sistemaService = new SistemaService();

    public List<Paquete> getPaquetes() {
        return paqueteDB.getPaquetes();
    }

    public List<Paquete> getPaquetesBodega() {
        return paqueteDB.getPaquetesBodega();
    }

    public List<Paquete> getPaquetesSinRecoger() {
        return paqueteDB.getPaquetesSinRecoger();
    }

    public Paquete getPaqueteById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Paquete paquete = paqueteDB.getPaqueteById(id);

        if (paquete == null) {//si no existe el paquete en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El paquete no existe").build();
        }

        return paquete;
    }

    public Paquete crearPaquete(Paquete paquete) throws PaqueteriaApiException {
        if (paquete.getNitCliente() <= 0 || paquete.getIdDestino() <= 0 || paquete.getPeso() <= 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();
        }
        if (paquete.getPrecioPLibra() == 0) {
            Sistema sistema = sistemaService.getSistemaById(1);
            paquete.setPrecioPLibra(sistema.getPrecioPLibra());
        }
        return paqueteDB.crear(paquete);
    }

    public Paquete actualizarPaquete(Paquete paquete) throws PaqueteriaApiException {

        if (paquete == null) {//si no existe el paquete en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El paquete no existe").build();
        }
        return paqueteDB.actualizar(paquete);
    }

    public void eliminarPaquete(int id) {
        paqueteDB.eliminar(id);
    }
}
