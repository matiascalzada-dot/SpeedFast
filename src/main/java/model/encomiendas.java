package model;

public class encomiendas extends Pedido {

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
        System.out.println("el repartidor le dará su encomienda una vez se pese y embale");
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
}
