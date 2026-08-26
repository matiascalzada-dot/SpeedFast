package model;

public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private String tipoPedido;
    private int distanciaKm;

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

    public void repartidorAsignado(){
        System.out.println("se le ha asignado un repartidor cerca de usted");
    }

    public void mostrarResumen(){
        System.out.println("id del pedido: " + idPedido + "\ndireccion de la entrega: " + direccionEntrega + "\ndistancia del destino: " + distanciaKm);
    }
    public abstract int calcularTiempoEntrega();

    @Override
    public String toString() {
        return "\nPedido: " + getTipoPedido() + "\nID del pedido: " + getIdPedido() + "\nDireccion de entrega: " + getDireccionEntrega() ;
    }
}
