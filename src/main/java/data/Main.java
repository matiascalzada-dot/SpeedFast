package data;


import model.Pedido;
import model.comida;
import model.compraExpress;
import model.encomiendas;

public class Main {
    public static void main(String[] args) {

        comida papasFritas = new comida(111,"maipu","papas fritas",12, "olivo limon");
        System.out.println(papasFritas);
        papasFritas.repartidorAsignado();

        encomiendas encomienda1 = new encomiendas(113,"quilicura","cama de gato", 7, 2);
        System.out.println(encomienda1);
        encomienda1.repartidorAsignado();

        compraExpress paraguas = new compraExpress(114,"Pedro de valdivia","articulo para salir", 10,"paraguas");
        System.out.println(paraguas);
        paraguas.repartidorAsignado("Fernando");
        System.out.println();

        papasFritas.mostrarResumen();
        System.out.println("su encomienda llegará en "+papasFritas.calcularTiempoEntrega() + " minutos");
        System.out.println();

        encomienda1.mostrarResumen();
        System.out.println("su encomienda llegará en "+encomienda1.calcularTiempoEntrega() + " minutos");

        System.out.println();
        paraguas.mostrarResumen();
        System.out.println("su encomienda llegará en "+paraguas.calcularTiempoEntrega() + " minutos");
    }
}