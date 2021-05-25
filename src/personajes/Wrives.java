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
		//TODO definir comportamiento del contador de ataques.
		//Si el wrives ha meditado y no puede atacar
		//(no inflinje danio) el contador de ataques se incrementa??.
	}

	/**
	 * post: reduce la salud del personaje y niega el estado meditativo.
	 */
	@Override
	public void recibirAtaque(int ataque) {
		super.recibirAtaque(ataque * 2);
		this.haMeditado = false;
	}

	/**
	 * pre : 'haMeditado' es falso
	 * post: si se cumple la precondicion entonces se incrementa
	 * 		 la salud actual y la maxima en 50 unidades.
	 * 		 Ademas se adquiere el estado de meditacion.
	 * 		 Si la precondicion no se comple, no sucede nada.
	 */
	@Override
	public void descansar() {
		if(!haMeditado) {
			this.haMeditado = true;
			this.setMaxSalud(getMaxSalud() + 50);
			this.setSalud(comprobarSalud() + 50);
		}
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
