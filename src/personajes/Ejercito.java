package personajes;

import excepciones.DesmayadoException;
import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public abstract class Ejercito {

	protected abstract int getAtaque();

	protected abstract void recibirAtaque(int ataque);

	protected abstract void atacar(Ejercito enemigo)
			throws FueraRangoException, MeditandoException, EstadoPiedraException, DesmayadoException;

	protected abstract int getSalud();

	protected abstract int getSaludInicial();

	protected abstract Unidad.Estado getEstado();

	public abstract int getPosicion();

	public abstract void descansar();

	public abstract void setPosicion(int i);

}
