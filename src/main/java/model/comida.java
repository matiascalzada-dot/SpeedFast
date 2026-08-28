package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public class comida extends Pedido implements Despachable, Cancelable, Rastreable {
    private String restaurant;

    public comida(int idPedido, String direccionEntrega, String tipoPedido, int distanciaKm, String restaurant) {
        super(idPedido, direccionEntrega, tipoPedido, distanciaKm);
        this.restaurant = restaurant;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void repartidorAsignado(){
        System.out.println("un repartidor con mochila termica le entregará su pedido");
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
    }


    @Override
    public int calcularTiempoEntrega() {
        return 15 + (2 * getDistanciaKm());
    }

    @Override
    public String toString() {
        return "\nPedido de comida: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nReataurant: " + getRestaurant() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }

    @Override
    public void cancelar() {
        System.out.println("Pedido de comida despachado.");

    }

    @Override
    public void despachar() {
        System.out.println("Pedido de comida cancelado.");

    }

    @Override
    public void verHistorial() {
        System.out.println("Historial: Pedido de comida entregado");

    }
}
