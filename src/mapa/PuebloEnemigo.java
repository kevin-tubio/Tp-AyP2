package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;
import personajes.Grupo;

public class PuebloEnemigo extends Pueblo {

	public PuebloEnemigo(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		if(super.obtenerEjercitoNativo() != null) {
			((Grupo)propio).pelear((Grupo)super.obtenerEjercitoNativo());
		}
	}
}
