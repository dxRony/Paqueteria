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
import model.Empleado;

/**
 *
 * @author Rony Rojas
 */
public class EmpleadoDB {

    public Empleado crear(Empleado empleado) {
        String query = "INSERT INTO Empleado (idSistema, username, password, activo, rol, nombre) VALUES (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, empleado.getIdSistema());
            statement.setString(2, empleado.getUsername());
            statement.setString(3, empleado.getPassword());
            statement.setBoolean(4, empleado.isActivo());
            statement.setInt(5, empleado.getRol());
            statement.setString(6, empleado.getNombre());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                empleado.setId(generatedKeys.getInt(1));
                System.out.println("empleado creado");
                return empleado;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Empleado actualizar(Empleado empleado) {
        String query = "UPDATE Empleado SET idSistema = ?, username = ?, password = ?, activo = ?, rol = ?, nombre = ?  WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, empleado.getIdSistema());
            statement.setString(2, empleado.getUsername());
            statement.setString(3, empleado.getPassword());
            statement.setBoolean(4, empleado.isActivo());
            statement.setInt(5, empleado.getRol());
            statement.setString(6, empleado.getNombre());
            statement.setInt(7, empleado.getId());
            statement.execute();
            System.out.println("Empleado actualizado");
            return empleado;
        } catch (SQLException e) {
            System.out.println("error");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Empleado WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Empleado eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM Empleado;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setIdSistema(resultSet.getInt("idSistema"));
                empleado.setUsername(resultSet.getString("username"));
                empleado.setPassword(resultSet.getString("password"));
                empleado.setActivo(resultSet.getBoolean("activo"));
                empleado.setRol(resultSet.getInt("rol"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleados.add(empleado);
            }
            return empleados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empleado getEmpleadoById(int id) {
        String query = "SELECT * FROM Empleado WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setIdSistema(resultSet.getInt("idSistema"));
                empleado.setUsername(resultSet.getString("username"));
                empleado.setPassword(resultSet.getString("password"));
                empleado.setActivo(resultSet.getBoolean("activo"));
                empleado.setRol(resultSet.getInt("rol"));
                empleado.setNombre(resultSet.getString("nombre"));
                return empleado;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Empleado getEmpleadoLogin(String username, String password) {
        String query = "SELECT * FROM Empleado WHERE username = ? AND password = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setIdSistema(resultSet.getInt("idSistema"));
                empleado.setUsername(resultSet.getString("username"));
                empleado.setPassword(resultSet.getString("password"));
                empleado.setActivo(resultSet.getBoolean("activo"));
                empleado.setRol(resultSet.getInt("rol"));
                empleado.setNombre(resultSet.getString("nombre"));
                return empleado;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
