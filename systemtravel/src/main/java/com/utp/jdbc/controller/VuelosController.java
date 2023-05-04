package com.utp.jdbc.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.dao.ClientesDao;
import com.utp.jdbc.dao.VuelosDao;
import com.utp.jdbc.factory.ConnectionFactory;
import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Vuelos;

public class VuelosController {
	
	private VuelosDao vuelosDao;
	
	public VuelosController() {
		var factory = new ConnectionFactory();
		this.vuelosDao = new VuelosDao(factory.recuperaConexion());
	}
	public int modificar(String origen, String destino, Integer plazastotales, Integer plazasturista, Integer idvuelo) {
		return vuelosDao.modificar(origen,destino,plazastotales,plazasturista,idvuelo);
	}

	public int eliminar(Integer id) {
		return vuelosDao.eliminar(id);
	}

	public List<Vuelos> listar() {
		return vuelosDao.listar();
	}

    public void guardar(Vuelos vuelos) {
		vuelosDao.guardar(vuelos);
	}

}
