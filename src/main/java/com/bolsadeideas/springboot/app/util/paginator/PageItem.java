package com.bolsadeideas.springboot.app.util.paginator;


/*
 * 
 * Esta clase se encarga de enumerar los items o bien registros y saber en cual
 * esta posicionado */
public class PageItem {
	
	private int numero;
	
	private boolean actual;

	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isActual() {
		return actual;
	}
	
	
}
