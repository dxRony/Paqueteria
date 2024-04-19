/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Empleado;
import services.DesactivarEmpleadoService;
import services.EmpleadoService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "DesactivarEmpleadoServlet", urlPatterns = "/desactivarEmpleado/*")
public class DesactivarEmpleadoServlet extends HttpServlet {
    
    private EmpleadoService empleadoService = new EmpleadoService();
    private DesactivarEmpleadoService desactivarService = new DesactivarEmpleadoService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            String mensaje;
            if (req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                System.out.println("pathParam = " + pathParam);
                Empleado empleado = empleadoService.getEmpleadoById(Integer.parseInt(pathParam));
                mensaje = desactivarService.desactivarEmpleado(empleado);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST)
                        .mensaje("Id de Paquete requerido").build();
            }
        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder().codigoError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR).mensaje(
                            e.getMessage()).build());
        }
        
    }
    
    private void sendResponse(HttpServletResponse resp, Object object) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = resp.getWriter();
        out.println(new Gson().toJson(object));
    }
    
    private void sendError(HttpServletResponse resp, PaqueteriaApiException e) throws IOException {
        resp.setContentType("application/json");
        resp.sendError(e.getCodigoError(), e.getMensaje());
    }
}
