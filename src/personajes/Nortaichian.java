package personajes;

import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;

public class Nortaichian extends Unidad {
	public int ataques = 0;
	public boolean esPiedra;
	// Estado para saber si estando convertido en piedra fue atacado al menos una
	// vez
	public boolean fueAtacadoUnaVez = false;

	public Nortaichian() {
		super(66, "Arco", new int[] { 16, 22 }, 18);
		this.esPiedra = false;
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException, EstadoPiedraException{
		if (!this.esPiedra) {
			if (super.puedeAtacar(unidad)) {
				unidad.recibirAtaque(super.getAtaque());
				super.setSalud(super.getSalud() + ((super.getSalud() * 4) / 100));
				if (this.ataques < 2) {
					this.ataques++;
				} else {
					this.ataques = 0;
					super.setAtaque(super.getAtaqueInicial());
				}
			} else {
				throw new FueraRangoException("El personaje se encuentra fuera de rango");
			}
		} else {
			if (this.ataques < 2) {
				this.ataques++;
			} else {
				this.ataques = 0;
				this.esPiedra = false;
				this.fueAtacadoUnaVez = false;
			}
			throw new EstadoPiedraException("No puedo atacar, estoy duro como una piedra");
		}

	}

	@Override
	public void recibirAtaque(int ataque) {
		if (this.esPiedra && !this.fueAtacadoUnaVez) {
			super.setAtaque(super.getAtaque() * 2);
			super.recibirAtaque(ataque / 2);
			this.fueAtacadoUnaVez = true;
		} else if (this.esPiedra && this.fueAtacadoUnaVez) {
			super.recibirAtaque(ataque / 2);
		} else {
			super.recibirAtaque(ataque);
			super.setAtaque(super.getAtaque() * 2);
		}
	}

	public void descansar() {
		super.setSalud(super.getMaxSalud());
		this.esPiedra = true;
	}

}
