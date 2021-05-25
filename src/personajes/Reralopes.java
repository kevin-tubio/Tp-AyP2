package personajes;

public class Reralopes extends Personaje {

	private boolean concentrado;
	private int ataquesConcentrados;

	public Reralopes() {
		super(53, "Catapulta", new int[]{5, 46}, 27);
		this.concentrado = false;
		this.ataquesConcentrados = 0;
	}

	@Override
	public void atacar(Personaje enemigo) throws FueraRangoException {
		if (super.puedeAtacar(enemigo) && aciertaAtaque(super.getContadorAtaques())) {
			if(duplicaDanio()) {
				enemigo.recibirAtaque(super.getAtaque() * 2);
				this.ataquesConcentrados--;
			}
			else {
				enemigo.recibirAtaque(super.getAtaque());
			}
		}
		super.setContadorAtaques(super.getContadorAtaques() + 1);
	}

	@Override
	public void recibirAtaque(int ataque) {
		this.concentrado = false;
		this.ataquesConcentrados = 0;
		super.recibirAtaque(ataque);
	}

	@Override
	public void descansar() {
		this.desconcentrado = false;
		super.setAtaque(super.getAtaque() * 2);
	}

	private boolean aciertaAtaque(int contadorAtaques) {
		return ((contadorAtaques % 4)+1) >= 3;
	}

	private boolean duplicaDanio() {
		return concentrado && ataquesConcentrados > 0;
	}
}
