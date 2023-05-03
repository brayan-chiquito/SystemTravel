package com.utp.jdbc.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.utp.jdbc.controller.CategoriaController;
import com.utp.jdbc.controller.ClientesController;

public class ControlDeVuelos extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelFecha, labelHora, labelOrigen, labelDestino, labelPlazasDisponibles, labelPlazasTurista
    ;
    private JTextField textoFecha, textoHora, textoOrigen, textoDestino, textoPlazasDisponibles, textoPlazasTurista
    ;
    private JComboBox<Object> comboCategoria;
    private JButton botonGuardar, botonModificar, botonLimpiar, botonEliminar, botonReporte;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ClientesController productoController;
    private CategoriaController categoriaController;

    public ControlDeVuelos() {
        super("Vuelos");
        //falta
        this.categoriaController = new CategoriaController();
        this.productoController = new ClientesController();

        Container container = getContentPane();
        setLayout(null);

        configurarCamposDelFormulario(container);

        configurarTablaDeContenido(container);

        configurarAccionesDelFormulario();
    }

    private void configurarTablaDeContenido(Container container) {
        tabla = new JTable();
        //falta
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.addColumn("Identificador del Producto");
        modelo.addColumn("Nombre del Producto");
        modelo.addColumn("Descripción del Producto");

        cargarTabla();

        tabla.setBounds(10, 205, 760, 280);

        botonEliminar = new JButton("Eliminar");
        botonModificar = new JButton("Modificar");
        botonReporte = new JButton("Ver Reporte");
        botonEliminar.setBounds(10, 500, 80, 20);
        botonModificar.setBounds(100, 500, 80, 20);
        botonReporte.setBounds(190, 500, 80, 20);

        container.add(tabla);
        container.add(botonEliminar);
        container.add(botonModificar);
        container.add(botonReporte);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void configurarCamposDelFormulario(Container container) {
        labelFecha = new JLabel("Fecha");
        labelHora = new JLabel("Hora");
        labelOrigen = new JLabel("Origen");
        labelDestino = new JLabel("Destino");
        labelPlazasDisponibles = new JLabel("Plazas disponibles");
        labelPlazasTurista= new JLabel("Plazas disponibles turista");

        labelFecha.setBounds(10, 10, 240, 15);
        labelHora.setBounds(10, 50, 240, 15);
        labelOrigen.setBounds(10, 90, 240, 15);
        labelDestino.setBounds(10, 130, 240, 15);
        labelPlazasDisponibles.setBounds(300,10,240,15);
        labelPlazasTurista.setBounds(300, 50, 240, 15);

        labelFecha.setForeground(Color.BLACK);
        labelHora.setForeground(Color.BLACK);
        labelOrigen.setForeground(Color.BLACK);
        labelDestino.setForeground(Color.BLACK);
        labelPlazasDisponibles.setForeground(Color.BLACK);
        labelPlazasTurista.setForeground(Color.BLACK);

        textoFecha = new JTextField();
        textoHora = new JTextField();
        textoOrigen = new JTextField();
        textoDestino = new JTextField();
        textoPlazasDisponibles = new JTextField();
        textoPlazasTurista = new JTextField();
//        comboCategoria = new JComboBox<>();
//        comboCategoria.addItem("Elige una Categoría");

        // TODO
        var categorias = this.categoriaController.listar();
        // categorias.forEach(categoria -> comboCategoria.addItem(categoria));

        textoFecha.setBounds(10, 25, 265, 20);
        textoHora.setBounds(10, 65, 265, 20);
        textoOrigen.setBounds(10, 105, 265, 20);
        textoDestino.setBounds(10, 145, 265, 20);
        textoPlazasDisponibles.setBounds(300,25,265,20);
        textoPlazasTurista.setBounds(300, 65, 265, 20);

        botonGuardar = new JButton("Guardar");
        botonLimpiar = new JButton("Limpiar");
        botonGuardar.setBounds(10, 175, 80, 20);
        botonLimpiar.setBounds(100, 175, 80, 20);

        container.add(labelFecha);
        container.add(labelHora);
        container.add(labelOrigen);
        container.add(labelDestino);
        container.add(labelPlazasDisponibles);
        container.add(labelPlazasTurista);
        container.add(textoFecha);
        container.add(textoHora);
        container.add(textoOrigen);
        container.add(textoDestino);
        container.add(textoPlazasDisponibles);
        container.add(textoPlazasTurista);
        container.add(botonGuardar);
        container.add(botonLimpiar);
    }
    //falta 
    private void configurarAccionesDelFormulario() {
        botonGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
                limpiarTabla();
                cargarTabla();
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
                limpiarTabla();
                cargarTabla();
            }
        });

        botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
                limpiarTabla();
                cargarTabla();
            }
        });

        botonReporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirReporte();
            }
        });
    }

    private void abrirReporte() {
        new ReporteFrameVuelos(this);
    }

    private void limpiarTabla() {
        modelo.getDataVector().clear();
    }

    private boolean tieneFilaElegida() {
        return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
    }

    private void modificar() {
        if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
                    String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 1);
                    String descripcion = (String) modelo.getValueAt(tabla.getSelectedRow(), 2);

                    this.productoController.modificar(nombre, descripcion, id);
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void eliminar() {
        if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        Optional.ofNullable(modelo.getValueAt(tabla.getSelectedRow(), tabla.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
                    Integer id = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);

                    this.productoController.eliminar(id);

                    modelo.removeRow(tabla.getSelectedRow());

                    JOptionPane.showMessageDialog(this, "Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void cargarTabla() {
        var productos = this.productoController.listar();

        try {
            // TODO
            // productos.forEach(producto -> modelo.addRow(new Object[] { "id", "nombre",
            // "descripcion" }));
        } catch (Exception e) {
            throw e;
        }
    }

    private void guardar() {
        if (textoFecha.getText().isBlank() || textoHora.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Los campos Nombre y Apellido son requeridos.");
            return;
        }

        Integer cantidadInt;

        try {
            cantidadInt = Integer.parseInt(textoOrigen.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, String
                    .format("El campo cantidad debe ser numérico dentro del rango %d y %d.", 0, Integer.MAX_VALUE));
            return;
        }

        // TODO
        var producto = new Object[] { textoFecha.getText(), textoHora.getText(), cantidadInt };
        var categoria = comboCategoria.getSelectedItem();

        //this.productoController.guardar(producto);

        JOptionPane.showMessageDialog(this, "Registrado con éxito!");

        this.limpiarFormulario();
    }

    private void limpiarFormulario() {
        this.textoFecha.setText("");
        this.textoHora.setText("");
        this.textoOrigen.setText("");
        this.comboCategoria.setSelectedIndex(0);
    }

}
