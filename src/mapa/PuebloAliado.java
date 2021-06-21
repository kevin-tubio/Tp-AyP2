package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;
import personajes.Grupo;

public class PuebloAliado extends Pueblo {

	public PuebloAliado(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		propio.descansar();
		if(super.obtenerEjercitoNativo() != null) {
			((Grupo)propio).reclutar(super.obtenerEjercitoNativo());
		}
	}
}
