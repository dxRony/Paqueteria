/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.FacturaDB;
import data.PaqueteDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Factura;
import model.FacturaPaquete;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class FacturaService {

    private FacturaDB facturaDB = new FacturaDB();
    private PaqueteDB paqueteDB = new PaqueteDB();

    public List<Factura> getFacturas() {
        return facturaDB.getFacturas();
    }

    public Factura getFacturaById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Factura factura = facturaDB.getFacturaById(id);

        if (factura == null) {//si no existe la factura en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("La factura no existe").build();
        }

        return factura;
    }

    public Factura crearFactura(Factura factura) throws PaqueteriaApiException {
        if (factura.getFecha() == null || factura.getNitCliente() <= 0 || factura.getTotal() < 0) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();
        }
        return facturaDB.crear(factura);
    }
    
    public List<FacturaPaquete> getReporte2(){
        return paqueteDB.getReporte2();
        
    }

    public Factura actualizarFactura(Factura factura) throws PaqueteriaApiException {

        if (factura == null) {//si no existe la factura en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El empleado no existe").build();
        }
        return facturaDB.actualizar(factura);
    }

    public void eliminarFactura(int id) {
        facturaDB.eliminar(id);
    }
}
