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
import com.utp.jdbc.modelo.Hoteles;

public class ControlDeHoteles extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel labelNombre, labelDireccion, labelCiudad, labelTelefono,labelPlazasDisponibles;
    private JTextField textoNombre, textoDireccion, textoCiudad, textoTelefono, textoPlazasDisponibles;
    private JComboBox<Object> comboCategoria;
    private JButton botonGuardar, botonModificar, botonLimpiar, botonEliminar, botonReporte;
    private JTable tabla;
    private DefaultTableModel modelo;
    private HotelesController hotelesController;
    //private CategoriaController categoriaController;

    public ControlDeHoteles() {
        super("Hoteles");
        //falta
        //this.categoriaController = new CategoriaController();
        this.hotelesController = new HotelesController();

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
        modelo.addColumn("idhoteles");
        modelo.addColumn("nombre");
        modelo.addColumn("direccion");
        modelo.addColumn("ciudad");
        modelo.addColumn("telefono");
        modelo.addColumn("numeroPlazasDispo");

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
        labelDireccion = new JLabel("direccion");
        labelCiudad = new JLabel("ciudad");
        labelTelefono = new JLabel("Telefono");
        labelPlazasDisponibles = new JLabel("Plazas disponibles");

        labelNombre.setBounds(10, 10, 240, 15);
        labelDireccion.setBounds(10, 50, 240, 15);
        labelCiudad.setBounds(10, 90, 240, 15);
        labelTelefono.setBounds(10, 130, 240, 15);
        labelPlazasDisponibles.setBounds(300,10,240,15);

        labelNombre.setForeground(Color.BLACK);
        labelDireccion.setForeground(Color.BLACK);
        labelTelefono.setForeground(Color.BLACK);
        labelPlazasDisponibles.setForeground(Color.BLACK);

        textoNombre = new JTextField();
        textoDireccion = new JTextField();
        textoCiudad = new JTextField();
        textoTelefono = new JTextField();
        textoPlazasDisponibles = new JTextField();
//        comboCategoria = new JComboBox<>();
//        comboCategoria.addItem("Elige una Categoría");

        // TODO
        var categorias = this.hotelesController.listar();
        // categorias.forEach(categoria -> comboCategoria.addItem(categoria));

        textoNombre.setBounds(10, 25, 265, 20);
        textoDireccion.setBounds(10, 65, 265, 20);
        textoCiudad.setBounds(10, 105, 265, 20);
        textoTelefono.setBounds(10, 145, 265, 20);
        textoPlazasDisponibles.setBounds(300,25,265,20);

        botonGuardar = new JButton("Guardar");
        botonLimpiar = new JButton("Limpiar");
        botonGuardar.setBounds(10, 175, 80, 20);
        botonLimpiar.setBounds(100, 175, 80, 20);

        container.add(labelNombre);
        container.add(labelDireccion);
        container.add(labelCiudad);
        container.add(labelTelefono);
        container.add(labelPlazasDisponibles);
        container.add(textoNombre);
        container.add(textoDireccion);
        container.add(textoCiudad);
        container.add(textoTelefono);
        container.add(textoPlazasDisponibles);
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
        new ReporteFrameHoteles(this);
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
                    Integer idhoteles = (Integer) modelo.getValueAt(tabla.getSelectedRow(), 0);
                    String nombre = (String) modelo.getValueAt(tabla.getSelectedRow(), 1);
                    String direccion = (String) modelo.getValueAt(tabla.getSelectedRow(), 2);
                    String ciudad = (String) modelo.getValueAt(tabla.getSelectedRow(), 3);
                    String telefono = (String) modelo.getValueAt(tabla.getSelectedRow(), 4);
                    Integer numeroPlazasDispo = Integer.valueOf(modelo.getValueAt(tabla.getSelectedRow(), 5).toString());
                    int cantidadEliminada;
					cantidadEliminada = this.hotelesController.modificar(nombre, direccion, ciudad, telefono, numeroPlazasDispo, idhoteles);
					
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

                    this.hotelesController.eliminar(id);

                    modelo.removeRow(tabla.getSelectedRow());

                    JOptionPane.showMessageDialog(this, "Item eliminado con éxito!");
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void cargarTabla() {
    	var hoteles = this.hotelesController.listar();
        hoteles.forEach(hotel -> modelo.addRow(
        		new Object[] {
        			hotel.getIdhoteles(),
        			hotel.getNombre(),
        			hotel.getDireccion(),
        			hotel.getCiudad(),
        			hotel.getTelefono(),
        			hotel.getNumeroPlazasDispo()}));
    }

    private void guardar() {
        if (textoNombre.getText().isBlank() || textoDireccion.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Los campos Nombre y Direccion son requeridos.");
            return;
        }

        Integer numeroPlazasDisponi;

        try {
            numeroPlazasDisponi = Integer.parseInt(textoPlazasDisponibles.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, String
                    .format("El campo cantidad debe ser numérico dentro del rango %d y %d.", 0, Integer.MAX_VALUE));
            return;
        }

        // TODO
        var hotel = new Hoteles(textoNombre.getText(),textoDireccion.getText(),textoCiudad.getText(),textoTelefono.getText(), numeroPlazasDisponi);

        this.hotelesController.guardar(hotel);

        JOptionPane.showMessageDialog(this, "Registrado con éxito!");

        this.limpiarFormulario();
    }

    private void limpiarFormulario() {
        this.textoNombre.setText("");
        this.textoDireccion.setText("");
        this.textoCiudad.setText("");
        this.textoTelefono.setText("");
        this.textoPlazasDisponibles.setText("");
    }

}
