/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class Sistema {

    private int id;
    private int precioPLibra;
    private int tarifaOperacionGlobal;

    public Sistema(int id, int precioPLibra, int tarifaOperacionGlobal) {
        this.id = id;
        this.precioPLibra = precioPLibra;
        this.tarifaOperacionGlobal = tarifaOperacionGlobal;
    }

    public Sistema() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecioPLibra() {
        return precioPLibra;
    }

    public void setPrecioPLibra(int precioPLibra) {
        this.precioPLibra = precioPLibra;
    }

    public int getTarifaOperacionGlobal() {
        return tarifaOperacionGlobal;
    }

    public void setTarifaOperacionGlobal(int tarifaOperacionGlobal) {
        this.tarifaOperacionGlobal = tarifaOperacionGlobal;
    }

    @Override
    public String toString() {
        return "Sistema{" + "id=" + id + ", precioPLibra=" + precioPLibra + ", tarifaOperacionGlobal=" + tarifaOperacionGlobal + '}';
    }

}
