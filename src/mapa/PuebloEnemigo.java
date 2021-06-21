package mapa;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import personajes.Ejercito;
import personajes.Grupo;

public class PuebloEnemigo extends Pueblo {

	public PuebloEnemigo(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	/**
	 * pre : 'propio' no es nulo.
	 * post: hace que el ejercito 'propio' pelee con el ejercito nativo de este pueblo.
	 */
	@Override
	public void visitarPueblo(Ejercito propio) throws FueraRangoException, EjercitoDesmayadoException {
		if(propio != null) {
			((Grupo)propio).pelear((Grupo)super.obtenerEjercitoNativo());
		}
	}
}
