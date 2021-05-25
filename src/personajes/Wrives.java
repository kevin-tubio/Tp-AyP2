package personajes;

public class Wrives extends Personaje {
	private boolean meditado = false;

	public Wrives(int salud, String tipoAtaque, int[] rango, int ataque) {
		super(salud, tipoAtaque, rango, ataque);
	}

	@Override
	public void atacar(Personaje personaje) throws FueraRangoException {
		if (!meditado) {
			if (super.puedeAtacar(personaje)) {
				if (super.getAtaques() % 2 == 0 && super.getAtaques() != 0) {
					personaje.recibirAtaque(super.getAtaque() * 2);
				} else {
					personaje.recibirAtaque(super.getAtaque());
				}
				super.setAtaques(super.getAtaques() + 1);
			} else {
				throw new FueraRangoException("El personaje se encuentra fuera de rango");
			}
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
	public void descansar() {
		this.meditado = true;
	}

}
