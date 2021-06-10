package personajes;

import excepciones.MeditandoException;

public class Wrives extends Unidad {
	private boolean meditado;

	public Wrives() {
		super(108, "Magia", new int[]{14, 28}, 113);
		this.meditado = false;
	}

	@Override
	public void atacar(Ejercito unidad) throws FueraRangoException, MeditandoException {
		if (!meditado) {
			if (super.puedeAtacar(unidad)) {
				if (super.getCantAtaques() % 2 != 0) {
					unidad.recibirAtaque(super.getAtaque() * 2);
				} else {
					unidad.recibirAtaque(super.getAtaque());
				}
				super.setCantAtaques(1);
			} else {
				throw new FueraRangoException("El personaje se encuentra fuera de rango");
			}
		}else {
			throw new MeditandoException("No puedo atacar, estoy meditando");
		}
	}

	@Override
	public void recibirAtaque(int ataque) {
		super.recibirAtaque(ataque * 2);
		this.meditado = false;
	}

	public boolean isMeditado() {
		return meditado;
	}

	public void setMeditado(boolean meditado) {
		this.meditado = meditado;
	}

	@Override
	public void descanzar() {
		this.meditado = true;
	}

}
