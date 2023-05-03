package com.utp.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.dao.ClientesDao;
import com.utp.jdbc.factory.ConnectionFactory;
import com.utp.jdbc.modelo.Clientes;

public class ClientesController {
	
	private ClientesDao clientesDao;
	
	public ClientesController() {
		var factory = new ConnectionFactory();
		this.clientesDao = new ClientesDao(factory.recuperaConexion());
	}
	public void modificar(String nombre, String descripcion, Integer id) {
		// TODO
	}

	public void eliminar(Integer id) {
		// TODO
	}

	public List<Clientes> listar() {
		return clientesDao.listar();
	}

    public void guardar(Clientes clientes) {
		clientesDao.guardar(clientes);
	}

}
