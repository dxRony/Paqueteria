/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.AlmacenColas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.ColaEspera;
import model.Paquete;
import model.PuntoDeControl;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class OperarPaqueteService {

    private PaqueteService paqueteService = new PaqueteService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private PuntoDeControlService puntoService = new PuntoDeControlService();

    public String operarPaquete(int idPaquete, int tiempo) throws PaqueteriaApiException {
        String mensaje = "";
        int indicePunto = 0;

        Paquete paqueteAOperar = paqueteService.getPaqueteById(idPaquete);//obteniendo el paquete

        int idRuta = paqueteAOperar.getIdRuta();//obteniendo la ruta del paquete
        List<ColaEspera> colasRuta = colaService.getColasByRuta(idRuta);//buscando las colas de la ruta donde esta el paquete
        ArrayList<String> nombreColas = new ArrayList<>();

        for (ColaEspera colaEspera : colasRuta) {
            nombreColas.add(colaEspera.getNombreCola());//de las colas de la ruta, guardo el nombre 
        }

        Collections.sort(nombreColas, new Comparator<String>() {//ordenando las colas de menor a mayor
            @Override
            public int compare(String o1, String o2) {
                int numero2_1 = Integer.parseInt(o1.split("-")[1]);
                int numero2_2 = Integer.parseInt(o2.split("-")[1]);
                return Integer.compare(numero2_1, numero2_2);
            }
        });

        int idPuntoDeControlActual = paqueteAOperar.getIdPuntoDeControlActual();

        ColaEspera colaActual = colaService.getColaByIdPuntoDeControlActual(idPuntoDeControlActual);//obteniendo colaActual

        String nombreColaActual = colaActual.getNombreCola();//obteniendo el nombre de la cola actual
        String nombreColaSiguiente = obtenerSiguienteCola(nombreColas, nombreColaActual);//obteniendo nombre cola siguiente (si existe)

        Paquete paqueteDeCola = AlmacenColas.getPaqueteById(nombreColaActual, idPaquete);

        if (nombreColaSiguiente != null) {//si existe una cola siguiente en la ruta
            System.out.println("entrando al if");
            ColaEspera colaSiguiente = colaService.getColaByName(nombreColaSiguiente);//obteniendo la cola siguiente

            indicePunto = nombreColas.indexOf(nombreColaSiguiente);
            int tamanoPuntos = nombreColas.size();

            int idPuntoDeControlPaquete = paqueteAOperar.getIdPuntoDeControlActual();//obteber el id del punto donde esta el paquete
            PuntoDeControl puntoControl = puntoService.getPuntoDeControlById(idPuntoDeControlPaquete);//obtener el punto donde esta el paquete

            //actualizar paquete en DB, tiempo, precio envio y puntoDe control actual
            int idPuntoDeControlSiguiente = colaSiguiente.getIdPuntoDeControl();
            int tarifaOperacion = puntoControl.getTarifaOperacion();
            int precioEnvio = paqueteAOperar.getPrecioEnvio();//obteniendo datos del punto                    
            precioEnvio = precioEnvio + (tiempo * tarifaOperacion);//definiendo precio envio
            paqueteAOperar.setIdPuntoDeControlActual(idPuntoDeControlSiguiente);
            paqueteAOperar.setTiempo(paqueteAOperar.getTiempo() + tiempo);
            paqueteAOperar.setPrecioEnvio(precioEnvio);//mandando precio envio 
            paqueteService.actualizarPaquete(paqueteAOperar);

            mensaje = "el paquete esta en el punto de control:" + indicePunto + " de:" + tamanoPuntos + ", de la ruta: " + paqueteAOperar.getIdRuta();

            System.out.println("agregando paquete");
            AlmacenColas.agregarPaqueteACola(nombreColaSiguiente, paqueteAOperar, true);//agregando a la lista siguiente el paquete
            puntoControl.setLibre(false);
            puntoService.actualizarPuntoDeControl(puntoControl);

            System.out.println("eliminando paquete");
            AlmacenColas.agregarPaqueteACola(nombreColaActual, paqueteDeCola, false);//eliminando de la lista actual el paquete

        } else {//si ya no hay una siguiente cola (el paquete ya ha llegado al destino)
            System.out.println("entrando al else");
            AlmacenColas.agregarPaqueteACola(nombreColaActual, paqueteDeCola, false);//eliminando de la lista actual el paquete
            int idPuntoDeControlPaquete = paqueteAOperar.getIdPuntoDeControlActual();//obteber el id del punto donde esta el paquete

            PuntoDeControl puntoControl = puntoService.getPuntoDeControlById(idPuntoDeControlPaquete);//obtener el punto donde esta el paquete
            int precioEnvio = paqueteAOperar.getPrecioEnvio();//obteniendo datos del punto
            int tarifaOperacion = puntoControl.getTarifaOperacion();
            paqueteAOperar.setIngresado(true);
            paqueteAOperar.setTiempo(paqueteAOperar.getTiempo() + tiempo);
            precioEnvio = precioEnvio + (tiempo * tarifaOperacion);//definiendo precio envio
            paqueteAOperar.setPrecioEnvio(precioEnvio);//mandando precio envio
            //  paqueteAOperar.setIdPuntoDeControlActual(0);//haciendo que el paquete ya no este en un punto de control

            paqueteService.actualizarPaquete(paqueteAOperar);
            System.out.println("paquete ingresado a destino: " + paqueteAOperar.toString());
            mensaje = "el paquete ya ha llegado al destino!!";
        }
        
        return mensaje;
    }

    public String obtenerSiguienteCola(ArrayList<String> nombreColas, String nombreColaActual) {
        System.out.println("nombrecolas recibidas");
        for (String nombreCola : nombreColas) {

            System.out.println(nombreCola);
        }
        System.out.println("nombreColaActual = " + nombreColaActual);
        int indiceActual = nombreColas.indexOf(nombreColaActual);
        if (indiceActual != -1 && indiceActual + 1 < nombreColas.size()) {
            return nombreColas.get(indiceActual + 1);
        }
        return null;
    }
}
