/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rony Rojas
 */
public final class Conexion {

    private static Conexion instancia;
    private Connection conexion;

    private final String url = "jdbc:mysql://localhost:3306/Paqueteria";
    private final String user = "dxRony"; // "rony_rojas"
    private final String password = "5812";

    public static Conexion obtenerInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection obtenerConexion() {

        try {
            if (conexion == null) {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conexion = DriverManager.getConnection(url, user, password);
                System.out.println("Conexion exitosa");
            }
            return conexion;

        } catch (SQLException e) {
            System.out.println("Error al registrar el driver de mysql: " + e);
        }
        return conexion;
    }

}
