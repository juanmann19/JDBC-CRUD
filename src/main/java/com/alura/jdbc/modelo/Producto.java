package com.alura.jdbc.modelo;

public class Producto {
	private Integer Id;
	private String nombre;
	private String descripcion;
	private Integer cantidad;
	
	public Producto(String nombre, String descripcion, Integer cantidad) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setId(int Id) {
		// TODO Auto-generated method stub
		this.Id = Id;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %s, nombre : %s, descripcion : %s, cantidad: %d", this.Id, this.nombre, this.descripcion, this.cantidad);
		
	}
}
