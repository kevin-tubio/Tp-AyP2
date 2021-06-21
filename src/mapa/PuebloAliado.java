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
		if(propio != null) {
			propio.descansar();
			((Grupo)propio).reclutar(super.obtenerEjercitoNativo());
		}
	}
}
