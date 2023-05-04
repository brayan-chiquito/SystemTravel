package com.utp.jdbc.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vuelos {
	private Integer idvuelos;
	private LocalDate fecha;
	private LocalTime hora;
	private String origen;
	private String destino;
	private Integer plazasTotales;
	private Integer plazasTurista;
	
	public Vuelos(LocalDate fecha, LocalTime hora, String origen, String destino, Integer plazasTotales,
			Integer plazasTurista) {
		this.fecha = fecha;
		this.hora = hora;
		this.origen = origen;
		this.destino = destino;
		this.plazasTotales = plazasTotales;
		this.plazasTurista = plazasTurista;
	}

	public Vuelos(Integer idvuelos, LocalDate fecha, LocalTime hora, String origen, String destino,
			Integer plazasTotales, Integer plazasTurista) {
		this.idvuelos = idvuelos;
		this.fecha = fecha;
		this.hora = hora;
		this.origen = origen;
		this.destino = destino;
		this.plazasTotales = plazasTotales;
		this.plazasTurista = plazasTurista;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Integer getPlazasTotales() {
		return plazasTotales;
	}

	public void setPlazasTotales(Integer plazasTotales) {
		this.plazasTotales = plazasTotales;
	}

	public Integer getPlazasTurista() {
		return plazasTurista;
	}

	public void setPlazasTurista(Integer plazasTurista) {
		this.plazasTurista = plazasTurista;
	}

	public Integer getIdvuelos() {
		return idvuelos;
	}
	
	public void setIdvuelos(int idvuelos) {
		this.idvuelos = idvuelos;
	}

	@Override
	public String toString() {
		return "Vuelos [idvuelos=" + idvuelos + ", fecha=" + fecha + ", hora=" + hora + ", origen=" + origen
				+ ", destino=" + destino + ", plazasTotales=" + plazasTotales + ", plazasTurista=" + plazasTurista
				+ "]";
	}
	
	
	
}
