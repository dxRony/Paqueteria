/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Date;
import model.Factura;
import model.Paquete;
import model.Ruta;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class RecogerPaqueteService {

    private PaqueteService paqueteService = new PaqueteService();
    private FacturaService facturaService = new FacturaService();
    private RutaService rutaService = new RutaService();

    public void recogerPaquete(Date fechaSql, int idPaquete) throws PaqueteriaApiException {
        System.out.println("paquete a recoger");
        Paquete paquete = paqueteService.getPaqueteById(idPaquete);//obteniendo el paquete dado el id
        System.out.println(paquete.toString());
        if (paquete.isIngresado() && !paquete.isRecogido()) { //si el paquete ya llego a su destino y no ha sido recogido
            System.out.println("el paquete no ha sido recogido");
            int precioIngreso = paquete.getPrecioIngreso();
            int precioEnvio = paquete.getPrecioEnvio();
            int totalEnvio = precioIngreso + precioEnvio;//obteniendo el total, para la factura
            int nitCliente = paquete.getNitCliente();//obteniendo el nit del propietario del paquete

            //cambiando estado de paquete
            paquete.setRecogido(true);
            paqueteService.actualizarPaquete(paquete);

            //modificando los paquetes que estan en la ruta
            int idRuta = paquete.getIdRuta();
            Ruta ruta = rutaService.getRutaById(idRuta);
            int concurrencia = ruta.getConcurrencia();
            System.out.println("concurrencia = " + concurrencia);
            System.out.println("ruta para actualizar: " + ruta.toString());
            ruta.setCantidadTotalPaquetes(ruta.getCantidadTotalPaquetes() - 1);
            ruta.setConcurrencia(ruta.getConcurrencia());
            System.out.println("ruta actualizada: " + ruta.toString());
            rutaService.actualizarRuta(ruta);

            //creando la factura de la entrega
            System.out.println("creando factura...");
            Factura factura = new Factura();
            factura.setFecha(fechaSql);
            System.out.println("fechaSql = " + fechaSql);
            factura.setNitCliente(nitCliente);
            System.out.println("nitCliente = " + nitCliente);
            factura.setPrecioEnvio(precioEnvio);
            factura.setPrecioIngreso(precioIngreso);
            factura.setTotal(totalEnvio);
            System.out.println("totalEnvio = " + totalEnvio);
            facturaService.crearFactura(factura);//creando factura
        } else {
            System.out.println("el paquete ya fue recogido anteriormente");
        }
    }

}
