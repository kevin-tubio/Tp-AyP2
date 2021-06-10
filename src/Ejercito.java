
public abstract class Ejercito {

	protected abstract int getAtaque();

	protected abstract void recibirAtaque(int ataque);

	protected abstract void atacar(Ejercito enemigo) throws FueraRangoException, MeditandoException;

	protected abstract int getSalud();

	protected abstract int getSaludInicial();

	protected abstract Unidad.Estado getEstado();

	protected abstract int getPosicion();

	protected abstract void descanzar();

}
