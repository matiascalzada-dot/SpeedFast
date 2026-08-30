package model;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;
    private int distanciaKm;
    protected String repartidor;

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido, int distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
    }


    public int getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(int distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public abstract void repartidorAsignado();

    public void repartidorAsignado(String nombre){
        this.repartidor = nombre;
        System.out.println("Repartidor asignado manualmente" + nombre);
    }

    public void mostrarResumen(){
        System.out.println("\nid del pedido: " + idPedido + "\ndireccion de la entrega: " + direccionEntrega + "\ndistancia del destino: " + distanciaKm);
    }
    public abstract int calcularTiempoEntrega();


    @Override
    public String toString() {
        return "\nPedido: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }
}
