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
import model.Factura;

/**
 *
 * @author Rony Rojas
 */
public class FacturaDB {

    public Factura crear(Factura factura) {
        String query = "INSERT INTO Factura (nitCliente, total, fecha, precioIngreso, precioEnvio) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, factura.getNitCliente());
            statement.setInt(2, factura.getTotal());
            statement.setDate(3, factura.getFecha());
            statement.setInt(4, factura.getPrecioIngreso());
            statement.setInt(5, factura.getPrecioEnvio());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                factura.setId(generatedKeys.getInt(1));
                System.out.println("factura creada");
                return factura;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Factura actualizar(Factura factura) {
        String query = "UPDATE Factura SET nitCliente = ?, total = ?, fecha = ?, precioIngreso = 0, precioEnvio = 0 WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, factura.getNitCliente());
            statement.setInt(2, factura.getTotal());
            statement.setDate(3, factura.getFecha());
            statement.setInt(4, factura.getId());
            statement.setInt(4, factura.getPrecioIngreso());
            statement.setInt(5, factura.getPrecioEnvio());
            statement.execute();
            System.out.println("factura actualizada");
            return factura;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Factura WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("factura eliminada");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Factura> getFacturas() {
        List<Factura> facturas = new ArrayList<>();
        String query = "SELECT * FROM Factura;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Factura factura = new Factura();
                factura.setId(resultSet.getInt("id"));
                factura.setNitCliente(resultSet.getInt("nitCliente"));
                factura.setTotal(resultSet.getInt("total"));
                factura.setFecha(resultSet.getDate("fecha"));
                factura.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                factura.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                facturas.add(factura);
            }
            return facturas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Factura getFacturaById(int id) {
        String query = "SELECT * FROM Factura WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Factura factura = new Factura();
                factura.setId(resultSet.getInt("id"));
                factura.setNitCliente(resultSet.getInt("nitCliente"));
                factura.setTotal(resultSet.getInt("total"));
                factura.setFecha(resultSet.getDate("fecha"));
                factura.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                factura.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                return factura;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
