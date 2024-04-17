/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class ColaEspera {

    private int id;
    private int idPuntoDeControl;
    private int idRuta;
    private int cantidadMaximaPaquetes;
    private String nombreCola;

    public ColaEspera(int id, int idPuntoDeControl, int idRuta, int cantidadMaximaPaquetes, String nombreCola) {
        this.id = id;
        this.idPuntoDeControl = idPuntoDeControl;
        this.idRuta = idRuta;
        this.cantidadMaximaPaquetes = cantidadMaximaPaquetes;
        this.nombreCola = nombreCola;
    }

    public ColaEspera() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPuntoDeControl() {
        return idPuntoDeControl;
    }

    public void setIdPuntoDeControl(int idPuntoDeControl) {
        this.idPuntoDeControl = idPuntoDeControl;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getCantidadMaximaPaquetes() {
        return cantidadMaximaPaquetes;
    }

    public void setCantidadMaximaPaquetes(int cantidadMaximaPaquetes) {
        this.cantidadMaximaPaquetes = cantidadMaximaPaquetes;
    }

    public String getNombreCola() {
        return nombreCola;
    }

    public void setNombreCola(String nombreCola) {
        this.nombreCola = nombreCola;
    }

    @Override
    public String toString() {
        return "ColaEspera{" + "id=" + id + ", idPuntoDeControl=" + idPuntoDeControl + ", idRuta=" + idRuta + ", cantidadMaximaPaquetes=" + cantidadMaximaPaquetes + ", nombreCola=" + nombreCola + '}';
    }

}
