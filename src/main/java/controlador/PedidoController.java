package controlador;

import model.Pedido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PedidoController {

    private final List<Pedido> pedidos;
    private final List<Runnable> listenersCambio;

    public PedidoController() {
        pedidos = new ArrayList<>();
        listenersCambio = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo.");
        }
        pedidos.add(pedido);
        notificarCambio();
    }

    public List<Pedido> getPedidos() {
        return Collections.unmodifiableList(pedidos);
    }

    public boolean existeId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == id) {
                return true;
            }
        }
        return false;
    }


    public int asignarRepartidores() {
        int asignados = 0;

        for (Pedido pedido : pedidos) {
            String repartidorAnterior = pedido.getRepartidor();
            pedido.repartidorAsignado();

            if (pedido.getRepartidor() != null
                    && !pedido.getRepartidor().isBlank()
                    && !pedido.getRepartidor().equals(repartidorAnterior)) {
                asignados++;
            }
        }

        notificarCambio();
        return asignados;
    }

    public void iniciarEntregas() {
        for (Pedido pedido : pedidos) {
            pedido.despachar();
        }
    }

    public void agregarListenerCambio(Runnable listener) {
        if (listener != null) {
            listenersCambio.add(listener);
        }
    }

    private void notificarCambio() {
        for (Runnable listener : new ArrayList<>(listenersCambio)) {
            listener.run();
        }
    }
}
