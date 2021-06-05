package mapa;

import personajes.Ejercito;

public class PuebloAliado extends Pueblo {

	public PuebloAliado(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public Ejercito visitarPueblo(Ejercito propio) {
		super.visitarPueblo(propio);
		propio.descansar();
		propio.reclutar(getEjercitoNativo());
		return propio;
	}
}
