package bbdd;

import modelos.Mesa;
import modelos.ModeloPedido;
import modelos.Producto;
import windows.camarero.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static bbdd.Configuracion.cerrarConexion;
import static bbdd.Configuracion.conectarConBD;

public class PedidoBD extends Configuracion{
    public static void crearActualizarPedido(ModeloPedido p) {

        List<ModeloPedido> pedidoBD = obtenerPormesa(p.getMesa());
        for (ModeloPedido mp : pedidoBD) {
            if (pedidoBD.size() == 0) {
                crearPedido(mp);
            } else if (mp.getProducto() != null) {
                actualizarPedido(mp);
            }

        }
    }
    public static void crearPedido(ModeloPedido pedido){
        Connection con = conectarConBD();

        try {
            PreparedStatement insert = con.prepareStatement("insert into pedido (id, mesa,camarero, producto, cantidad)" +
                    "values(?,?,?, ?,?)");

            insert.setInt(1, pedido.getId());
            insert.setInt(2, pedido.getMesa());
            insert.setString(3, pedido.getCamarero());
            insert.setString(4, pedido.getProducto());
            insert.setInt(5, pedido.getCantidad());


            //Ejecución del insert
            insert.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }
    public static List<ModeloPedido> obtenerPormesa(Integer mesa) {

        Connection con = conectarConBD();
        ModeloPedido num_mesa = null;
        List<ModeloPedido> lista_mesa = new ArrayList<>();

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM pedido where mesa = ?  ");
            query.setInt(1, mesa);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                num_mesa = new ModeloPedido(rs.getInt("id"), rs.getInt("mesa"), rs.getString("camarero"), rs.getString("producto"), rs.getInt("cantidad"));
                lista_mesa.add(num_mesa);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return lista_mesa;
    }

    public static void eliminarPedidos(ModeloPedido mesa){
        Connection con = conectarConBD();

        try {
            PreparedStatement delete = con.prepareStatement("delete from pedido where mesa = ? ");

            delete.setInt(1, mesa.getMesa());

            //Ejecución del delete
            delete.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }
    public static void eliminarPedidosProducto(ModeloPedido producto){
        Connection con = conectarConBD();

        try {
            PreparedStatement delete = con.prepareStatement("delete from pedido where producto = ? and mesa = ?");

            delete.setString(1, producto.getProducto());
            delete.setInt(2, producto.getMesa());

            //Ejecución del delete
            delete.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }
    public static void actualizarPedido(ModeloPedido productoMesa){
        Connection con = conectarConBD();
        try {
            PreparedStatement update = con.prepareStatement("update pedido " +
                    "set id = ? , camarero = ?, cantidad = ?" +
                    "where mesa = ? and producto = ?");

            update.setInt(1, productoMesa.getId());
            update.setString(2, productoMesa.getCamarero());
            update.setInt(3, productoMesa.getCantidad());
            update.setInt(4, productoMesa.getMesa());
            update.setString(5, productoMesa.getProducto());


            //Ejecución del update
            update.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }




}