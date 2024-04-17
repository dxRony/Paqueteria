/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PuntoDeControl;

/**
 *
 * @author Rony Rojas
 */
public class PuntoDeControlDB {

    public PuntoDeControl crear(PuntoDeControl puntoDeControl) {
        String query = "INSERT INTO PuntoDeControl (idRuta, idEmpleado, tarifaOperacion, libre, cantidadMaximaPaquetes) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, puntoDeControl.getIdRuta());
            statement.setInt(2, puntoDeControl.getIdEmpleado());
            statement.setInt(3, puntoDeControl.getTarifaOperacion());
            statement.setBoolean(4, puntoDeControl.isLibre());
            statement.setInt(5, puntoDeControl.getCantidadMaximaPaquetes());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                puntoDeControl.setId(generatedKeys.getInt(1));
                System.out.println("empleado creado");
                return puntoDeControl;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public PuntoDeControl actualizar(PuntoDeControl puntoDeControl) {
        String query = "UPDATE PuntoDeControl SET idRuta = ?, idEmpleado = ?, tarifaOperacion = ?, libre = ?, cantidadMaximaPaquetes = ?  WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, puntoDeControl.getIdRuta());
            statement.setInt(2, puntoDeControl.getIdEmpleado());
            statement.setInt(3, puntoDeControl.getTarifaOperacion());
            statement.setBoolean(4, puntoDeControl.isLibre());
            statement.setInt(5, puntoDeControl.getCantidadMaximaPaquetes());
            statement.setInt(6, puntoDeControl.getId());
            statement.execute();
            System.out.println("punto actualizado");
            return puntoDeControl;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM PuntoDeControl WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("punto eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public void eliminar2(int id, int ruta) {
        String query = "DELETE FROM PuntoDeControl WHERE id = ? AND idRuta = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, ruta);
            statement.execute();
            System.out.println("punto eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<PuntoDeControl> getPuntosDeControl() {
        List<PuntoDeControl> puntos = new ArrayList<>();
        String query = "SELECT * FROM PuntoDeControl;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                PuntoDeControl punto = new PuntoDeControl();
                punto.setId(resultSet.getInt("id"));
                punto.setIdRuta(resultSet.getInt("idRuta"));
                punto.setIdEmpleado(resultSet.getInt("idEmpleado"));
                punto.setTarifaOperacion(resultSet.getInt("tarifaOperacion"));
                punto.setLibre(resultSet.getBoolean("libre"));
                punto.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                puntos.add(punto);
            }
            return puntos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PuntoDeControl getPuntoDeControlById(int id) {
        String query = "SELECT * FROM PuntoDeControl WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PuntoDeControl punto = new PuntoDeControl();
                punto.setId(resultSet.getInt("id"));
                punto.setIdRuta(resultSet.getInt("idRuta"));
                punto.setIdEmpleado(resultSet.getInt("idEmpleado"));
                punto.setTarifaOperacion(resultSet.getInt("tarifaOperacion"));
                punto.setLibre(resultSet.getBoolean("libre"));
                punto.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                return punto;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
