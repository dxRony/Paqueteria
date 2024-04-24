/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.RutaDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Ruta;
import util.GsonUtils;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class RutaService {

    private RutaDB rutaDB = new RutaDB();
        private GsonUtils<Ruta> gsonRuta = new GsonUtils<>();
    

    public List<Ruta> getRutas() {
        return rutaDB.getRutas();
    }
    
    public List<Ruta> getReporte(){
        return rutaDB.getReporte4();
    }

    public Ruta getRutaById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Ruta ruta = rutaDB.getRutaById(id);

        if (ruta == null) {//si no existe la ruta en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("la ruta no existe").build();
        }

        return ruta;
    }

    public Ruta crearRuta(Ruta ruta) throws PaqueteriaApiException {
        if (ruta.getIdDestino() <= 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();
        }
        ruta.setActiva(true);
        ruta.setCantidadTotalPaquetes(0);
        ruta.setConcurrencia(0);
        return rutaDB.crear(ruta);
    }

    public Ruta actualizarRuta(Ruta ruta) throws PaqueteriaApiException {

        if (ruta == null) {//si no existe la ruta en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La ruta no existe").build();
        }
        return rutaDB.actualizar(ruta);
    }

    public void eliminarRuta(int id) {
        rutaDB.eliminar(id);
    }

}
