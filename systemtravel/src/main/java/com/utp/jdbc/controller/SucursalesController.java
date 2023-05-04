package com.utp.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.dao.ClientesDao;
import com.utp.jdbc.dao.SucursalesDao;
import com.utp.jdbc.factory.ConnectionFactory;
import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Sucursales;

public class SucursalesController {
	
	private SucursalesDao sucursalesDao;
	
	public SucursalesController() {
		var factory = new ConnectionFactory();
		this.sucursalesDao = new SucursalesDao(factory.recuperaConexion());
	}
	public int modificar(String direccion, String telefono, Integer idsucursal) {
		return sucursalesDao.modificar(direccion,telefono,idsucursal);
	}

	public int eliminar(Integer id) {
		return sucursalesDao.eliminar(id);
	}

	public List<Sucursales> listar() {
		return sucursalesDao.listar();
	}

    public void guardar(Sucursales sucursales) {
		sucursalesDao.guardar(sucursales);
	}

}
