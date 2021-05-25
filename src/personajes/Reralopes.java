package personajes;

public class Reralopes extends Personaje {
	private boolean desconcentrado;
	private int ataqueInicial;
	private int ataquesModificador;

	public Reralopes() {
		super(53, "Catapulta", new int[]{5, 46}, 27);
		this.ataqueInicial = super.getAtaque();
		this.desconcentrado = false;
		this.ataquesModificador = 0;
	}

	@Override
	public void atacar(Personaje personaje) throws FueraRangoException {
		if (super.puedeAtacar(personaje)) {
			if (super.getAtaques() > 2) {
				personaje.recibirAtaque(super.getAtaque());
			}
		}

		if (super.getAtaques() < 4) {
			super.setAtaques(super.getAtaques() + 1);
		} else {
			super.setAtaques(1);
		}

		if (this.ataquesModificador == 3) {
			super.setAtaque(ataqueInicial);
		} else {
			this.ataquesModificador++;
		}
	}

	@Override
	public void recibirAtaque(int ataque) {
		this.desconcentrado = true;
		super.setAtaque(ataqueInicial);

	}

	@Override
	public void descansar() {
		this.desconcentrado = false;
		super.setAtaque(super.getAtaque() * 2);
	}

}
