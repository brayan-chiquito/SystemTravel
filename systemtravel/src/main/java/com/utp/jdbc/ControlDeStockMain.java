package com.utp.jdbc;

import javax.swing.JFrame;

import com.utp.jdbc.view.ControlDeClientes;
import com.utp.jdbc.view.ControlDeHoteles;
import com.utp.jdbc.view.ControlDeSucursales;

public class ControlDeStockMain {

	public static void main(String[] args) {
		ControlDeSucursales produtoCategoriaFrame = new ControlDeSucursales();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
