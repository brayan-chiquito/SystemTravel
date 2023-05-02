package com.utp.jdbc.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    private JButton btnOpcion1, btnOpcion2, btnOpcion3, btnOpcion4, btnOpcion5, btnSalir;
    private JPanel panel;

    public Menu() {
        setTitle("Menú");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel y asignar layout
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Crear botones
        btnOpcion1 = crearBoton("Clientes");
        btnOpcion2 = crearBoton("Hoteles");
        btnOpcion3 = crearBoton("Sucursales");
        btnOpcion4 = crearBoton("Vuelos");
        btnOpcion5 = crearBoton("Opción 5");
        btnSalir = crearBoton("Salir");

        // Agregar botones al panel
        panel.add(btnOpcion1);
        panel.add(btnOpcion2);
        panel.add(btnOpcion3);
        panel.add(btnOpcion4);
        panel.add(btnOpcion5);
        panel.add(btnSalir);

        // Agregar listener a los botones
        btnOpcion1.addActionListener(this);
        btnOpcion2.addActionListener(this);
        btnOpcion3.addActionListener(this);
        btnOpcion4.addActionListener(this);
        btnOpcion5.addActionListener(this);
        btnSalir.addActionListener(this);

        // Agregar panel al frame
        add(panel);

        setVisible(true);
        setLocationRelativeTo(null);
        
    }

    public JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(150, 40));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(59, 89, 182));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    public void actionPerformed(ActionEvent e) {
        // Obtener el botón que se ha presionado
        JButton botonPresionado = (JButton) e.getSource();

        // Verificar el botón y hacer lo correspondiente
        if (botonPresionado == btnOpcion1) {
            ControlDeClientes clientes = new ControlDeClientes();
        } else if (botonPresionado == btnOpcion2) {
            ControlDeHoteles controlDeHoteles = new ControlDeHoteles();
        } else if (botonPresionado == btnOpcion3) {
            ControlDeSucursales controlDeSucursales = new ControlDeSucursales();
        } else if (botonPresionado == btnOpcion4) {
            ControlDeVuelos controlDeVuelos = new ControlDeVuelos();
        } else if (botonPresionado == btnOpcion5) {
            JOptionPane.showMessageDialog(this, "Has seleccionado la opción 5");
        } else if (botonPresionado == btnSalir) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}