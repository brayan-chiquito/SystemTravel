package com.utp.jdbc.modelo;

public class Clientes {
	private Integer codigo;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private Integer hotelId;
	
	public Clientes(String nombre, String apellido, String direccion, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	public Clientes(Integer codigo, String nombre, String apellido, String direccion, String telefono) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	@Override
	public String toString() {
		return "Clientes [codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion="
				+ direccion + ", telefono=" + telefono + ", hotelId=" + hotelId + "]";
	}
	
}
