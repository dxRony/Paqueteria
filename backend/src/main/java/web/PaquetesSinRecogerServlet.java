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
import java.util.List;
import model.Paquete;
import services.PaqueteService;
import util.GsonUtils;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "PaquetesSinRecogerServlet", urlPatterns = "/paquetesSinRecoger")
public class PaquetesSinRecogerServlet extends HttpServlet {

    private PaqueteService paqueteService = new PaqueteService();
    private GsonUtils<Paquete> gsonPaquete = new GsonUtils<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Paquete> paquetesSinRecoger = paqueteService.getPaquetesSinRecoger();
            
            resp.setStatus(HttpServletResponse.SC_OK);
            gsonPaquete.sendAsJson(resp, paquetesSinRecoger);
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
