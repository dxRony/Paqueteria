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
import model.Destino;

/**
 *
 * @author Rony Rojas
 */
public class DestinoDB {

    public Destino crear(Destino destino) {
        String query = "INSERT INTO Destino (id, cuota) VALUES (?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, destino.getId());
            statement.setInt(2, destino.getCuota());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                destino.setId(generatedKeys.getInt(1));
                System.out.println("destino creado");
                return destino;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Destino actualizar(Destino destino) {
        String query = "UPDATE Destino SET cuota = ? WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, destino.getCuota());
            statement.setInt(2, destino.getId());
            statement.execute();
            System.out.println("destino actualizado");
            return destino;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }

    }

    public void eliminar(int id) {
        String query = "DELETE FROM Destino WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("destino eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Destino> getDestinos() {
        List<Destino> destinos = new ArrayList<>();
        String query = "SELECT * FROM Destino;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Destino destino = new Destino();
                destino.setId(resultSet.getInt("id"));
                destino.setCuota(resultSet.getInt("cuota"));
                destinos.add(destino);
            }
            return destinos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Destino getDestinoById(int id) {
        String query = "SELECT * FROM Destino WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Destino destino = new Destino();
                destino.setCuota(resultSet.getInt("cuota"));
                return destino;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
