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
import model.Ruta;

/**
 *
 * @author Rony Rojas
 */
public class RutaDB {

    public Ruta crear(Ruta ruta) {
        String query = "INSERT INTO Ruta (idDestino, activa, cantidadTotalPaquetes, concurrencia) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, ruta.getIdDestino());
            statement.setBoolean(2, ruta.isActiva());
            statement.setInt(3, ruta.getCantidadTotalPaquetes());
            statement.setInt(4, ruta.getConcurrencia());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                ruta.setId(generatedKeys.getInt(1));
                System.out.println("ruta creada");
                return ruta;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Ruta actualizar(Ruta ruta) {
        String query = "UPDATE Ruta SET idDestino = ?, activa = ?, cantidadTotalPaquetes = ?, concurrencia = ? WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, ruta.getIdDestino());
            statement.setBoolean(2, ruta.isActiva());
            statement.setInt(3, ruta.getCantidadTotalPaquetes());
            statement.setInt(4, ruta.getConcurrencia());
            statement.setInt(5, ruta.getId());
            statement.execute();
            System.out.println("ruta actualizada");
            return ruta;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Ruta WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Ruta eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Ruta> getRutas() {
        List<Ruta> rutas = new ArrayList<>();
        String query = "SELECT * FROM Ruta;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setId(resultSet.getInt("id"));
                ruta.setIdDestino(resultSet.getInt("idDestino"));
                ruta.setActiva(resultSet.getBoolean("activa"));
                ruta.setCantidadTotalPaquetes(resultSet.getInt("cantidadTotalPaquetes"));
                ruta.setConcurrencia(resultSet.getInt("concurrencia"));
                rutas.add(ruta);
            }
            return rutas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Ruta> getReporte4(){
         List<Ruta> rutas = new ArrayList<>();
        String query = "SELECT * FROM Ruta ORDER BY concurrencia DESC LIMIT 3;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setId(resultSet.getInt("id"));
                ruta.setIdDestino(resultSet.getInt("idDestino"));
                ruta.setActiva(resultSet.getBoolean("activa"));
                ruta.setCantidadTotalPaquetes(resultSet.getInt("cantidadTotalPaquetes"));
                ruta.setConcurrencia(resultSet.getInt("concurrencia"));
                rutas.add(ruta);
            }
            return rutas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ruta getRutaById(int id) {
        String query = "SELECT * FROM Ruta WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setId(resultSet.getInt("id"));
                ruta.setIdDestino(resultSet.getInt("idDestino"));
                ruta.setActiva(resultSet.getBoolean("activa"));
                ruta.setCantidadTotalPaquetes(resultSet.getInt("cantidadTotalPaquetes"));
                ruta.setConcurrencia(resultSet.getInt("concurrencia"));
                return ruta;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
