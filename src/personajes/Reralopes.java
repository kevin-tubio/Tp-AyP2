package personajes;

import excepciones.DesmayadoException;
import excepciones.FueraRangoException;

public class Reralopes extends Unidad {
	private boolean desconcentrado;
	private int ataquesModificador;

	public Reralopes() {
		super(53, "Catapulta", new int[] { 5, 46 }, 27);
		this.desconcentrado = false;
		this.ataquesModificador = 0;
		super.setCantAtaques(1);
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException, DesmayadoException {
		if (this.getEstado() != Unidad.Estado.DESMAYADO) {
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
				// Resetea la cant de ataques a 0
				super.setCantAtaques(-3);
			}

			if (this.ataquesModificador == 4) {
				super.setAtaque(super.getAtaqueInicial());
			} else {
				this.ataquesModificador++;
			}
		} else {
			throw new DesmayadoException("No puedo atacar, estoy desmayado");
		}
	}

	@Override
	public void recibirAtaque(int ataque) {
		super.recibirAtaque(ataque);
		this.setDesconcentrado(true);
		super.setAtaque(super.getAtaqueInicial());
	}

	@Override
	public void descansar() {
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
