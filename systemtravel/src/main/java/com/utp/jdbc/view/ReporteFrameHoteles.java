package com.utp.jdbc.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.utp.jdbc.controller.CategoriaController;

public class ReporteFrameHoteles extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable tablaReporte;
    private DefaultTableModel modelo;

    private CategoriaController categoriaController;

    public ReporteFrameHoteles(ControlDeHoteles controlDeHoteles) {
        super("Reporte de produtos del stock");

        this.categoriaController = new CategoriaController();

        Container container = getContentPane();
        setLayout(null);

        tablaReporte = new JTable();
        tablaReporte.setBounds(0, 0, 600, 400);
        container.add(tablaReporte);

        modelo = (DefaultTableModel) tablaReporte.getModel();
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");
        modelo.addColumn("");

        cargaReporte();

        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(controlDeHoteles);
    }

    private void cargaReporte() {
        var contenido = categoriaController.cargaReporte();
        
        // TODO
        contenido.forEach(fila -> modelo
                .addRow(new Object[] {}));
    }

}