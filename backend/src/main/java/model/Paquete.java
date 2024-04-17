/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class Paquete {

    private int id;
    private int idRuta;
    private int nitCliente;
    private int idDestino;
    private int idPuntoDeControlActual;
    private int tiempo;
    private int peso;
    private int precioEnvio;
    private boolean ingresado;
    private boolean recogido;
    private int precioPLibra;
    private boolean enRuta;
    private int precioIngreso;

    public Paquete(int id, int idRuta, int nitCliente, int idDestino, int idPuntoDeControlActual, int tiempo, int peso, int precioEnvio, boolean ingresado, boolean recogido, int precioPLibra, boolean enRuta, int precioIngreso) {
        this.id = id;
        this.idRuta = idRuta;
        this.nitCliente = nitCliente;
        this.idDestino = idDestino;
        this.idPuntoDeControlActual = idPuntoDeControlActual;
        this.tiempo = tiempo;
        this.peso = peso;
        this.precioEnvio = precioEnvio;
        this.ingresado = ingresado;
        this.recogido = recogido;
        this.precioPLibra = precioPLibra;
        this.enRuta = enRuta;
        this.precioIngreso = precioIngreso;
    }

    public Paquete() {
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

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public int getIdPuntoDeControlActual() {
        return idPuntoDeControlActual;
    }

    public void setIdPuntoDeControlActual(int idPuntoDeControlActual) {
        this.idPuntoDeControlActual = idPuntoDeControlActual;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrecioEnvio() {
        return precioEnvio;
    }

    public void setPrecioEnvio(int precioEnvio) {
        this.precioEnvio = precioEnvio;
    }

    public boolean isIngresado() {
        return ingresado;
    }

    public void setIngresado(boolean ingresado) {
        this.ingresado = ingresado;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }

    public int getPrecioPLibra() {
        return precioPLibra;
    }

    public void setPrecioPLibra(int precioPLibra) {
        this.precioPLibra = precioPLibra;
    }

    public boolean isEnRuta() {
        return enRuta;
    }

    public void setEnRuta(boolean enRuta) {
        this.enRuta = enRuta;
    }

    public int getPrecioIngreso() {
        return precioIngreso;
    }

    public void setPrecioIngreso(int precioIngreso) {
        this.precioIngreso = precioIngreso;
    }

    @Override
    public String toString() {
        return "Paquete{" + "id=" + id + ", idRuta=" + idRuta + ", nitCliente=" + nitCliente + ", idDestino=" + idDestino + ", idPuntoDeControlActual=" + idPuntoDeControlActual + ", tiempo=" + tiempo + ", peso=" + peso + ", precioEnvio=" + precioEnvio + ", ingresado=" + ingresado + ", recogido=" + recogido + ", precioPLibra=" + precioPLibra + ", enRuta=" + enRuta + ", precioIngreso=" + precioIngreso + '}';
    }

}
