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
import java.text.SimpleDateFormat;
import java.util.Date;
import services.ClienteService;
import services.FacturaService;
import services.PaqueteService;
import services.RecogerPaqueteService;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "RecogerPaqueteServlet", urlPatterns = "/recogerPaquete")//recogerPaquete?parametros=2_2024-04-05
public class RecogerPaqueteServlet extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();
    private FacturaService facturaService = new FacturaService();
    private ClienteService clienteService = new ClienteService();
    private RecogerPaqueteService recogerPaqueteService = new RecogerPaqueteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String urlParam = req.getParameter("parametros");
            String[] params = urlParam.split("_");
            String idPaqueteString = params[0];//obteniendo el id del paquete de la urlc
            String fechaString = params[1];//obteniendo la fecha de la url

            int idPaquete = Integer.parseInt(idPaqueteString);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (urlParam != null) {

                Date fechaUtil = format.parse(fechaString);//parseando a utildate fecha
                java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
                System.out.println("fecha = " + fechaSql);
                System.out.println("idPaquete = " + idPaquete);

                recogerPaqueteService.recogerPaquete(fechaSql, idPaquete);

            }
            this.sendResponse(resp, facturaService.getFacturas());

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
