package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;

public class Pueblo {

	private Ejercito ejercitoNativo;

	public Pueblo(Ejercito ejercitoNativo) {
		this.ejercitoNativo = ejercitoNativo;
	}

	public Ejercito visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		return ejercitoNativo;
	}

	protected int consultarPrioridad() {
		return 0;
	}
}
