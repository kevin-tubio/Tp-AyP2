package mapa;

import sistema.Batalla;
import personajes.Ejercito;

public class PuebloEnemigo extends Pueblo {

	public PuebloEnemigo(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public Ejercito visitarPueblo(Ejercito propio) {
		super.visitarPueblo(propio);
		Batalla batalla = new Batalla();
		propio = batalla.iniciar(propio, getEjercitoNativo());
		return propio;
	}
}
