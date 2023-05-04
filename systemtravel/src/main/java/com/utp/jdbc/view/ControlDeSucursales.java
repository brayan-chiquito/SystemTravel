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
import com.utp.jdbc.controller.SucursalesController;
import com.utp.jdbc.modelo.Sucursales;

public class ControlDeSucursales extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelDireccion, labelTelefono;
    private JTextField textoDireccion, textoTelefono;
    private JComboBox<Object> comboCategoria;
    private JButton botonGuardar, botonModificar, botonLimpiar, botonEliminar, botonReporte;
    private JTable tabla;
    private DefaultTableModel modelo;
    private SucursalesController sucursalesController;
    private CategoriaController categoriaController;

    public ControlDeSucursales() {
        super("Sucursales");
        //falta
        //this.categoriaController = new CategoriaController();
        this.sucursalesController = new SucursalesController();

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
        modelo.addColumn("idsucursales");
        modelo.addColumn("direccion");
        modelo.addColumn("telefono");

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
        labelDireccion = new JLabel("Direccion");
        labelTelefono = new JLabel("Telefono");

        labelDireccion.setBounds(10, 10, 240, 15);
        labelTelefono.setBounds(10, 50, 240, 15);

        labelDireccion.setForeground(Color.BLACK);
        labelTelefono.setForeground(Color.BLACK);

        textoDireccion = new JTextField();
        textoTelefono = new JTextField();
//        comboCategoria = new JComboBox<>();
//        comboCategoria.addItem("Elige una Categoría");

        // TODO
        //var categorias = this.categoriaController.listar();
        // categorias.forEach(categoria -> comboCategoria.addItem(categoria));

        textoDireccion.setBounds(10, 25, 265, 20);
        textoTelefono.setBounds(10, 65, 265, 20);

        botonGuardar = new JButton("Guardar");
        botonLimpiar = new JButton("Limpiar");
        botonGuardar.setBounds(10, 175, 80, 20);
        botonLimpiar.setBounds(100, 175, 80, 20);

        container.add(labelDireccion);
        container.add(labelTelefono);
        container.add(textoDireccion);
        container.add(textoTelefono);
        container.add(botonGuardar);
        container.add(botonLimpiar);
    }

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
        new ReporteFrameSucursales(this);
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
                    Integer idsucursal = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
                    String direccion= (String) modelo.getValueAt(tabla.getSelectedRow(), 1);
                    String telefono = (String) modelo.getValueAt(tabla.getSelectedRow(), 2);
                    int cantidadEliminada;
					cantidadEliminada = this.sucursalesController.modificar(direccion, telefono, idsucursal);
					
					JOptionPane.showMessageDialog(this, cantidadEliminada + " item actualizado con éxito!");
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

                    this.sucursalesController.eliminar(id);

                    modelo.removeRow(tabla.getSelectedRow());

                    JOptionPane.showMessageDialog(this, "Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void cargarTabla() {
    	var sucursales = this.sucursalesController.listar();
        sucursales.forEach(sucursal -> modelo.addRow(
        		new Object[] {
        			sucursal.getIdsucursales(),
        			sucursal.getDireccion(),
        			sucursal.getTelefono()}));
    }

    private void guardar() {
        if (textoDireccion.getText().isBlank() || textoTelefono.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Los campos Direccion y Telefono son requeridos.");
            return;
        }

        

        // TODO
        var sucursal = new Sucursales(textoDireccion.getText(), textoTelefono.getText());
        //var categoria = comboCategoria.getSelectedItem();

        this.sucursalesController.guardar(sucursal);

        JOptionPane.showMessageDialog(this, "Registrado con éxito!");

        this.limpiarFormulario();
    }

    private void limpiarFormulario() {
    	this.textoDireccion.setText("");
        this.textoTelefono.setText("");
    }

}
