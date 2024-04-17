/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import data.AlmacenColas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.ColaEspera;
import model.Destino;
import model.Paquete;
import model.PuntoDeControl;
import model.Ruta;
import util.PaqueteriaApiException;

/**
 *
 * @author ronyrojas
 */
public class EnviarPaqueteService {

    private PaqueteService paqueteService = new PaqueteService();
    private RutaService rutaService = new RutaService();
    private ColaEsperaService colaService = new ColaEsperaService();
    private DestinoService destinoService = new DestinoService();
    private PuntoDeControlService puntoService = new PuntoDeControlService();

    public void enviarPaquetesDeBodegaSistema() throws PaqueteriaApiException {

        List<Paquete> paquetesEnBodega = paqueteService.getPaquetesBodega();//obtener paquetes
        List<Ruta> rutasDisponibles = rutaService.getRutas();//obtener rutas

        for (Paquete paquete : paquetesEnBodega) {
            for (Ruta rutaDisponible : rutasDisponibles) {
                if (rutaDisponible.isActiva()) {
                    if (paquete.getIdDestino() == rutaDisponible.getIdDestino() && !paquete.isEnRuta() && !paquete.isRecogido()) {
                        //si el destino del paquete y el destino de la ruta coinciden y el paquete no esta en ruta (esta en cola bodega)

                        int idRutaDisponible = rutaDisponible.getId();//obtener el id de la ruta coincidente
                        List<ColaEspera> colasRuta = colaService.getColasByRuta(idRutaDisponible);//obtengo las colas de la ruta conicidente

                        ArrayList<String> nombreCola = new ArrayList<>();
                        for (ColaEspera colaEspera : colasRuta) {//guardo los nombres de todas las colas de la rutas                            
                            nombreCola.add(colaEspera.getNombreCola());
                        }
                        Collections.sort(nombreCola, new Comparator<String>() {//ordenando las colas de menor a mayor
                            @Override
                            public int compare(String o1, String o2) {
                                int numero2_1 = Integer.parseInt(o1.split("-")[1]);
                                int numero2_2 = Integer.parseInt(o2.split("-")[1]);
                                return Integer.compare(numero2_1, numero2_2);
                            }
                        });

                        String nombreColaAMandar = nombreCola.get(0);
                        ColaEspera colaInicio = colaService.getColaByName(nombreColaAMandar);//obtener la primera cola de la DB

                        int puntoControl = colaInicio.getIdPuntoDeControl();//obtener el idPuntoDeControl de la cola

                        ArrayList<Paquete> colaPaquete = AlmacenColas.obtenerCola(nombreColaAMandar);//obtener la cola de la lista de listas
                        System.out.println(colaPaquete.size());
                        System.out.println(colaInicio.getCantidadMaximaPaquetes());

                        if (colaPaquete.size() < colaInicio.getCantidadMaximaPaquetes()) {//para ver si cabe otro paquete en la lista

                            //actualizar paquete en DB, para cambiar el punto de control actual al 1er punto de la ruta
                            Destino destino = destinoService.getDestinoById(paquete.getIdDestino());
                            int cuotaDestino = destino.getCuota();
                            int pesoPaquete = paquete.getPeso();
                            int precioPLibra = paquete.getPrecioPLibra();
                            int precioIngreso = cuotaDestino + pesoPaquete * precioPLibra;//determinando el precio de ingreso del paquete

                            paquete.setIdRuta(idRutaDisponible);//agregar la ruta donde encontro espacio disponible
                            paquete.setNitCliente(paquete.getNitCliente());
                            paquete.setIdDestino(paquete.getIdDestino());
                            paquete.setIdPuntoDeControlActual(puntoControl);//agregar el paquete a la primera cola
                            paquete.setTiempo(paquete.getTiempo());
                            paquete.setPeso(paquete.getPeso());
                            paquete.setPrecioEnvio(paquete.getPrecioEnvio());
                            paquete.setIngresado(paquete.isIngresado());
                            paquete.setRecogido(paquete.isRecogido());
                            paquete.setPrecioPLibra(paquete.getPrecioPLibra());
                            paquete.setEnRuta(true);
                            paquete.setPrecioIngreso(precioIngreso);
                            paqueteService.actualizarPaquete(paquete);

                            //agregar paquete a la lista de listas                        
                            AlmacenColas.agregarPaqueteACola(nombreCola.get(0), paquete, true);//se agrega a la lista con el nombre de la cola de la DB

                            //cambiando estado de punto de control 
                            PuntoDeControl punto = puntoService.getPuntoDeControlById(puntoControl);//obteniendo el punto de control donde se manda el paquete
                            punto.setLibre(false);
                            puntoService.actualizarPuntoDeControl(punto);//actualizando punto de control en la DB

                            //actualizando la ruta en la DB
                            int concurrenciaActual = rutaDisponible.getConcurrencia();
                            rutaDisponible.setConcurrencia(concurrenciaActual + 1);
                            int cantidadTotalPaquetes = rutaDisponible.getCantidadTotalPaquetes();
                            rutaDisponible.setCantidadTotalPaquetes(cantidadTotalPaquetes + 1);
                            rutaService.actualizarRuta(rutaDisponible);

                        } else {//si en la cola se supero la cantidad maxima de paquetes, ya no cabe
                            System.out.println("la cola esta llena");
                        }
                    }
                } else {
                    System.out.println("la ruta esta desactivada");
                }

            }
        }
        System.out.println("XD");
    }

}
