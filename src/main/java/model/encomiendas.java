package model;

public class encomiendas extends Pedido {

    private int paquetes;

    public encomiendas(int idPedido, String direccionEntrega, String tipoPedido, int paquetes) {
        super(idPedido, direccionEntrega, tipoPedido);
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
    public String toString() {
        return "\nEncomienda: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nCantidad: " + getPaquetes() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }
}
