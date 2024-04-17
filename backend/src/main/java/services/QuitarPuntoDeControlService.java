/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.AlmacenColas;
import model.PuntoDeControl;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class QuitarPuntoDeControlService {

    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();

    public String quitarPuntoDeControl(int ruta, int puntoDeControl, String nombreCola) throws PaqueteriaApiException {
        String mensaje = "";

        PuntoDeControl punto = puntoService.getPuntoDeControlById(puntoDeControl);

        System.out.println("nombreCola a quitar = " + nombreCola);

        if (punto.isLibre()) {
            colaService.eliminarColaByName(nombreCola);
            puntoService.eliminarPuntoDeControl(puntoDeControl);
            AlmacenColas.eliminarCola(nombreCola);
            mensaje = "punto de control eliminado";
        } else {
            mensaje = "el punto de control no se puede eliminar xq esta ocupado";
        }

        return mensaje;
    }
}
