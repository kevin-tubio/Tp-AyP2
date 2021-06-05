package mapa;

import sistema.Batalla;
import personajes.Ejercito;

public class PuebloEnemigo extends Pueblo {

	public PuebloEnemigo(Ejercito ejercitoNativo) {
		super(ejercitoNativo);
	}

	@Override
	public Ejercito visitarPueblo(Ejercito propio) {
		Batalla batalla = new Batalla();
		propio = batalla.iniciar(propio, super.visitarPueblo(propio));
		return propio;
	}

	@Override
	protected int consultarPrioridad() {
		return super.consultarPrioridad()-1;
	}
}
