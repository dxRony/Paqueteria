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
@WebServlet(name = "QuitarPuntoControlServlet", urlPatterns = "/quitarPuntos/*")//idRuta/idPuntoDeControl
public class QuitarPuntoControlServlet extends HttpServlet {

    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private QuitarPuntoDeControlService quitarPuntoService = new QuitarPuntoDeControlService();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String pathInfo = req.getPathInfo();

            String[] info = pathInfo.split("/");

            String mensaje = "";
            if (info.length >= 3) {
                int idRuta = Integer.parseInt(info[1]);
                int idPuntoControl = Integer.parseInt(info[2]);
                String nombreCola = info[1] + "-" + info[2];

                mensaje = quitarPuntoService.quitarPuntoDeControl(idRuta, idPuntoControl, nombreCola);
                this.sendResponse(resp, mensaje);
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
