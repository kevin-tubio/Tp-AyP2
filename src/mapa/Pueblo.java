package mapa;

import personajes.Ejercito;

public class Pueblo {

	public enum Relacion{
		PROPIO, ALIADO, ENEMIGO;
	}
	
	private boolean visitado;
	private Relacion relacion;
	private Ejercito ejercitoNativo;
	
	public Pueblo(Ejercito ejercitoNativo, Relacion relacion) {
		this.relacion = relacion;
		this.ejercitoNativo = ejercitoNativo;
	}

	public Relacion consultarRelacion() {
		return relacion;
	}
	
	public Ejercito devolverEjercito() {
		return ejercitoNativo;
	}

	public boolean fueVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
}
