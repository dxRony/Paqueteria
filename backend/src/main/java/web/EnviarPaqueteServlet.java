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
import services.ColaEsperaService;
import services.DestinoService;
import services.EnviarPaqueteService;
import services.PaqueteService;
import services.PuntoDeControlService;
import services.RutaService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "EnviarPaqueteServlet", urlPatterns = "/enviarPaquetes")
public class EnviarPaqueteServlet extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();
    private RutaService rutaService = new RutaService();
    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private DestinoService destinoService = new DestinoService();
    private EnviarPaqueteService enviarPaqueteService = new EnviarPaqueteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //envia todos los paquetes posibles a las rutas libres, de la cola de la bodega, llamar cuando se opere a un paquete
        try {
            enviarPaqueteService.enviarPaquetesDeBodegaSistema();
            this.sendResponse(resp, paqueteService.getPaquetes());
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
