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
import model.Envio;

/**
 *
 * @author Rony Rojas
 */
public class EnvioDB {

    public Envio crear(Envio envio) {
        String query = "INSERT INTO Envio (idSistema, idPaquete, idRuta) VALUES (?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, envio.getIdSistema());
            statement.setInt(2, envio.getIdPaquete());
            statement.setInt(3, envio.getIdRuta());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                envio.setId(generatedKeys.getInt(1));
                System.out.println("envio creado");
                return envio;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Envio actualizar(Envio envio) {
        String query = "UPDATE Envio SET idSistema = ?, idPaquete = ?, idRuta = ?  WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, envio.getIdSistema());
            statement.setInt(2, envio.getIdPaquete());
            statement.setInt(3, envio.getIdRuta());
            statement.setInt(4, envio.getId());
            statement.execute();
            System.out.println("envio actualizado");
            return envio;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Envio WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("envio eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Envio> getEnvios() {
        List<Envio> envios = new ArrayList<>();
        String query = "SELECT * FROM Envio;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Envio envio = new Envio();
                envio.setId(resultSet.getInt("id"));
                envio.setIdSistema(resultSet.getInt("idSistema"));
                envio.setIdPaquete(resultSet.getInt("idPaquete"));
                envio.setIdRuta(resultSet.getInt("idRuta"));
                envios.add(envio);
            }
            return envios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Envio getEnvioById(int id) {
        String query = "SELECT * FROM Envio WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Envio envio = new Envio();
                envio.setId(resultSet.getInt("id"));
                envio.setIdSistema(resultSet.getInt("idSistema"));
                envio.setIdPaquete(resultSet.getInt("idPaquete"));
                envio.setIdRuta(resultSet.getInt("idRuta"));
                return envio;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
