package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;

public class PuebloPropio extends Pueblo {

	public PuebloPropio(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
	}
}
