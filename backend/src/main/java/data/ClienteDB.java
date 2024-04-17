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
import model.Cliente;

/**
 *
 * @author Rony Rojas
 */
public class ClienteDB {

    public Cliente crear(Cliente cliente) {
        String query = "INSERT INTO Cliente (nit, idSistema, activo, nombre) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cliente.getNit());
            statement.setInt(2, cliente.getIdSistema());
            statement.setBoolean(3, cliente.isActivo());
            statement.setString(4, cliente.getNombre());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                cliente.setNit(generatedKeys.getInt(1));
                System.out.println("cliente creado");
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Cliente actualizar(Cliente cliente) {
        String query = "UPDATE Cliente SET idSistema = ?, activo = ?, nombre = ?  WHERE nit = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, cliente.getIdSistema());
            statement.setBoolean(2, cliente.isActivo());
            statement.setString(3, cliente.getNombre());
            statement.setInt(4, cliente.getNit());
            statement.execute();
            System.out.println("Cliente actualizado");
            return cliente;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int nit) {
        String query = "DELETE FROM Cliente WHERE nit = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, nit);
            statement.execute();
            System.out.println("Cliente eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getInt("nit"));
                cliente.setIdSistema(resultSet.getInt("idSistema"));
                cliente.setActivo(resultSet.getBoolean("activo"));
                cliente.setNombre(resultSet.getString("nombre"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente getClienteByNit(int nit) {
        String query = "SELECT * FROM Cliente WHERE nit = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(nit));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setNit(resultSet.getInt("nit"));
                cliente.setIdSistema(resultSet.getInt("idSistema"));
                cliente.setActivo(resultSet.getBoolean("activo"));
                cliente.setNombre(resultSet.getString("nombre"));
                return cliente;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
