package concurrent;

import model.EstadoPedido;
import model.Pedido;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {

    private final Queue<Pedido> pedidos = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        if (p != null) {
            p.setEstado(EstadoPedido.PENDIENTE);
            pedidos.offer(p);
            System.out.println("[Zona de carga] Pedido #" + p.getIdPedido()
                    + " agregado. Destino: " + p.getDireccionEntrega());
        }
    }

    public synchronized Pedido retirarPedido() {
        while (!pedidos.isEmpty()) {
            Pedido pedido = pedidos.poll();
            if (pedido != null && pedido.getEstado() == EstadoPedido.PENDIENTE) {
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                return pedido;
            }
        }
        return null;
    }

    public synchronized boolean hayPedidosPendientes() {
        return !pedidos.isEmpty();
    }
}
