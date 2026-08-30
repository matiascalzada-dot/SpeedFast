package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class encomiendas extends Pedido implements Despachable, Cancelable, Rastreable {

    private int paquetes;

    public encomiendas(int idPedido, String direccionEntrega, String tipoPedido, int distanciaKm, int paquetes) {
        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.paquetes = paquetes;
    }

    public int getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(int paquetes) {
        this.paquetes = paquetes;
    }

    @Override
    public void repartidorAsignado(){
        this.repartidor = "Repartidor encomienda";
        System.out.println("repartidor asignado automaticamente: " + repartidor);
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) Math.round(20 + (1.5 * getDistanciaKm()));
    }

    @Override
    public String toString() {
        return "\nEncomienda: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nCantidad: " + getPaquetes() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }

    @Override
    public void cancelar() {
        System.out.println("Encomienda cancelada.");

    }

    @Override
    public void despachar() {
        System.out.println("\nEncomienda despachada\ntiempo de espera: " + calcularTiempoEntrega() + " minutos");

    }

    @Override
    public void verHistorial() {
        System.out.println("Historial: Encomienda entregada.");

    }
}
