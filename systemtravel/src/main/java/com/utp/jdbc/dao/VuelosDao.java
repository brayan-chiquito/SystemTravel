package com.utp.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.utp.jdbc.modelo.Clientes;
import com.utp.jdbc.modelo.Vuelos;

public class VuelosDao {
	final private Connection con;
	public VuelosDao(Connection con) {
		this.con = con;
	}
	
	public void guardar(Vuelos vuelos) {
		try{
			con.setAutoCommit(false);	
			//deshabilitar la tranasacion en automatico y dejarla en manual
			
			PreparedStatement statement = con.prepareStatement("INSERT INTO VUELOS(fecha, hora, origen, destino, plazastotales, plazasturista)"
					+ "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRejistro(vuelos, statement);		
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
	
	private void ejecutarRejistro(Vuelos vuelos, PreparedStatement statement)		//se crea funcion para generar varios registros 
			throws SQLException {

		LocalDate fechaLocal = vuelos.getFecha();
		java.sql.Date fechaSql = java.sql.Date.valueOf(fechaLocal);
		Time hora = Time.valueOf(vuelos.getHora());
		statement.setDate(1, fechaSql);
		statement.setTime(2, hora);
		statement.setString(3, vuelos.getOrigen());
		statement.setString(4, vuelos.getDestino());
		statement.setInt(5, vuelos.getPlazasTotales());
		statement.setInt(6, vuelos.getPlazasTurista());
    	
    	statement.execute();
    	final ResultSet resultSet = statement.getGeneratedKeys();
    	try(resultSet){
	    	while(resultSet.next()) {
	    		vuelos.setIdvuelos(resultSet.getInt(1));
	    		System.out.println(String.format("Fue insertado el producto %s", vuelos));
	    	}
    	}
	}

	public List<Vuelos> listar() {
		List<Vuelos> resultado = new ArrayList<>();
		
		try{
		
			final PreparedStatement statement = con.prepareStatement("SELECT IDVUELOS, FECHA, HORA, ORIGEN, DESTINO, PLAZASTOTALES, PLAZASTURISTA FROM VUELOS");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				
				while(resultSet.next()) {
					
					java.sql.Date fechaSql = resultSet.getDate("FECHA");
				    LocalDate fecha = fechaSql.toLocalDate();
				    
				    java.sql.Time horaSql = resultSet.getTime("HORA");
				    LocalTime hora = horaSql.toLocalTime();
					
					Vuelos fila = new Vuelos(resultSet.getInt("IDVUELOS"),
							fecha,
							hora,
							resultSet.getString("ORIGEN"),
							resultSet.getString("DESTINO"),
							resultSet.getInt("PLAZASTOTALES"),
							resultSet.getInt("PLAZASTURISTA"));
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM VUELOS WHERE IDVUELOS = ?");
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

	public int modificar( String origen, String destino, Integer plazastotales,
			Integer plazasturista, Integer idvuelo) {
		try{
			final PreparedStatement statement = con.prepareStatement("UPDATE VUELOS SET ORIGEN = ?, DESTINO = ?, PLAZASTOTALES = ?, PLAZASTURISTA = ? WHERE IDVUELOS = ?");
			try(statement){
		
				statement.setString(1, origen);
				statement.setString(2, destino);
				statement.setInt(3, plazastotales);
				statement.setInt(4, plazasturista);
				statement.setInt(5, idvuelo);

				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

