/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import model.Ruta;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class DesactivarRutaService {

    private RutaService rutaService = new RutaService();

    public String desactivarRuta(Ruta ruta) throws PaqueteriaApiException {

        String mensaje = "";

        if (ruta.getCantidadTotalPaquetes() == 0) {//si no hay paquetes en la ruta
            ruta.setActiva(false);
            rutaService.actualizarRuta(ruta);
            mensaje = "la ruta fue desactivada";
        } else {
            mensaje = "la ruta no ha podido ser desactivada, xq tiene paquetes en ruta";
        }
        return mensaje;
    }
}
