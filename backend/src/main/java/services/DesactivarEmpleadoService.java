/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import model.Empleado;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class DesactivarEmpleadoService {

    private EmpleadoService empleadoService = new EmpleadoService();

    public String desactivarEmpleado(Empleado empleado) throws PaqueteriaApiException {
        String mensaje = "";
        if (empleado.getRol() != 1) {//si el empleado no es un administrador
            empleado.setActivo(false);
            empleadoService.actualizarEmpleado(empleado);
            mensaje = "empleado con id = " + empleado.getId() + ", desactivado";
        } else {
            mensaje = "el empleado no se puede desactivar porque es un administrador tambien";
        }

        return mensaje;
    }

}
