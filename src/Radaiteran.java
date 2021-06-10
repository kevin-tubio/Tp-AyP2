
public class Radaiteran extends Unidad {
	private int ataques = 0;

	public Radaiteran() {
		super(36, "Shuriken", new int[]{17, 41}, 56);
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException {
		if (super.puedeAtacar(unidad)) {
			unidad.recibirAtaque(super.getAtaque());
			ataques++;
			super.setAtaque(super.getAtaqueInicial() + (3 * ataques));
		} else {
			throw new FueraRangoException("El personaje se encuentra fuera de rango");
		}
	}

	@Override
	public void descanzar() {
		System.out.println("¡Soy un Radaiteran y estoy descansando! No me molesten");
	}

}
