package personajes;

import excepciones.DesmayadoException;
import excepciones.FueraRangoException;

public class Radeiteran extends Unidad {
	private int ataques = 0;

	public Radeiteran() {
		super(36, "Shuriken", new int[] { 17, 41 }, 56);
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException, DesmayadoException {
		if (super.puedeAtacar(unidad)) {
			unidad.recibirAtaque(super.getAtaque());
			this.ataques++;
			super.setAtaque(super.getAtaque() + (3 * this.ataques));
		} else {
			throw new FueraRangoException("El personaje se encuentra fuera de rango");
		}
	}

	@Override
	public void descansar() {
		System.out.println("¡Soy un Radaiteran y estoy descansando! No me molesten");
	}

}
