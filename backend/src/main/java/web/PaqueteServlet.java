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
import model.Paquete;
import services.PaqueteService;
import util.GsonUtils;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "PaqueteServlet", urlPatterns = "/paquetes/*")
public class PaqueteServlet extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();
    private GsonUtils<Paquete> gsonPaquete = new GsonUtils<>();
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//obtenerRecurso

        try {
            if (req.getPathInfo() != null) {//acceder a un paquete
                
                 String pathParam = req.getPathInfo().replace("/", "");//componiendo pathParam
                 int idPaquete = Integer.parseInt(pathParam);

                
                
                Paquete paqueteEnviado = paqueteService.getPaqueteById(idPaquete);
                 resp.setStatus(HttpServletResponse.SC_OK);
                 gsonPaquete.sendAsJson(resp, paqueteEnviado);
                 System.out.println("paquete enviado: " + paqueteEnviado.toString());
                
                
               
            } else {//acceder todos los paquetes

                this.sendResponse(resp, paqueteService.getPaquetes());
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
            Paquete paqueteRecibido = gsonPaquete.readFromJson(req, Paquete.class);
            System.out.println("paquete recibido: " + paqueteRecibido.toString());
            Paquete paqueteCreado = paqueteService.crearPaquete(paqueteRecibido);
            resp.setStatus(HttpServletResponse.SC_OK);
            gsonPaquete.sendAsJson(resp, paqueteCreado);

            System.out.println(paqueteRecibido.toString());
        } catch (Exception e) {
            this.sendError(resp, PaqueteriaApiException.builder()
                    .codigoError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                    .mensaje(e.getMessage()).build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//modficar Recurso
        try {
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(req.getReader(), Paquete.class);
            this.sendResponse(resp, paqueteService.actualizarPaquete(paquete));
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
                paqueteService.eliminarPaquete(Integer.parseInt(pathParam));
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
