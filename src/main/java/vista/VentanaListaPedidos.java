package vista;

import controlador.PedidoController;
import model.Pedido;
import model.encomiendas;
import model.comida;
import model.compraExpress;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {

    private final PedidoController controller;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaPedidos;

    public VentanaListaPedidos(PedidoController controller) {
        this.controller = controller;

        setTitle("SpeedFast - Lista de pedidos");
        setSize(850, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Pedidos registrados", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 10, 5, 10));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Dirección", "Tipo", "Detalle", "Distancia (km)", "Repartidor", "Tiempo (min)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPedidos = new JTable(modeloTabla);
        tablaPedidos.setRowHeight(25);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnCerrar = new JButton("Cerrar");
        panelInferior.add(btnRefrescar);
        panelInferior.add(btnCerrar);
        add(panelInferior, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> refrescarTabla());
        btnCerrar.addActionListener(e -> dispose());

        controller.agregarListenerCambio(this::refrescarTabla);
        refrescarTabla();
    }

    public void refrescarTabla() {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(this::refrescarTabla);
            return;
        }

        modeloTabla.setRowCount(0);

        for (Pedido pedido : controller.getPedidos()) {
            String repartidor = pedido.getRepartidor();
            if (repartidor == null || repartidor.isBlank()) {
                repartidor = "No asignado";
            }

            String detalle = "";
            if (pedido instanceof comida pedidoComida) {
                detalle = pedidoComida.getRestaurant();
            } else if (pedido instanceof encomiendas encomienda) {
                detalle = encomienda.getDetalle();
            } else if (pedido instanceof compraExpress express) {
                detalle = express.getPedido();
            }

            modeloTabla.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega(),
                    pedido.getTipoPedido(),
                    detalle,
                    pedido.getDistanciaKm(),
                    repartidor,
                    pedido.calcularTiempoEntrega()
            });
        }
    }
}
