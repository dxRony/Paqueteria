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
import services.EmpleadoService;
import util.GsonUtils;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private EmpleadoService empleadoService;
    private GsonUtils<Empleado> gsonEmpleado;

    public LoginServlet() {
        empleadoService = new EmpleadoService();
        gsonEmpleado = new GsonUtils<>();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Empleado empleadoRecibido = gsonEmpleado.readFromJson(req, Empleado.class);
            System.out.println("empleado recibido: " + empleadoRecibido.toString());

            if (empleadoRecibido.getUsername().equals("") || empleadoRecibido.getPassword().equals("")) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            Empleado empleadoBuscado = empleadoService.getEmpleadoLogin(empleadoRecibido.getUsername(), empleadoRecibido.getPassword());

            if (empleadoBuscado == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
                gsonEmpleado.sendAsJson(resp, empleadoBuscado);
                System.out.println("Empleado enviado: " + empleadoBuscado.toString());
            }

        } catch (PaqueteriaApiException ex) {
            this.sendError(resp, ex);
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
