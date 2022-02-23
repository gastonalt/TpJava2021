package com.tpjava.model;

public class Tamano {
	int id;
	String descripcion;
	int borrado;
	
	public Tamano() {
		
	}
	
	public Tamano(int id, String descripcion) {
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

	public Tamano(int id, String descripcion, int borrado) {
		this.id = id;
		this.descripcion = descripcion;
		this.borrado = borrado;
	}
	
	public Tamano(String descripcion, int borrado) {
		this.descripcion = descripcion;
		this.borrado = borrado;
	}
}
