package com.utp.jdbc.modelo;

public class Sucursales {
	private Integer idsucursales;
	private String direccion;
	private String telefono;
	public Sucursales(String direccion, String telefono) {
		this.direccion = direccion;
		this.telefono = telefono;
	}
	public Sucursales(Integer idsucursales, String direccion, String telefono) {
		this.idsucursales = idsucursales;
		this.direccion = direccion;
		this.telefono = telefono;
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
	public Integer getIdsucursales() {
		return idsucursales;
	}
	
	public void setIdsucursales(int idsuscursales) {
		this.idsucursales = idsucursales;
	}
	@Override
	public String toString() {
		return "Sucursales [idsucursales=" + idsucursales + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	
	
}
