package personajes;

public class Wrives extends Personaje {
	
	private boolean haMeditado;

	public Wrives() {
		super(108, "Magia", new int[]{14, 28}, 113);
		this.haMeditado = false;
	}

	/**
	 * pre : 'enemigo' debe pertenecer al rango de ataque.
	 * post: reduce la salud del enemigo.
	 */
	@Override
	public void atacar(Personaje enemigo) throws FueraRangoException {
		if (super.puedeAtacar(enemigo) && !haMeditado) {
			if (duplicaAtaque(super.getContadorAtaques())) {
				enemigo.recibirAtaque(super.getAtaque() * 2);
			} else {
				enemigo.recibirAtaque(super.getAtaque());
			}
		}
		super.setContadorAtaques(super.getContadorAtaques() + 1);
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
	
	/**
	 * post: devuelve verdadero si 'contadorAtaques' es inpar.
	 * @param contadorAtaques
	 * @return
	 */
	private boolean duplicaAtaque(int contadorAtaques) {
		return !((contadorAtaques % 2) == 0);
	}
	
}
