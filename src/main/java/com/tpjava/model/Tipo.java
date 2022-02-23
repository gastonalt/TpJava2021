package com.tpjava.model;

public class Tipo {
	int id;
	String descripcion;
	int borrado;
	
	public Tipo() {
		
	}
	
	public Tipo(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getBorrado() {
		return borrado;
	}

	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}

	public Tipo(int id, String descripcion, int borrado) {
		this.id = id;
		this.descripcion = descripcion;
		this.borrado = borrado;
	}
	
	public Tipo(String descripcion, int borrado) {
		this.descripcion = descripcion;
		this.borrado = borrado;
	}
}
