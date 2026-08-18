package data;


import model.Pedido;
import model.comida;
import model.compraExpress;
import model.encomiendas;

public class Main {
    public static void main(String[] args) {

        Pedido pedido1 = new Pedido(112, "los carmelos", "favor");
        System.out.println(pedido1);
        pedido1.repartidorAsignado();

        comida papasFritas = new comida(111,"maipu","papas fritas", "olivo limon");
        System.out.println(papasFritas);
        papasFritas.repartidorAsignado();

        encomiendas encomienda1 = new encomiendas(113,"quilicura","cama de gato", 2);
        System.out.println(encomienda1);
        encomienda1.repartidorAsignado();

        compraExpress paraguas = new compraExpress(114,"pedro de valdivia","articulo para salir", "paraguas");
        System.out.println(paraguas);
        paraguas.repartidorAsignado("Luchito");

    }
}