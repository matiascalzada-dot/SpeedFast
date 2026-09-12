package concurrent;

import model.EstadoPedido;
import model.Pedido;

public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            pedido.repartidorAsignado(nombre);
            System.out.println("\n[Repartidor - " + nombre + "] Retirando pedido #"
                    + pedido.getIdPedido() + "...");
            System.out.println("\n[Repartidor - " + nombre + "] Estado: "
                    + pedido.getEstado());
            System.out.println("\n[Repartidor - " + nombre + "] Entregando pedido #"
                    + pedido.getIdPedido() + "...");

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("[Repartidor - " + nombre + "] Hilo interrumpido.");
                return;
            }

            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("\n[Repartidor - " + nombre + "] Estado: "
                    + pedido.getEstado());
        }
    }
}
