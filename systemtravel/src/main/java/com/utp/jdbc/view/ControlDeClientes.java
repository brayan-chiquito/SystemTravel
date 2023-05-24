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
import com.utp.jdbc.controller.HotelesController;
import com.utp.jdbc.controller.VuelosController;
import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Hoteles;
import com.utp.jdbc.modelo.Vuelos;

public class ControlDeClientes extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelNombre, labelApellido, labelDireccion, labelTelefono, labelHoteles, labelVuelo;
    private JTextField textoNombre, textoApellido, textoDireccion, textoTelefono;
    private JComboBox<Hoteles> comboHotel;
    private JComboBox<Vuelos> comboVuelos;
    private JButton botonGuardar, botonModificar, botonLimpiar, botonEliminar, botonReporte;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ClientesController clientesController;
    private HotelesController hotelesController;
    private VuelosController vuelosController;

    public ControlDeClientes() {
        super("Clientes");
        //falta
        this.hotelesController = new HotelesController();
        this.clientesController = new ClientesController();
        this.vuelosController = new VuelosController();

        Container container = getContentPane();
        setLayout(null);

        configurarCamposDelFormulario(container);

        configurarTablaDeContenido(container);

        configurarAccionesDelFormulario();
    }

    private void configurarTablaDeContenido(Container container) {
        tabla = new JTable();

        modelo = (DefaultTableModel) tabla.getModel();
        modelo.addColumn("codigo");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido");
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
        labelNombre = new JLabel("Nombre");
        labelApellido = new JLabel("Apellido");
        labelDireccion = new JLabel("Direccion");
        labelTelefono = new JLabel("Telefono");
        labelHoteles = new JLabel("Hoteles");
        labelVuelo = new JLabel("Vuelos");
        

        labelNombre.setBounds(10, 10, 240, 15);
        labelApellido.setBounds(10, 50, 240, 15);
        labelDireccion.setBounds(10, 90, 240, 15);
        labelTelefono.setBounds(10, 130, 240, 15);
        labelHoteles.setBounds(300, 10, 240, 15);
        labelVuelo.setBounds(300, 50, 240, 15);

        labelNombre.setForeground(Color.BLACK);
        labelApellido.setForeground(Color.BLACK);
        labelTelefono.setForeground(Color.BLACK);
        labelHoteles.setForeground(Color.BLACK);
        labelVuelo.setForeground(Color.BLACK);

        textoNombre = new JTextField();
        textoApellido = new JTextField();
        textoDireccion = new JTextField();
        textoTelefono= new JTextField();
        comboHotel = new JComboBox<>();
        comboHotel.addItem(new Hoteles(0,"elije un hotel"));
        comboVuelos = new JComboBox<>();
        comboVuelos.addItem(new Vuelos(0));

        // TODO
        var hoteles = this.hotelesController.listar();
        hoteles.forEach(hotel -> comboHotel.addItem(hotel));
        
        var vuelos = this.vuelosController.listar();
        vuelos.forEach(vuelo -> comboVuelos.addItem(vuelo));

        textoNombre.setBounds(10, 25, 265, 20);
        textoApellido.setBounds(10, 65, 265, 20);
        textoDireccion.setBounds(10, 105, 265, 20);
        textoTelefono.setBounds(10, 145, 265, 20);
        comboHotel.setBounds(300, 25, 265, 20);
        comboVuelos.setBounds(300, 65, 265, 20);

        botonGuardar = new JButton("Guardar");
        botonLimpiar = new JButton("Limpiar");
        botonGuardar.setBounds(10, 175, 80, 20);
        botonLimpiar.setBounds(100, 175, 80, 20);

        container.add(labelNombre);
        container.add(labelApellido);
        container.add(labelDireccion);
        container.add(labelTelefono);
        container.add(labelHoteles);
        container.add(labelVuelo);
        container.add(textoNombre);
        container.add(textoApellido);
        container.add(textoDireccion);
        container.add(textoTelefono);
        container.add(comboHotel);
        container.add(comboVuelos);
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
        new ReporteFrameClientes(this);
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
                    String direccion = (String) modelo.getValueAt(tabla.getSelectedRow(), 3);
                    String telefono = (String) modelo.getValueAt(tabla.getSelectedRow(), 4);
                    int cantidadEliminada;
                    cantidadEliminada = this.clientesController.modificar(direccion, telefono, id);
					
					JOptionPane.showMessageDialog(this, cantidadEliminada + " item actualizado con éxito!");
//********************
                    //this.productoController.modificar(nombre, descripcion, id);
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
//********************
                   this.clientesController.eliminar(id);

                    modelo.removeRow(tabla.getSelectedRow());

                    JOptionPane.showMessageDialog(this, "Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void cargarTabla() {
        var clientes = this.clientesController.listar();
        clientes.forEach(cliente -> modelo.addRow(
        		new Object[] {
        			cliente.getCodigo(),
        			cliente.getNombre(),
        			cliente.getApellido(),
        			cliente.getDireccion(),
        			cliente.getTelefono()}));
    }
    //ya
    private void guardar() {
        if (textoNombre.getText().isBlank() || textoApellido.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Los campos Nombre y Apellido son requeridos.");
            return;
        }

//        Integer cantidadInt;
//
//        try {
//            cantidadInt = Integer.parseInt(textoDireccion.getText());
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, String
//                    .format("El campo cantidad debe ser numérico dentro del rango %d y %d.", 0, Integer.MAX_VALUE));
//            return;
//        }

        // TODO
        var cliente = new Clientes(textoNombre.getText(),textoApellido.getText(),textoDireccion.getText(),textoTelefono.getText());
        var hotel = (Hoteles)comboHotel.getSelectedItem();
        var vuelo = (Vuelos)comboVuelos.getSelectedItem();

        this.clientesController.guardar(cliente, hotel.getIdhoteles(), vuelo.getIdvuelos());

        JOptionPane.showMessageDialog(this, "Registrado con éxito!");

        this.limpiarFormulario();
    }

    private void limpiarFormulario() {
        this.textoNombre.setText("");
        this.textoApellido.setText("");
        this.textoDireccion.setText("");
        this.textoTelefono.setText("");
        this.comboHotel.setSelectedIndex(0);
    }

}
