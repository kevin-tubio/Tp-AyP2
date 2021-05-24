
public class Reralopes extends Personaje {
	private boolean desconcentrado = false;
	private int ataqueInicial = super.getAtaque();
	private int ataquesModificador = 0;

	public Reralopes(int salud, String tipoAtaque, int[] rango, int ataque) {
		super(salud, tipoAtaque, rango, ataque);
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
		this.setDesconcentrado(true);
		super.setAtaque(ataqueInicial);

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
	//comentario banana

}
