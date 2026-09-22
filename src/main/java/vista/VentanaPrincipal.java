package vista;

import controlador.PedidoController;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final PedidoController controller;
    private VentanaListaPedidos ventanaLista;

    public VentanaPrincipal() {
        this(new PedidoController());
    }

    public VentanaPrincipal(PedidoController controller) {
        this.controller = controller;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("SpeedFast - Gestión de Entregas");
        setSize(520, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel("SpeedFast", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 5, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnAsignar = new JButton("Asignar repartidor / Iniciar entrega");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnAsignar);
        add(panelBotones, BorderLayout.CENTER);

        JLabel estado = new JLabel("Pedidos registrados: 0", SwingConstants.CENTER);
        add(estado, BorderLayout.SOUTH);
        controller.agregarListenerCambio(() -> estado.setText(
                "Pedidos registrados: " + controller.getPedidos().size()));

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventana = new VentanaRegistroPedido(controller);
            ventana.setVisible(true);
        });

        btnListar.addActionListener(e -> abrirLista());

        btnAsignar.addActionListener(e -> {
            if (controller.getPedidos().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No existen pedidos registrados.",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int asignados = controller.asignarRepartidores();
            controller.iniciarEntregas();

            JOptionPane.showMessageDialog(this,
                    "Operación realizada correctamente.\n"
                            + "Repartidores asignados automáticamente: " + asignados + "\n"
                            + "Las entregas fueron iniciadas de forma simulada.",
                    "SpeedFast", JOptionPane.INFORMATION_MESSAGE);

            if (ventanaLista != null) {
                ventanaLista.refrescarTabla();
            }
        });
    }

    private void abrirLista() {
        if (ventanaLista == null || !ventanaLista.isDisplayable()) {
            ventanaLista = new VentanaListaPedidos(controller);
        }
        ventanaLista.refrescarTabla();
        ventanaLista.setVisible(true);
        ventanaLista.toFront();
    }
}
