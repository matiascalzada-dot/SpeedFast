package data;

import concurrent.Repartidor;
import concurrent.ZonaDeCarga;
import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;
import model.comida;
import model.compraExpress;
import model.encomiendas;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PedidoDAO dao = new PedidoDAO();

        Pedido pedidoModificar = new comida(
                11,
                "Nueva direccion 456",
                "Sushi",
                15,
                "Kami Sushi"
        );

        pedidoModificar.setEstado("EN_REPARTO");

        boolean actualizado = dao.actualizarPedido(pedidoModificar);

        if (actualizado) {
            System.out.println("Pedido actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el pedido.");
        }

        encomiendas teclado = new encomiendas(
                12,
                "Pedro Aguirre 321",
                "Encomienda",
                5,
                2
        );

        boolean resultadoEncomienda = dao.crearPedido(teclado);

        System.out.println("Encomienda guardada: " + resultadoEncomienda);

        compraExpress lapices = new compraExpress(
                13,
                "Juan Errázuriz 674",
                "Express",
                12,
                "Lápices"
        );

        boolean resultadoExpress = dao.crearPedido(lapices);

        System.out.println("Express guardado: " + resultadoExpress);

        ArrayList<Pedido> listaPedidos = dao.listarPedidos();

        System.out.println("\n===== PEDIDOS DESDE MYSQL =====");

        for (Pedido pedido : listaPedidos) {
            System.out.println(pedido);
        }

    }


}
