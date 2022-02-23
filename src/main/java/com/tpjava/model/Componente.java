package com.tpjava.model;

public class Componente {
	private int id;
	private String descripcion;
	private Tamano tamano;
	private Socket socket;
	private int consumo;
	private int precio;
	private Marca marca;
	private Tipo tipo;
	private int borrado;
	private int stock;
	
	public Componente() {
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

	public Tamano getTamano() {
		return tamano;
	}

	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getBorrado() {
		return borrado;
	}

	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Componente(int id, String descripcion, Tamano tamano, Socket socket, int consumo, int precio, Marca marca,
			Tipo tipo, int stock) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.socket = socket;
		this.consumo = consumo;
		this.precio = precio;
		this.marca = marca;
		this.tipo = tipo;
		this.stock = stock;
	}
	
	public Componente(int id, String descripcion, Tamano tamano, Socket socket, int consumo, int precio, Marca marca,
			Tipo tipo, int stock, int borrado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.socket = socket;
		this.consumo = consumo;
		this.precio = precio;
		this.marca = marca;
		this.tipo = tipo;
		this.stock = stock;
		this.borrado = borrado;
	}

	public Componente(String descripcion, Tamano tamano, Socket socket, int consumo, int precio, Marca marca,
			Tipo tipo, int stock) {
		super();
		this.descripcion = descripcion;
		this.tamano = tamano;
		this.socket = socket;
		this.consumo = consumo;
		this.precio = precio;
		this.marca = marca;
		this.tipo = tipo;
		this.stock = stock;
		this.borrado = 0;
	}
	
	
}
