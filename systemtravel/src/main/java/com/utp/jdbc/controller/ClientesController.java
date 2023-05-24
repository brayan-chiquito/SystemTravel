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
	public int modificar(String direccion, String telefono, Integer id) {
		return clientesDao.modificar(direccion, telefono, id);
	}

	public int eliminar(Integer id) {
		return clientesDao.eliminar(id);
	}

	public List<Clientes> listar() {
		return clientesDao.listar();
	}

    public void guardar(Clientes clientes, Integer idHotel, Integer idVuelo) {
    	clientes.setHotelId(idHotel);
    	clientes.setVueloId(idVuelo);
		clientesDao.guardar(clientes);
	}

}
