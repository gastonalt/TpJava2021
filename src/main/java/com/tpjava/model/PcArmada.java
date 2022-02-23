package com.tpjava.model;

public class PcArmada {
	
	int id;
	Componente cpu;
	Componente gpu;
	Componente memoria;
	Componente gabinete;
	Componente fuente;
	int borrado;
	int entregado;

	public int getBorrado() {
		return borrado;
	}
	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}
	public int getEntregado() {
		return entregado;
	}
	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Componente getCpu() {
		return cpu;
	}
	public void setCpu(Componente cpu) {
		this.cpu = cpu;
	}
	public Componente getGpu() {
		return gpu;
	}
	public void setGpu(Componente gpu) {
		this.gpu = gpu;
	}
	public Componente getMemoria() {
		return memoria;
	}
	public void setMemoria(Componente memoria) {
		this.memoria = memoria;
	}
	public Componente getGabinete() {
		return gabinete;
	}
	public void setGabinete(Componente gabinete) {
		this.gabinete = gabinete;
	}
	public Componente getFuente() {
		return fuente;
	}
	public void setFuente(Componente fuente) {
		this.fuente = fuente;
	}
	
	public PcArmada(Componente cpu, Componente gpu, Componente memoria, Componente gabinete, Componente fuente, int borrado, int entregado) {
		this.cpu = cpu;
		this.gpu = gpu;
		this.memoria = memoria;
		this.gabinete = gabinete;
		this.fuente = fuente;
		this.borrado = borrado;
		this.entregado = entregado;
	}
	
	public PcArmada(Componente cpu, Componente gpu, Componente memoria, Componente gabinete, Componente fuente) {
		this.cpu = cpu;
		this.gpu = gpu;
		this.memoria = memoria;
		this.gabinete = gabinete;
		this.fuente = fuente;
	}
	
	public PcArmada(int id, Componente cpu, Componente gpu, Componente memoria, Componente gabinete, Componente fuente, int borrado, int entregado) {
		this.id = id;
		this.cpu = cpu;
		this.gpu = gpu;
		this.memoria = memoria;
		this.gabinete = gabinete;
		this.fuente = fuente;
		this.borrado = borrado;
		this.entregado = entregado;
	}

}
