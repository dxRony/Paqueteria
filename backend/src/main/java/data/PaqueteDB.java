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
import model.Paquete;

/**
 *
 * @author Rony Rojas
 */
public class PaqueteDB {

    public Paquete crear(Paquete paquete) {
        String query = "INSERT INTO Paquete (idRuta, nitCliente, idDestino,  tiempo, peso, precioEnvio, ingresado, recogido, precioPLibra, enRuta, precioIngreso) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, paquete.getIdRuta());
            statement.setInt(2, paquete.getNitCliente());
            statement.setInt(3, paquete.getIdDestino());
            statement.setInt(4, paquete.getTiempo());
            statement.setInt(5, paquete.getPeso());
            statement.setInt(6, paquete.getPrecioEnvio());
            statement.setBoolean(7, paquete.isIngresado());
            statement.setBoolean(8, paquete.isRecogido());
            statement.setInt(9, paquete.getPrecioPLibra());
            statement.setBoolean(10, paquete.isEnRuta());
            statement.setInt(11, paquete.getPrecioIngreso());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                paquete.setId(generatedKeys.getInt(1));
                System.out.println("paquete creado");
                return paquete;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public Paquete actualizar(Paquete paquete) {
        String query = "UPDATE Paquete SET idRuta = ?, nitCliente = ?, idDestino = ?, idPuntoDeControlActual= ?, tiempo = ?, "
                + "peso = ?, precioEnvio = ?, ingresado = ?, recogido = ?, precioPLibra = ?, enRuta = ?, precioIngreso = ? WHERE id = ?;";

        System.out.println("actualizar paquete:" + paquete.getId());
        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);

            statement.setInt(1, paquete.getIdRuta());
            statement.setInt(2, paquete.getNitCliente());
            statement.setInt(3, paquete.getIdDestino());
            statement.setInt(4, paquete.getIdPuntoDeControlActual());
            statement.setInt(5, paquete.getTiempo());
            statement.setInt(6, paquete.getPeso());
            statement.setInt(7, paquete.getPrecioEnvio());
            statement.setBoolean(8, paquete.isIngresado());
            statement.setBoolean(9, paquete.isRecogido());
            statement.setInt(10, paquete.getPrecioPLibra());
            statement.setBoolean(11, paquete.isEnRuta());
            statement.setInt(12, paquete.getPrecioIngreso());
            statement.setInt(13, paquete.getId());
            statement.execute();
            System.out.println("paquete actualizado");
            return paquete;
        } catch (SQLException e) {
            System.out.println("error de actualizar paquete");
            return null;
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM Paquete WHERE id = ?;";

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Paquete eliminado");

        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

    public List<Paquete> getPaquetes() {
        List<Paquete> paquetes = new ArrayList<>();
        String query = "SELECT * FROM Paquete;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setId(resultSet.getInt("id"));
                paquete.setIdRuta(resultSet.getInt("idRuta"));
                paquete.setNitCliente(resultSet.getInt("nitCliente"));
                paquete.setIdDestino(resultSet.getInt("idDestino"));
                paquete.setIdPuntoDeControlActual(resultSet.getInt("idPuntoDeControlActual"));
                paquete.setTiempo(resultSet.getInt("tiempo"));
                paquete.setPeso(resultSet.getInt("peso"));
                paquete.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                paquete.setIngresado(resultSet.getBoolean("ingresado"));
                paquete.setRecogido(resultSet.getBoolean("recogido"));
                paquete.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                paquete.setEnRuta(resultSet.getBoolean("enRuta"));
                paquete.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                paquetes.add(paquete);
            }
            return paquetes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paquete> getPaquetesBodega() {
        List<Paquete> paquetes = new ArrayList<>();
        String query = "SELECT * FROM Paquete WHERE idPuntoDeControlActual IS NULL;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setId(resultSet.getInt("id"));
                paquete.setIdRuta(resultSet.getInt("idRuta"));
                paquete.setNitCliente(resultSet.getInt("nitCliente"));
                paquete.setIdDestino(resultSet.getInt("idDestino"));
                paquete.setIdPuntoDeControlActual(resultSet.getInt("idPuntoDeControlActual"));
                paquete.setTiempo(resultSet.getInt("tiempo"));
                paquete.setPeso(resultSet.getInt("peso"));
                paquete.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                paquete.setIngresado(resultSet.getBoolean("ingresado"));
                paquete.setRecogido(resultSet.getBoolean("recogido"));
                paquete.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                paquete.setEnRuta(resultSet.getBoolean("enRuta"));
                paquete.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                paquetes.add(paquete);
            }
            return paquetes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Paquete> getPaquetesSinRecoger() {
        List<Paquete> paquetes = new ArrayList<>();
        String query = "SELECT * FROM Paquete WHERE recogido = false AND ingresado = true;";

        try {
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setId(resultSet.getInt("id"));
                paquete.setIdRuta(resultSet.getInt("idRuta"));
                paquete.setNitCliente(resultSet.getInt("nitCliente"));
                paquete.setIdDestino(resultSet.getInt("idDestino"));
                paquete.setIdPuntoDeControlActual(resultSet.getInt("idPuntoDeControlActual"));
                paquete.setTiempo(resultSet.getInt("tiempo"));
                paquete.setPeso(resultSet.getInt("peso"));
                paquete.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                paquete.setIngresado(resultSet.getBoolean("ingresado"));
                paquete.setRecogido(resultSet.getBoolean("recogido"));
                paquete.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                paquete.setEnRuta(resultSet.getBoolean("enRuta"));
                paquete.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                paquetes.add(paquete);
            }
            return paquetes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paquete getPaqueteById(int id) {
        String query = "SELECT * FROM Paquete WHERE id = ?;";
        System.out.println("id recibido en DB=" + id);

        try {
            PreparedStatement statement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Paquete paquete = new Paquete();
                paquete.setId(resultSet.getInt("id"));
                paquete.setIdRuta(resultSet.getInt("idRuta"));
                paquete.setNitCliente(resultSet.getInt("nitCliente"));
                paquete.setIdDestino(resultSet.getInt("idDestino"));
                paquete.setIdPuntoDeControlActual(resultSet.getInt("idPuntoDeControlActual"));
                paquete.setTiempo(resultSet.getInt("tiempo"));
                paquete.setPeso(resultSet.getInt("peso"));
                paquete.setPrecioEnvio(resultSet.getInt("precioEnvio"));
                paquete.setIngresado(resultSet.getBoolean("ingresado"));
                paquete.setRecogido(resultSet.getBoolean("recogido"));
                paquete.setPrecioPLibra(resultSet.getInt("precioPLibra"));
                paquete.setEnRuta(resultSet.getBoolean("enRuta"));
                paquete.setPrecioIngreso(resultSet.getInt("precioIngreso"));
                return paquete;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
