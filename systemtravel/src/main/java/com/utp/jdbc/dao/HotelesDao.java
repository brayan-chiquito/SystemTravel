package com.utp.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Hoteles;

public class HotelesDao {
	final private Connection con;
	public HotelesDao(Connection con) {
		this.con = con;
	}
	
	public void guardar(Hoteles hoteles) {
		try{
			con.setAutoCommit(false);	
			//deshabilitar la tranasacion en automatico y dejarla en manual
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO HOTELES(nombre, direccion, ciudad, telefono, numeroPlazasDispo)"
					+ "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRejistro(hoteles, statement);		
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
	
	private void ejecutarRejistro(Hoteles hoteles, PreparedStatement statement)		//se crea funcion para generar varios registros 
			throws SQLException {
		statement.setString(1, hoteles.getNombre());
		statement.setString(2, hoteles.getDireccion());
		statement.setString(3, hoteles.getCiudad());
		statement.setString(4, hoteles.getTelefono());
		statement.setInt(5, hoteles.getNumeroPlazasDispo());
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
	    	while(resultSet.next()) {
	    		hoteles.setIdhoteles(resultSet.getInt(1));;
	    		System.out.println(String.format("Fue insertado el producto %s", hoteles));
	    	}
    	}
	}

	public List<Hoteles> listar() {
		List<Hoteles> resultado = new ArrayList<>();
		
		try{
		
			final PreparedStatement statement = con.prepareStatement("SELECT IDHOTELES, NOMBRE, NOMBRE, DIRECCION, CIUDAD, TELEFONO, NUMEROPLAZASDISPO FROM HOTELES");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Hoteles fila = new Hoteles(resultSet.getInt("IDHOTELES"),
							resultSet.getString("NOMBRE"),
							resultSet.getString("DIRECCION"),
							resultSet.getString("CIUDAD"),
							resultSet.getString("TELEFONO"),
							resultSet.getInt("NUMEROPLAZASDISPO"));
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HOTELES WHERE IDHOTELES = ?");
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

	public int modificar(String nombre, String direccion, String ciudad, String telefono, Integer numeroPlazasDispo,
			Integer idhoteles) {
		try{
			final PreparedStatement statement = con.prepareStatement("UPDATE HOTELES SET NOMBRE =  ?, DIRECCION = ?, CIUDAD = ?, TELEFONO = ?, NUMEROPLAZASDISPO = ? WHERE IDHOTELES = ?");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, direccion);
				statement.setString(3, ciudad);
				statement.setString(4, telefono);
				statement.setInt(5, numeroPlazasDispo);
				statement.setInt(6, idhoteles);

				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

