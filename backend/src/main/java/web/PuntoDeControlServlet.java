/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import com.google.gson.Gson;
import data.AlmacenColas;
import data.CargarColas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.ColaEspera;
import model.Paquete;
import model.PuntoDeControl;
import model.Sistema;
import services.ColaEsperaService;
import services.PuntoDeControlService;
import services.SistemaService;
import util.GsonUtils;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
@WebServlet(name = "PuntoDeControlServlet", urlPatterns = "/puntos/*")
public class PuntoDeControlServlet extends HttpServlet {

    private PuntoDeControlService puntoService = new PuntoDeControlService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private CargarColas cargarColas = new CargarColas();
    private SistemaService sistemaService = new SistemaService();
    private GsonUtils<PuntoDeControl> gsonPunto = new GsonUtils<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {//obtenerRecurso

        try {
            if (req.getPathInfo() != null) {//acceder a un puntoDeControl

                PuntoDeControl punto = new PuntoDeControl();
                String pathParam = req.getPathInfo().replace("/", "");//componiendoUrl
                this.sendResponse(resp, puntoService.getPuntoDeControlById(Integer.parseInt(pathParam)));
                punto = puntoService.getPuntoDeControlById(Integer.parseInt(pathParam));

                //obteniendo cola de la lista de colas
                ArrayList<Paquete> cola = AlmacenColas.obtenerCola("" + punto.getId());

                //obteniendo la cola de la DB
                ColaEspera colaEspera = new ColaEspera();
                colaEspera = colaService.getColaByName("" + punto.getId());

            } else {//acceder todos los empleados

                this.sendResponse(resp, puntoService.getPuntosDeControl());
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
            PuntoDeControl puntoRecibido = gsonPunto.readFromJson(req, PuntoDeControl.class);
            System.out.println("punto recibido" + puntoRecibido.toString());

            if (puntoRecibido.getTarifaOperacion() == 0) {
                Sistema sistema = sistemaService.getSistemaById(1);
                int tarifaOperacionGlobal = sistema.getTarifaOperacionGlobal();
                puntoRecibido.setTarifaOperacion(tarifaOperacionGlobal);
            }
            puntoRecibido.setLibre(true);
            PuntoDeControl puntoCreado = puntoService.crearPuntoDeControl(puntoRecibido);
            
            gsonPunto.sendAsJson(resp, puntoCreado);
            resp.setStatus(HttpServletResponse.SC_OK);
            System.out.println("punto enviado = " + puntoCreado.toString());

            //creando cola en base de datos
            ColaEspera cola = new ColaEspera();
            cola.setIdPuntoDeControl(puntoRecibido.getId());
            cola.setIdRuta(puntoRecibido.getIdRuta());
            cola.setCantidadMaximaPaquetes(puntoRecibido.getCantidadMaximaPaquetes());
            cola.setNombreCola(puntoRecibido.getIdRuta() + "-" + puntoRecibido.getId());
            colaService.crearCola(cola);

            //creando lista para contener los paquetes de la cola
            ArrayList<Paquete> colaNueva = new ArrayList<>();
            AlmacenColas.agregarCola("" + puntoRecibido.getId(), colaNueva);

        } catch (PaqueteriaApiException e) {
            this.sendError(resp, e);
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
            PuntoDeControl punto = gson.fromJson(req.getReader(), PuntoDeControl.class);
            this.sendResponse(resp, puntoService.actualizarPuntoDeControl(punto));
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
                colaService.eliminarColaByName(pathParam);
                puntoService.eliminarPuntoDeControl(Integer.parseInt(pathParam));
                AlmacenColas.eliminarCola(pathParam);

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
