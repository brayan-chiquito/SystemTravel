package com.utp.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.dao.ClientesDao;
import com.utp.jdbc.dao.HotelesDao;
import com.utp.jdbc.factory.ConnectionFactory;
import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Hoteles;

public class HotelesController {
	
	private HotelesDao hotelesDao;
	
	public HotelesController() {
		var factory = new ConnectionFactory();
		this.hotelesDao = new HotelesDao(factory.recuperaConexion());
	}
	public int modificar(String telefono, Integer numeroPlazasDispo, Integer idhoeteles) {
		return hotelesDao.modificar(telefono, numeroPlazasDispo, idhoeteles);
	}

	public int eliminar(Integer id) {
		return hotelesDao.eliminar(id);
	}

	public List<Hoteles> listar() {
		return hotelesDao.listar();
	}

    public void guardar(Hoteles hoteles) {
		hotelesDao.guardar(hoteles);
	}

}
