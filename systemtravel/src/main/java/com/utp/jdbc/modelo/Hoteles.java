package com.utp.jdbc.modelo;

public class Hoteles {
	private Integer idhoteles;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String telefono;
	private Integer numeroPlazasDispo;
	
	public Hoteles(String nombre, String direccion, String ciudad, String telefono, Integer numeroPlazasDispo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.numeroPlazasDispo = numeroPlazasDispo;
	}

	public Hoteles(Integer idhoteles, String nombre, String direccion, String ciudad, String telefono,
			Integer numeroPlazasDispo) {
		this.idhoteles = idhoteles;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.numeroPlazasDispo = numeroPlazasDispo;
	}

	public Hoteles(int id, String nombre) {
		this.idhoteles = id;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getNumeroPlazasDispo() {
		return numeroPlazasDispo;
	}

	public void setNumeroPlazasDispo(Integer numeroPlazasDispo) {
		this.numeroPlazasDispo = numeroPlazasDispo;
	}

	public Integer getIdhoteles() {
		return idhoteles;
	}
	
	public void setIdhoteles(int idhoteles) {
		this.idhoteles = idhoteles;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	
}
