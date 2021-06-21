package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;

public class Pueblo {

	private Ejercito ejercitoNativo;

	public Pueblo(Ejercito ejercitoNativo) {
		this.ejercitoNativo = ejercitoNativo;
	}

	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {}

	public Ejercito obtenerEjercitoNativo() {
		return this.ejercitoNativo;
	}
}
