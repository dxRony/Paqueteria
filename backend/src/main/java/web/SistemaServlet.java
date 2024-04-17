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
import model.Sistema;
import services.SistemaService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "SistemaServlet", urlPatterns = "/sistemas/*")
public class SistemaServlet extends HttpServlet {

    private SistemaService sistemaService = new SistemaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//obtenerRecurso

        try {
            if (req.getPathInfo() != null) {//acceder a un empleado

                String pathParam = req.getPathInfo().replace("/", "");//componiendoUrl
                this.sendResponse(resp, sistemaService.getSistemaById(Integer.parseInt(pathParam)));
            } else {//acceder todos los empleados

                this.sendResponse(resp, sistemaService.getSistemas());
            }
        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder().codigoError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR).mensaje(
                            e.getMessage()).build());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//guardar/crear recurso

        try {
            Gson gson = new Gson();
            Sistema sistema = gson.fromJson(req.getReader(), Sistema.class);
            this.sendResponse(resp, sistemaService.crearSistema(sistema));
        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder()
                    .codigoError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage()).build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            Sistema sistema = gson.fromJson(req.getReader(), Sistema.class);
            this.sendResponse(resp, sistemaService.actualizarSistema(sistema));
        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder()
                    .codigoError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage()).build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//eliminar recurso
        try {
            if (req.getPathInfo() != null) {
                String pathParam = req.getPathInfo().replace("/", "");
                sistemaService.eliminarSistema(Integer.parseInt(pathParam));
                this.sendResponse(resp, "");
            } else {
                throw PaqueteriaApiException.builder().codigoError(HttpServletResponse.SC_BAD_REQUEST).mensaje("Id requerido").build();
            }
        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder()
                    .codigoError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage()).build());
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
