/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.HashMap;
import model.Paquete;

/**
 *
 * @author ronyrojas
 */
public class AlmacenColas {

    private static HashMap<String, ArrayList<Paquete>> colasEspera = new HashMap<>();

    public static void agregarCola(String nombreCola, ArrayList<Paquete> cola) {
        if (!colasEspera.containsKey(nombreCola)) {
            colasEspera.put(nombreCola, cola);
        } else {
            System.out.println("la cola con nombre: " + nombreCola + " ya existe");
        }
    }

    public static boolean isColaVacia(String nombreCola) {
        ArrayList<Paquete> lista = colasEspera.get(nombreCola);

        return lista == null || lista.isEmpty();
    }

    public static void eliminarCola(String nombreCola) {
        if (!colasEspera.containsKey(nombreCola)) {
            System.out.println("la cola con nombre: " + nombreCola + " ya no existe");
        } else {
            colasEspera.remove(nombreCola);
        }
    }

    public static ArrayList<Paquete> obtenerCola(String nombre) {
        return colasEspera.get(nombre);
    }

    public static ArrayList<String> obtenerNombresColas() {
        return new ArrayList<>(colasEspera.keySet());
    }

    public static void agregarPaqueteACola(String nombreCola, Paquete paquete, boolean agregar) {
        ArrayList<Paquete> lista = colasEspera.get(nombreCola);
        if (agregar) {
            System.out.println("entrando a agregar");
            if (lista != null) {
                lista.add(paquete);
                System.out.println("paquete agregado: " + paquete.toString());
                System.out.println("a cola: " + nombreCola);
                //lista.remove(paquete);
            }
        } else {
            System.out.println("entrando a eliminar");
            if (lista != null) {

                System.out.println("paquete eliminado: " + paquete.toString());
                System.out.println("de cola: " + nombreCola);
                lista.remove(paquete);
            } else {
                System.out.println("el paquete no existe");
            }
        }

        // System.out.println("paquete con id= " + paquete.getId() + ", agregado a ruta con id= " + paquete.getIdRuta() + ", en el punto de control:" + paquete.getIdPuntoDeControlActual());
    }

    public static Paquete getPaqueteById(String nombreCola, int idPaquete) {
        ArrayList<Paquete> list = colasEspera.get(nombreCola);
        if (list != null) {
            for (Paquete paquete : list) {
                if (paquete.getId() == idPaquete) {
                    return paquete;
                }
            }
        }
        return null;
    }

}
