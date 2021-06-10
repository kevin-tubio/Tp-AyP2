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
	public Ejercito visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		propio.descansar();
		((Grupo)propio).reclutar(super.visitarPueblo(propio));
		return propio;
	}
}
