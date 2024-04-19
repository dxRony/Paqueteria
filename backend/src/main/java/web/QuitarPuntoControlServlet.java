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
import services.PuntoDeControlService;
import services.QuitarPuntoDeControlService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "QuitarPuntoControlServlet", urlPatterns = "/quitarPuntos")//quitarPuntos?parametros=idRuta_idPuntoDeControl
public class QuitarPuntoControlServlet extends HttpServlet {

    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private QuitarPuntoDeControlService quitarPuntoService = new QuitarPuntoDeControlService();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String urlParam = req.getParameter("parametros");

            String[] params = urlParam.split("_");
            String idRutaString = params[0];
            String idPuntoDeControlString = params[1];

            int idRuta = Integer.parseInt(idRutaString);
            System.out.println("idRuta de url = " + idRuta);
            int idPuntoDeControl = Integer.parseInt(idPuntoDeControlString);
            System.out.println("idPuntoDeControl de url = " + idPuntoDeControl);
            
            String nombreCola = params[0] + "-" + params[1];
            String mensaje = "";

            mensaje = quitarPuntoService.quitarPuntoDeControl(idRuta, idPuntoDeControl, nombreCola);

            resp.setStatus(HttpServletResponse.SC_OK);
            
           // this.sendResponse(resp, mensaje);
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
