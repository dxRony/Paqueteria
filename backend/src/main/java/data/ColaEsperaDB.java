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
import model.ColaEspera;

/**
 *
 * @author Rony Rojas
 */
public class ColaEsperaDB {

    public ColaEspera crear(ColaEspera colaEspera) {
        String query = "INSERT INTO ColaEspera (idPuntoDeControl, idRuta, cantidadMaximaPaquetes, nombreCola) VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, colaEspera.getIdPuntoDeControl());
            statement.setInt(2, colaEspera.getIdRuta());
            statement.setInt(3, colaEspera.getCantidadMaximaPaquetes());
            statement.setString(4, colaEspera.getNombreCola());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                colaEspera.setId(generatedKeys.getInt(1));
                System.out.println("cola creada");
                return colaEspera;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public ColaEspera actualizar(ColaEspera colaEspera) {
        String query = "UPDATE ColaEspera SET idPuntoDeControl = ?, idRuta = ?, cantidadMaximaPaquetes = ?, nombreCola = ? WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, colaEspera.getIdPuntoDeControl());
            statement.setInt(2, colaEspera.getIdRuta());
            statement.setInt(3, colaEspera.getCantidadMaximaPaquetes());
            statement.setString(4, colaEspera.getNombreCola());
            statement.setInt(5, colaEspera.getId());
            statement.execute();
            System.out.println("colaEspera actualizada");
            return colaEspera;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminarByName(String nombreCola) {
        String query = "DELETE FROM ColaEspera WHERE nombreCola = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, nombreCola);
            statement.execute();
            System.out.println("colaEspera eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<ColaEspera> getColasEspera() {
        List<ColaEspera> colasEspera = new ArrayList<>();
        String query = "SELECT * FROM ColaEspera;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ColaEspera colaEspera = new ColaEspera();
                colaEspera.setId(resultSet.getInt("id"));
                colaEspera.setIdPuntoDeControl(resultSet.getInt("idPuntoDeControl"));
                colaEspera.setIdRuta(resultSet.getInt("idRuta"));
                colaEspera.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                colaEspera.setNombreCola(resultSet.getString("nombreCola"));
                colasEspera.add(colaEspera);
            }
            return colasEspera;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ColaEspera> getColasEsperaByRuta(int idRuta) {
        List<ColaEspera> colasEspera = new ArrayList<>();
        String query = "SELECT * FROM ColaEspera WHERE idRuta = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, idRuta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ColaEspera colaEspera = new ColaEspera();
                colaEspera.setId(resultSet.getInt("id"));
                colaEspera.setIdPuntoDeControl(resultSet.getInt("idPuntoDeControl"));
                colaEspera.setIdRuta(resultSet.getInt("idRuta"));
                colaEspera.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                colaEspera.setNombreCola(resultSet.getString("nombreCola"));
                colasEspera.add(colaEspera);
            }
            return colasEspera;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ColaEspera getColaEsperaById(int id) {
        String query = "SELECT * FROM ColaEspera WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ColaEspera colaEspera = new ColaEspera();
                colaEspera.setId(resultSet.getInt("id"));
                colaEspera.setIdPuntoDeControl(resultSet.getInt("idPuntoDeControl"));
                colaEspera.setIdRuta(resultSet.getInt("idRuta"));
                colaEspera.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                colaEspera.setNombreCola(resultSet.getString("nombreCola"));
                return colaEspera;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ColaEspera getColaEsperaByIdPuntoDeControlActual(int id) {
        String query = "SELECT * FROM ColaEspera WHERE idPuntoDeControl = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ColaEspera colaEspera = new ColaEspera();
                colaEspera.setId(resultSet.getInt("id"));
                colaEspera.setIdPuntoDeControl(resultSet.getInt("idPuntoDeControl"));
                colaEspera.setIdRuta(resultSet.getInt("idRuta"));
                colaEspera.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                colaEspera.setNombreCola(resultSet.getString("nombreCola"));
                return colaEspera;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ColaEspera getColaEsperaByName(String nombreCola) {
        String query = "SELECT * FROM ColaEspera WHERE nombreCola = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, nombreCola);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ColaEspera colaEspera = new ColaEspera();
                colaEspera.setId(resultSet.getInt("id"));
                colaEspera.setIdPuntoDeControl(resultSet.getInt("idPuntoDeControl"));
                colaEspera.setIdRuta(resultSet.getInt("idRuta"));
                colaEspera.setCantidadMaximaPaquetes(resultSet.getInt("cantidadMaximaPaquetes"));
                colaEspera.setNombreCola(resultSet.getString("nombreCola"));
                return colaEspera;
            }
        } catch (Exception e) {
        }

        return null;
    }
}
