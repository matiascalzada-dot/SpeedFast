package model;


public class compraExpress extends Pedido {

    private String pedido;

    public compraExpress(int idPedido, String direccionEntrega, String tipoPedido, String pedido) {
        super(idPedido, direccionEntrega, tipoPedido);
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
    public String toString() {
        return "\nCompra express: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }

}
