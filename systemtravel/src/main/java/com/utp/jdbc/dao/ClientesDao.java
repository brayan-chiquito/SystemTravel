package com.utp.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utp.jdbc.modelo.Clientes;

public class ClientesDao {
	final private Connection con;
	public ClientesDao(Connection con) {
		this.con = con;
	}
	
	public void guardar(Clientes clientes) {
		try{
			con.setAutoCommit(false);	
			//deshabilitar la tranasacion en automatico y dejarla en manual
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO CLIENTES(nombre, apellido, direccion, telefono)"
					+ "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRejistro(clientes, statement);		
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
	
	private void ejecutarRejistro(Clientes clientes, PreparedStatement statement)		//se crea funcion para generar varios registros 
			throws SQLException {
		statement.setString(1, clientes.getNombre());
		statement.setString(2, clientes.getApellido());
		statement.setString(3, clientes.getDireccion());
		statement.setString(4, clientes.getTelefono());
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
	    	while(resultSet.next()) {
	    		clientes.setCodigo(resultSet.getInt(1));
	    		System.out.println(String.format("Fue insertado el producto %s", clientes));
	    	}
    	}
	}

	public List<Clientes> listar() {
		List<Clientes> resultado = new ArrayList<>();
		
		try{
		
			final PreparedStatement statement = con.prepareStatement("SELECT CODIGO, NOMBRE, APELLIDO, DIRECCION, TELEFONO FROM CLIENTES");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				while(resultSet.next()) {
					Clientes fila = new Clientes(resultSet.getInt("CODIGO"),
							resultSet.getString("NOMBRE"),
							resultSet.getString("APELLIDO"),
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
	
}

