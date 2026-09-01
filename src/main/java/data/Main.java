package data;


import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;
import model.comida;
import model.compraExpress;
import model.encomiendas;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        Pedido sushi = new comida(1, "san martin 123", "sushi", 9, "kami sushi");
        Pedido teclado = new encomiendas(2, "pedro aguirre 321", "teclado", 5, 2);
        Pedido lapices = new compraExpress(3, "juan errazuris 674", "utiles escolares", 12, "lapices");
        Pedido empanadas = new comida(4, "pedro ruiz 579", "empanadas", 7, "empanadas Maipu");

        pedidos.add(sushi);
        pedidos.add(teclado);
        pedidos.add(lapices);
        pedidos.add(empanadas);

        for (Pedido pedido : pedidos){
            if (pedido instanceof encomiendas || pedido instanceof compraExpress) {
                pedido.repartidorAsignado();
            }
        }



        sushi.repartidorAsignado("miguel");
        empanadas.repartidorAsignado("amaro");

        for (Pedido pedido : pedidos) {
            pedido.mostrarResumen();
        }


        System.out.println();
        System.out.println("--- DESPACHO ---");
        for (Pedido pedido : pedidos) {

            if (pedido instanceof Despachable) {

                Despachable despachable = (Despachable) pedido;

                despachable.despachar();
            }
        }

        System.out.println();
        System.out.println("--- CANCELACIÓN ---");

        if (teclado instanceof Cancelable) {

            Cancelable cancelable = (Cancelable) teclado;

            cancelable.cancelar();
        }

        System.out.println();
        System.out.println("--- HISTORIAL DE ENTREGAS ---");

        for (Pedido pedido : pedidos) {

            if (pedido instanceof Rastreable) {

                Rastreable rastreable = (Rastreable) pedido;

                rastreable.verHistorial();
            }
        }
    }
}
