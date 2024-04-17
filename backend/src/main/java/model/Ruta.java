/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class Ruta {

    private int id;
    private int idDestino;
    private boolean activa;
    private int cantidadTotalPaquetes;
    private int concurrencia;

    public Ruta(int id, int idDestino, boolean activa, int cantidadTotalPaquetes, int concurrencia) {
        this.id = id;
        this.idDestino = idDestino;
        this.activa = activa;
        this.cantidadTotalPaquetes = cantidadTotalPaquetes;
        this.concurrencia = concurrencia;
    }

    public Ruta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getCantidadTotalPaquetes() {
        return cantidadTotalPaquetes;
    }

    public void setCantidadTotalPaquetes(int cantidadTotalPaquetes) {
        this.cantidadTotalPaquetes = cantidadTotalPaquetes;
    }

    public int getConcurrencia() {
        return concurrencia;
    }

    public void setConcurrencia(int concurrencia) {
        this.concurrencia = concurrencia;
    }

    @Override
    public String toString() {
        return "Ruta{" + "id=" + id + ", idDestino=" + idDestino + ", activa=" + activa + ", cantidadTotalPaquetes=" + cantidadTotalPaquetes + ", concurrencia=" + concurrencia + '}';
    }

}
