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

    @Override
    public void repartidorAsignado() {
        this.repartidor = "Repartidor express";
        System.out.println("Repartidor asignado automáticamente: " + repartidor);
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempo = getDistanciaKm();

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
        System.out.println("Pedido express cancelado.");

    }

    @Override
    public void despachar() {
        System.out.println("\nPedido express despachado\ntiempo de espera: " + calcularTiempoEntrega() + " minutos");

    }

    @Override
    public void verHistorial() {
        System.out.println("Historial: Pedido express entregado.");

    }
}


