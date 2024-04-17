/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.List;
import model.ColaEspera;
import model.Paquete;
import model.PuntoDeControl;
import services.ColaEsperaService;
import services.PuntoDeControlService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class CargarColas {

    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();

    public void cargarColas() throws PaqueteriaApiException {

        List<PuntoDeControl> puntosDB = puntoService.getPuntosDeControl();
        for (PuntoDeControl puntoDeControl : puntosDB) {
            //creando colas en la DB
            ColaEspera cola = new ColaEspera();
            cola.setIdPuntoDeControl(puntoDeControl.getId());
            cola.setIdRuta(puntoDeControl.getIdRuta());
            cola.setCantidadMaximaPaquetes(puntoDeControl.getCantidadMaximaPaquetes());
            cola.setNombreCola(puntoDeControl.getIdRuta() + "-" + puntoDeControl.getId());
            colaService.crearCola(cola);
            //creando lista para los paquetes en la lista de colas
            ArrayList<Paquete> colaNueva = new ArrayList<>();
            AlmacenColas.agregarCola(puntoDeControl.getIdRuta() + "-" + puntoDeControl.getId(), colaNueva);
        }
        System.out.println(AlmacenColas.obtenerNombresColas());
    }
}
