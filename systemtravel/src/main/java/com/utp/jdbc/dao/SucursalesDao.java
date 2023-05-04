package com.utp.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Sucursales;

public class SucursalesDao {
	final private Connection con;
	public SucursalesDao(Connection con) {
		this.con = con;
	}
	
	public void guardar(Sucursales sucursales) {
		try{
			con.setAutoCommit(false);	
			//deshabilitar la tranasacion en automatico y dejarla en manual
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO SUCURSALES(direccion, telefono)"
					+ "VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRejistro(sucursales, statement);		
				//se envian datos para el nuevo registro y el statement	
				con.commit();		
				//control de trasaccion en caso de sea correcta
			}catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ROLLBACK de la transaccion");
				con.rollback();	
				//reverti cambios hechos en la base de datos en caso de error
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	
	private void ejecutarRejistro(Sucursales sucursales, PreparedStatement statement)		//se crea funcion para generar varios registros 
			throws SQLException {
		statement.setString(1, sucursales.getDireccion());
		statement.setString(2, sucursales.getTelefono());
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
	    	while(resultSet.next()) {
	    		sucursales.setIdsucursales(resultSet.getInt(1));
	    		System.out.println(String.format("Fue insertado el producto %s", sucursales));
	    	}
    	}
	}

	public List<Sucursales> listar() {
		List<Sucursales> resultado = new ArrayList<>();
		
		try{
		
			final PreparedStatement statement = con.prepareStatement("SELECT IDSUCURSALES, DIRECCION, TELEFONO FROM SUCURSALES");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Sucursales fila = new Sucursales(resultSet.getInt("IDSUCURSALES"),
							resultSet.getString("DIRECCION"),
							resultSet.getString("TELEFONO"));
					resultado.add(fila);
				}
				return resultado;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public int eliminar(Integer id) {
		try{
			final PreparedStatement statement = con.prepareStatement("DELETE FROM SUCURSALES WHERE IDSUCURSALES = ?");
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(String direccion, String telefono, Integer idsucursal) {
		try{
			final PreparedStatement statement = con.prepareStatement("UPDATE SUCURSALES SET DIRECCION =  ?, TELEFONO = ? WHERE IDSUCURSALES = ?");
			try(statement){
				statement.setString(1, direccion);
				statement.setString(2, telefono);
				statement.setInt(3, idsucursal);

				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

