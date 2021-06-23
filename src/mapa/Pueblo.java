package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;

public abstract class Pueblo {

	private Ejercito ejercitoNativo;

	public Pueblo(Ejercito ejercitoNativo) {
		this.ejercitoNativo = ejercitoNativo;
	}

	public abstract void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException;

	/**
	 * post: devuelve la referencia al ejercito de este pueblo.
	 * @return
	 */
	public Ejercito obtenerEjercitoNativo() {
		return this.ejercitoNativo;
	}
}
