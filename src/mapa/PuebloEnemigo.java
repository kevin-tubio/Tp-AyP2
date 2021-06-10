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
	public Ejercito visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		((Grupo)propio).pelear((Grupo)super.visitarPueblo(propio));
		return propio;
	}

	@Override
	protected int consultarPrioridad() {
		return super.consultarPrioridad()-1;
	}
}
