package data;

import model.Pedido;
import model.comida;
import model.encomiendas;
import model.compraExpress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {
    public boolean crearPedido(Pedido pedido) {

        String sql = "INSERT INTO pedidos "
                + "(id_pedido, direccion_entrega, tipo_pedido, distancia_km, "
                + "estado, repartidor, restaurant, paquetes, detalle, pedido_express) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pedido.getIdPedido());
            sentencia.setString(2, pedido.getDireccionEntrega());
            sentencia.setString(3, pedido.getTipoPedido());
            sentencia.setInt(4, pedido.getDistanciaKm());
            sentencia.setString(5, pedido.getEstadoTexto());
            sentencia.setString(6, pedido.getRepartidor());

            sentencia.setString(7, null);
            sentencia.setObject(8, null);
            sentencia.setString(9, null);
            sentencia.setString(10, null);

            if (pedido instanceof comida) {

                comida pedidoComida = (comida) pedido;

                sentencia.setString(7, pedidoComida.getRestaurant());
                sentencia.setObject(8, null);
                sentencia.setObject(9, null);
                sentencia.setObject(10, null);

            } else if (pedido instanceof encomiendas) {

                encomiendas pedidoEncomienda = (encomiendas) pedido;

                sentencia.setObject(7, null);
                sentencia.setInt(8, pedidoEncomienda.getPaquetes());
                sentencia.setString(9, pedidoEncomienda.getDetalle());
                sentencia.setObject(10, null);

            } else if (pedido instanceof compraExpress) {

                compraExpress pedidoExpress = (compraExpress) pedido;

                sentencia.setObject(7, null);
                sentencia.setObject(8, null);
                sentencia.setObject(9, null);
                sentencia.setString(10, pedidoExpress.getPedido());

            } else {

                sentencia.setObject(7, null);
                sentencia.setObject(8, null);
                sentencia.setObject(9, null);
                sentencia.setObject(10, null);
            }
            sentencia.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al crear el pedido: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Pedido> listarPedidos() {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM pedidos";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int idPedido = resultado.getInt("id_pedido");
                String direccion = resultado.getString("direccion_entrega");
                String tipo = resultado.getString("tipo_pedido");
                int distancia = resultado.getInt("distancia_km");
                String estado = resultado.getString("estado");
                String repartidor = resultado.getString("repartidor");

                Pedido pedido;

                if (resultado.getString("restaurant") != null) {

                    String restaurant = resultado.getString("restaurant");

                    pedido = new comida(
                            idPedido,
                            direccion,
                            tipo,
                            distancia,
                            restaurant
                    );

                } else if (resultado.getObject("paquetes") != null) {

                    int paquetes = resultado.getInt("paquetes");
                    String detalle = resultado.getString("detalle");

                    pedido = new encomiendas(
                            idPedido,
                            direccion,
                            tipo,
                            distancia,
                            paquetes
                    );

                    ((encomiendas) pedido).setDetalle(detalle);

                } else if (resultado.getString("pedido_express") != null) {

                    String pedidoExpress = resultado.getString("pedido_express");

                    pedido = new compraExpress(
                            idPedido,
                            direccion,
                            tipo,
                            distancia,
                            pedidoExpress
                    );

                } else {

                    continue;
                }

                pedido.setEstado(estado);

                if (repartidor != null) {
                    pedido.repartidorAsignado(repartidor);
                }

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return pedidos;
    }
    public boolean actualizarPedido(Pedido pedido) {

        String sql = "UPDATE pedidos SET "
                + "direccion_entrega = ?, "
                + "tipo_pedido = ?, "
                + "distancia_km = ?, "
                + "estado = ?, "
                + "repartidor = ?, "
                + "restaurant = ?, "
                + "paquetes = ?, "
                + "detalle = ?, "
                + "pedido_express = ? "
                + "WHERE id_pedido = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, pedido.getDireccionEntrega());
            sentencia.setString(2, pedido.getTipoPedido());
            sentencia.setInt(3, pedido.getDistanciaKm());
            sentencia.setString(4, pedido.getEstadoTexto());
            sentencia.setString(5, pedido.getRepartidor());

            if (pedido instanceof comida) {

                comida pedidoComida = (comida) pedido;

                sentencia.setString(6, pedidoComida.getRestaurant());
                sentencia.setObject(7, null);
                sentencia.setObject(8, null);
                sentencia.setObject(9, null);

            } else if (pedido instanceof encomiendas) {

                encomiendas pedidoEncomienda = (encomiendas) pedido;

                sentencia.setObject(6, null);
                sentencia.setInt(7, pedidoEncomienda.getPaquetes());
                sentencia.setString(8, pedidoEncomienda.getDetalle());
                sentencia.setObject(9, null);

            } else if (pedido instanceof compraExpress) {

                compraExpress pedidoExpress = (compraExpress) pedido;

                sentencia.setObject(6, null);
                sentencia.setObject(7, null);
                sentencia.setObject(8, null);
                sentencia.setString(9, pedidoExpress.getPedido());

            }

            sentencia.setInt(10, pedido.getIdPedido());

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar el pedido: " + e.getMessage());
            return false;
        }
    }
    public boolean eliminarPedido(int idPedido) {

        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPedido);

            int filasAfectadas = sentencia.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar el pedido: " + e.getMessage());
            return false;
        }
    }
}
