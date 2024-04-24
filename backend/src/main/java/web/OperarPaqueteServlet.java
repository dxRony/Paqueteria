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
import java.util.ArrayList;
import services.ColaEsperaService;
import services.OperarPaqueteService;
import services.PaqueteService;
import services.PuntoDeControlService;
import services.RutaService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "OperarPaqueteServlet", urlPatterns = "/operarPaquete")//operarPaquete?parametros=idPaquete_tiempo
public class OperarPaqueteServlet extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();
    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private RutaService rutaService = new RutaService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private OperarPaqueteService operarPaqueteService = new OperarPaqueteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mensaje = "";
        try {
            String urlParam = req.getParameter("parametros");

            String[] params = urlParam.split("_");

            String idPaqueteString = params[0];
            String tiempoString = params[1];

            int idPaquete = Integer.parseInt(idPaqueteString);
            System.out.println("idPaquete = " + idPaquete);
            int tiempo = Integer.parseInt(tiempoString);
            System.out.println("tiempo = " + tiempo);

            mensaje = operarPaqueteService.operarPaquete(idPaquete, tiempo);

            System.out.println("mensaje = " + mensaje);
            resp.setStatus(HttpServletResponse.SC_OK);
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

    public String obtenerSiguienteCola(ArrayList<String> nombreColas, String nombreColaActual) {
        System.out.println("nombrecolas recibidas");
        for (String nombreCola : nombreColas) {

            System.out.println(nombreCola);
        }
        System.out.println("nombreColaActual = " + nombreColaActual);
        int indiceActual = nombreColas.indexOf(nombreColaActual);
        if (indiceActual != -1 && indiceActual + 1 < nombreColas.size()) {
            return nombreColas.get(indiceActual + 1);
        }
        return null;
    }
}
