package personajes;

import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public abstract class Ejercito {

	protected abstract int getAtaque();

	protected abstract void recibirAtaque(int ataque);

	protected abstract void atacar(Ejercito enemigo)
			throws FueraRangoException, MeditandoException, EstadoPiedraException;

	protected abstract int getSalud();

	protected abstract int getSaludInicial();

	protected abstract Unidad.Estado getEstado();

	protected abstract int getPosicion();

	public abstract void descansar();

	protected abstract void setPosicion(int i);

}
