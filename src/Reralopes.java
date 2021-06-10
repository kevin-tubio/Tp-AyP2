
public class Reralopes extends Unidad {
	private boolean desconcentrado;
	private int ataquesModificador;

	public Reralopes(int posicion) {
		super(53, "Catapulta", new int[] { 5, 46 }, 27, posicion);
		this.desconcentrado = false;
		this.ataquesModificador = 0;
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException {
		if (super.puedeAtacar(unidad)) {
			if (super.getCantAtaques() > 2) {
				unidad.recibirAtaque(super.getAtaque());
			}
		} else {
			throw new FueraRangoException("El personaje se encuentra fuera de rango");
		}

		if (super.getCantAtaques() < 4) {
			super.setCantAtaques(1);
		} else {
			super.setCantAtaques(1);
		}

		if (this.ataquesModificador == 3) {
			super.setAtaque(super.getAtaqueInicial());
		} else {
			this.ataquesModificador++;
		}
	}

	@Override
	public void recibirAtaque(int ataque) {
		this.setDesconcentrado(true);
		super.setAtaque(super.getAtaqueInicial());

	}

	@Override
	public void descanzar() {
		this.setDesconcentrado(false);
		super.setAtaque(super.getAtaque() * 2);
	}

	public boolean isDesconcentrado() {
		return desconcentrado;
	}

	public void setDesconcentrado(boolean desconcentrado) {
		this.desconcentrado = desconcentrado;
	}

}
