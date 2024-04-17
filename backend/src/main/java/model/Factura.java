/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Rony Rojas
 */
public class Factura {

    private int id;
    private int nitCliente;
    private int total;
    private Date fecha;
    private int precioIngreso;
    private int precioEnvio;

    public Factura(int id, int nitCliente, int total, Date fecha, int precioIngreso, int precioEnvio) {
        this.id = id;
        this.nitCliente = nitCliente;
        this.total = total;
        this.fecha = fecha;
        this.precioIngreso = precioIngreso;
        this.precioEnvio = precioEnvio;
    }

    public Factura() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPrecioIngreso() {
        return precioIngreso;
    }

    public void setPrecioIngreso(int precioIngreso) {
        this.precioIngreso = precioIngreso;
    }

    public int getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(int precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

}
