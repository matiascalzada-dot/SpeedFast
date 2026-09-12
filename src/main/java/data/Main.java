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
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        List<Pedido> pedidos = new ArrayList<>();

        Pedido sushi = new comida(1, "san martin 123", "sushi", 9, "kami sushi");
        Pedido teclado = new encomiendas(2, "pedro aguirre 321", "teclado", 5, 2);
        Pedido lapices = new compraExpress(3, "juan errazuris 674", "utiles escolares", 12, "lapices");
        Pedido empanadas = new comida(4, "pedro ruiz 579", "empanadas", 7, "empanadas Maipu");
        Pedido documentos = new encomiendas(5, "providencia 1450", "documentos", 4, 1);

        pedidos.add(sushi);
        pedidos.add(teclado);
        pedidos.add(lapices);
        pedidos.add(empanadas);
        pedidos.add(documentos);

        System.out.println("[Zona de carga inicializada]");

        for (Pedido pedido : pedidos) {
            zonaDeCarga.agregarPedido(pedido);
        }



        System.out.println("\n--- INICIO DE REPARTIDORES ---");
        Thread juan = new Thread(new Repartidor("Juan", zonaDeCarga));
        Thread camila = new Thread(new Repartidor("Camila", zonaDeCarga));
        Thread pedro = new Thread(new Repartidor("Pedro", zonaDeCarga));

        juan.start();
        camila.start();
        pedro.start();
        try {
            juan.join();
            camila.join();
            pedro.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Proceso principal interrumpido.");
        }

        System.out.println("\n--- ESTADO FINAL ---");
        boolean todosEntregados = true;
        for (Pedido pedido : pedidos) {
            System.out.println("Pedido #" + pedido.getIdPedido()
                    + " -> " + pedido.getEstado()
                    + " | Repartidor: " + pedido.getRepartidor());
            if (pedido.getEstado().name().equals("ENTREGADO") == false) {
                todosEntregados = false;
            }
        }

        System.out.println();
        if (todosEntregados) {
            System.out.println("Todos los pedidos han sido entregados correctamente");
        } else {
            System.out.println("Quedaron pedidos sin entregar.");
        }

        System.out.println("\n--- CANCELACION ---");
        if (pedidos instanceof Cancelable) {
            ((Cancelable) teclado).cancelar();
        }

        System.out.println("\n--- HISTORIAL DE ENTREGAS ---");
        for (Pedido pedido : pedidos) {
            if (pedido instanceof Rastreable) {
                ((Rastreable) pedido).verHistorial();
            }
        }
    }
}
