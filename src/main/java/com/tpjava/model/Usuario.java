package com.tpjava.model;

public class Usuario {
	private int id;
	private String username;
	private String password;
	private boolean isAdmin;
	/*
	 * 1) Es usuario
	 * 2) Editor (puede cargar componentes nuevos/pc)
	 * 3) Administrador (Editor y además puede manipular usuarios)
	 * */
	
	public Usuario() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean esAdminBoolean) {
		this.isAdmin = esAdminBoolean;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario(String username, String password, boolean esAdminBoolean) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = esAdminBoolean;
	}
	
	public Usuario(int id, String username, String password, boolean esAdminBoolean) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = esAdminBoolean;
	}
}
