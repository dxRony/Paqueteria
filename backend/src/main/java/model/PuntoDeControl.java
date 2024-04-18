/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class PuntoDeControl {

    private int id;
    private int idRuta;
    private int idEmpleado;
    private int tarifaOperacion;
    private boolean libre;
    private int cantidadMaximaPaquetes;

    public PuntoDeControl(int id, int idRuta, int idEmpleado, int tarifaOperacion, boolean libre, int cantidadMaximaPaquetes) {
        this.id = id;
        this.idRuta = idRuta;
        this.idEmpleado = idEmpleado;
        this.tarifaOperacion = tarifaOperacion;
        this.libre = libre;
        this.cantidadMaximaPaquetes = cantidadMaximaPaquetes;
    }

    public PuntoDeControl() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getTarifaOperacion() {
        return tarifaOperacion;
    }

    public void setTarifaOperacion(int tarifaOperacion) {
        this.tarifaOperacion = tarifaOperacion;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public int getCantidadMaximaPaquetes() {
        return cantidadMaximaPaquetes;
    }

    public void setCantidadMaximaPaquetes(int cantidadMaximaPaquetes) {
        this.cantidadMaximaPaquetes = cantidadMaximaPaquetes;
    }

    @Override
    public String toString() {
        return "PuntoDeControl{" + "id=" + id + ", idRuta=" + idRuta + ", idEmpleado=" + idEmpleado + ", tarifaOperacion=" + tarifaOperacion + ", libre=" + libre + ", cantidadMaximaPaquetes=" + cantidadMaximaPaquetes + '}';
    }
    
    

}
