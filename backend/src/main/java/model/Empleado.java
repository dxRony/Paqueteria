/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Rony Rojas
 */
public class Empleado {

    private int id;
    private int idSistema;
    private String username;
    private String password;
    private boolean activo;
    private int rol;
    private String nombre;

    public Empleado(int id, int idSistema, String username, String password, boolean activo, int rol, String nombre) {
        this.id = id;
        this.idSistema = idSistema;
        this.username = username;
        this.password = password;
        this.activo = activo;
        this.rol = rol;
        this.nombre = nombre;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", idSistema=" + idSistema + ", username=" + username + ", password=" + password + ", activo=" + activo + ", rol=" + rol + ", nombre=" + nombre + '}';
    }

}
