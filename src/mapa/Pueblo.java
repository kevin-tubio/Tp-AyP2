package mapa;

import personajes.Ejercito;

public class Pueblo {

	private Ejercito ejercitoNativo;

	public Pueblo(Ejercito ejercitoNativo) {
		this.ejercitoNativo = ejercitoNativo;
	}

	public Ejercito visitarPueblo(Ejercito propio) {
		return ejercitoNativo;
	}

	protected int consultarPrioridad() {
		return 0;
	}
}
