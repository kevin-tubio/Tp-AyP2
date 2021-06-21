package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;
import personajes.Grupo;

public class PuebloAliado extends Pueblo {

	public PuebloAliado(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	/**
	 * pre : 'propio' no es nulo.
	 * post: hace que el ejercito 'propio' descanse y reclute el ejercito nativo de este pueblo.
	 */
	@Override
	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		if(propio != null) {
			propio.descansar();
			((Grupo)propio).reclutar(super.obtenerEjercitoNativo());
		}
	}
}
