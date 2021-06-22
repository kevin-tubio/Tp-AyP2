package personajes;

import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public class Wrives extends Unidad {
	private boolean meditado;

	public Wrives() {
		super(108, "Magia", new int[] { 14, 28 }, 113);
		this.meditado = false;
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException, MeditandoException {
		if (!meditado) {
			if (super.puedeAtacar(unidad)) {
				unidad.recibirAtaque(super.getAtaque() * (1 + super.getContadorAtaques() % 2));
				super.setContadorAtaques(super.getContadorAtaques() + 1);
			} else {
				throw new FueraRangoException("El personaje se encuentra fuera de rango");
			}
		} else {
			throw new MeditandoException("No puedo atacar, estoy meditando");
		}
	}

	@Override
	public void recibirAtaque(int ataque) {
		this.meditado = false;
		super.recibirAtaque(ataque * 2);
	}

	public boolean isMeditado() {
		return meditado;
	}

	public void setMeditado(boolean meditado) {
		this.meditado = meditado;
	}

	@Override
	public void descansar() {
		this.meditado = true;
		this.setSalud(this.getSalud() + 50);
		super.setSaludInicial(super.getSaludInicial() + 50);
	}

}
