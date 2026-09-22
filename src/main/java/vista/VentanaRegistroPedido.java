package vista;

import controlador.PedidoController;
import model.Pedido;
import model.comida;
import model.compraExpress;
import model.encomiendas;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final PedidoController controller;
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;
    private JTextField txtDistancia;
    private JTextField txtDetalle;

    public VentanaRegistroPedido(PedidoController controller) {
        this.controller = controller;
        inicializarVentana();
    }

    private void inicializarVentana() {
        setTitle("SpeedFast - Registrar pedido");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Registrar nuevo pedido", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 8, 8));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        txtId = new JTextField();
        txtDireccion = new JTextField();
        cmbTipo = new JComboBox<>(new String[]{"comida", "encomienda", "express"});
        txtDistancia = new JTextField();
        txtDetalle = new JTextField();

        formulario.add(new JLabel("ID:"));
        formulario.add(txtId);
        formulario.add(new JLabel("Dirección:"));
        formulario.add(txtDireccion);
        formulario.add(new JLabel("Tipo:"));
        formulario.add(cmbTipo);
        formulario.add(new JLabel("Distancia (km):"));
        formulario.add(txtDistancia);
        formulario.add(new JLabel("Detalle:"));
        formulario.add(txtDetalle);

        add(formulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarPedido());
        btnCancelar.addActionListener(e -> dispose());

        cmbTipo.addActionListener(e -> actualizarTextoDetalle());
        actualizarTextoDetalle();
    }

    private void actualizarTextoDetalle() {
        String tipo = (String) cmbTipo.getSelectedItem();
        if ("comida".equals(tipo)) {
            txtDetalle.setToolTipText("Nombre del restaurante");
        } else if ("encomienda".equals(tipo)) {
            txtDetalle.setToolTipText("Descripción de la encomienda, por ejemplo: teclado");
        } else {
            txtDetalle.setToolTipText("Descripción de la compra");
        }
    }

    private void guardarPedido() {
        try {
            String idTexto = txtId.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            String distanciaTexto = txtDistancia.getText().trim();
            String detalle = txtDetalle.getText().trim();

            if (idTexto.isEmpty() || direccion.isEmpty() || distanciaTexto.isEmpty() || detalle.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            int id = Integer.parseInt(idTexto);
            int distancia = Integer.parseInt(distanciaTexto);

            if (id <= 0) {
                throw new IllegalArgumentException("El ID debe ser mayor que 0.");
            }
            if (distancia < 0) {
                throw new IllegalArgumentException("La distancia no puede ser negativa.");
            }
            if (controller.existeId(id)) {
                throw new IllegalArgumentException("Ya existe un pedido con el ID indicado.");
            }

            Pedido nuevoPedido;

            switch (tipo) {
                case "comida" -> nuevoPedido = new comida(id, direccion, tipo, distancia, detalle);
                case "encomienda" -> nuevoPedido = new encomiendas(id, direccion, tipo, distancia, detalle);
                case "express" -> nuevoPedido = new compraExpress(id, direccion, tipo, distancia, detalle);
                default -> throw new IllegalArgumentException("Tipo de pedido no válido.");
            }

            controller.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(this,
                    "Pedido registrado correctamente.",
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "ID y distancia deben ser valores numéricos válidos.",
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtDireccion.setText("");
        txtDistancia.setText("");
        txtDetalle.setText("");
        txtId.requestFocus();
    }
}
