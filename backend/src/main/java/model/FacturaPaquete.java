/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ronyrojas
 */
public class FacturaPaquete {
    private int idRuta;
    private int nitCliente;
    private Date fecha;
    private int total;
    private int precioEnvio;
    private int precioIngreso;

    public FacturaPaquete(int idRuta, int nitCliente, Date fecha, int total, int precioEnvio, int precioIngreso) {
        this.idRuta = idRuta;
        this.nitCliente = nitCliente;
        this.fecha = fecha;
        this.total = total;
        this.precioEnvio = precioEnvio;
        this.precioIngreso = precioIngreso;
    }

    public FacturaPaquete() {
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(int precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public int getPrecioIngreso() {
        return precioIngreso;
    }

    public void setPrecioIngreso(int precioIngreso) {
        this.precioIngreso = precioIngreso;
    }

    @Override
    public String toString() {
        return "FacturaPaquete{" + "idRuta=" + idRuta + ", nitCliente=" + nitCliente + ", fecha=" + fecha + ", total=" + total + ", precioEnvio=" + precioEnvio + ", precioIngreso=" + precioIngreso + '}';
    }                  
    
}
