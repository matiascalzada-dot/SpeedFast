package model;


import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class compraExpress extends Pedido implements Despachable, Cancelable, Rastreable {

    private String pedido;

    public compraExpress(int idPedido, String direccionEntrega, String tipoPedido, int distanciaKm, String pedido) {
        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.pedido = pedido;
    }

    public String getPedido() {
        return pedido;
    }


    public void setPedido(String pedido) {
        this.pedido = pedido;
    }


    public void repartidorAsignado(String nombreRepartidor) {
        System.out.println("El repartidor " + nombreRepartidor + " le entregará el/la " + getPedido());
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = 10;

        if (getDistanciaKm() > 5) {
            tiempo += 5;
        }

        return tiempo;
    }
        @Override
        public String toString () {
            return "\nCompra express: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nDireccion de entrega: " + getDireccionEntrega();
        }

    @Override
    public void cancelar() {
        System.out.println("Pedido express despachado.");

    }

    @Override
    public void despachar() {
        System.out.println("Pedido express cancelado.");

    }

    @Override
    public void verHistorial() {
        System.out.println("Historial: Pedido express entregado.");

    }
}


