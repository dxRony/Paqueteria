/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.EmpleadoDB;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Empleado;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class EmpleadoService {

    private EmpleadoDB empleadoDB = new EmpleadoDB();

    public List<Empleado> getEmpleados() {
        return empleadoDB.getEmpleados();
    }

    public Empleado getEmpleadoById(Integer id) throws PaqueteriaApiException {
        if (id == null) {//si no hay id en la peticion
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("No has ingresado el id").build();
        }

        Empleado empleado = empleadoDB.getEmpleadoById(id);

        if (empleado == null) {//si no existe el empleado en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El empleado no existe").build();
        }

        return empleado;
    }

    public Empleado getEmpleadoLogin(String username, String password) throws PaqueteriaApiException {

        Empleado empleado = empleadoDB.getEmpleadoLogin(username, password);

        if (empleado == null) {//si no existe el empleado en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El empleado no existe").build();
        }

        return empleado;
    }
    
    public Empleado crearEmpleado(Empleado empleado) throws PaqueteriaApiException {
        if (empleado.getUsername() == null || empleado.getPassword() == null || empleado.getNombre() == null) {//campos vacios
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Complete los campos").build();
        }
        return empleadoDB.crear(empleado);
    }

    public Empleado actualizarEmpleado(Empleado empleado) throws PaqueteriaApiException {

        if (empleado == null) {//si no existe el empleado en la DB
            throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_NOT_FOUND).mensaje("El empleado no existe").build();
        }
        return empleadoDB.actualizar(empleado);
    }

    public void eliminarEmpleado(int id) {
        empleadoDB.eliminar(id);
    }

}
