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
        Pedido empanadas = new encomiendas(2, "pedro aguirre 321", "empanadas", 5, 2);
        Pedido lapices = new compraExpress(3, "juan errazuris", "utiles escolares", 12, "lapices");


        pedidos.add(sushi);
        pedidos.add(empanadas);
        pedidos.add(lapices);

        compraExpress escoba = new compraExpress(3, "pedro ruiz", "aseo", 7, "escoba");

        escoba.repartidorAsignado("juan");

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

        if (empanadas instanceof Cancelable) {

            Cancelable cancelable = (Cancelable) empanadas;

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
