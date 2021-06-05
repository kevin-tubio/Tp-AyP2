package mapa;

import personajes.Ejercito;

public class Pueblo {
	
	private boolean visitado;
	private Ejercito ejercitoNativo;
	
	public Pueblo(Ejercito ejercitoNativo) {
		this.ejercitoNativo = ejercitoNativo;
	}

	public Ejercito visitarPueblo(Ejercito propio) {
		this.visitado = true;
		return propio;
	}

	public boolean fueVisitado() {
		return visitado;
	}

	public Ejercito getEjercitoNativo() {
		return ejercitoNativo;
	}
}
