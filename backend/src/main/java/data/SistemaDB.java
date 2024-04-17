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
import model.Sistema;

/**
 *
 * @author Rony Rojas
 */
public class SistemaDB {

    public Sistema crear(Sistema sistema) {
        String query = "INSERT INTO Sistema (precioPLibra, tarifaOperacionGlobal) VALUES (?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sistema.getPrecioPLibra());
            statement.setInt(2, sistema.getTarifaOperacionGlobal());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                sistema.setId(generatedKeys.getInt(1));
                System.out.println("sistema creado");
                return sistema;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Sistema actualizar(Sistema sistema) {
        String query = "UPDATE Sistema SET precioPLibra = ?, tarifaOperacionGlobal = ? WHERE id = ?;";
        System.out.println(sistema.toString());
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, sistema.getPrecioPLibra());
            statement.setInt(2, sistema.getTarifaOperacionGlobal());
            statement.setInt(3, sistema.getId());
            statement.executeUpdate();
            System.out.println("sistema actualizado");
            return sistema;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Sistema WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("sistema eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Sistema> getSistemas() {
        List<Sistema> sistemas = new ArrayList<>();
        String query = "SELECT * FROM Sistema;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Sistema sistema = new Sistema();
                sistema.setId(resultSet.getInt("id"));
                sistema.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                sistema.setTarifaOperacionGlobal(resultSet.getInt("tarifaOperacionGlobal"));
                sistemas.add(sistema);
            }
            return sistemas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Sistema getSistemaById(int id) {
        String query = "SELECT * FROM Sistema WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Sistema sistema = new Sistema();
                sistema.setId(resultSet.getInt("id"));
                sistema.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                sistema.setTarifaOperacionGlobal(resultSet.getInt("tarifaOperacionGlobal"));
                return sistema;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
