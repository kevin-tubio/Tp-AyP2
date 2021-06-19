package personajes;

import java.util.PriorityQueue;

import excepciones.EjercitoDesmayadoException;
import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public class Grupo extends Ejercito {

	private PriorityQueue<Ejercito> soldados;
	private boolean estado;

	public Grupo() {
		super();
		this.soldados = new PriorityQueue<Ejercito>(new UnidadComparator());
		this.estado = false;
	}

	/*
	 * Método que inicializa la pelea contra un ejercito enemigo
	 * 
	 * @param ejercitoEnemigo - Grupo
	 */
	public void pelear(Grupo ejercitoEnemigo) throws EjercitoDesmayadoException {
		PriorityQueue<Ejercito> grupo = ejercitoEnemigo.getSoldados();

		Ejercito unidad = null;

		while (!grupo.isEmpty() && !this.soldados.isEmpty()) {

			unidad = this.soldados.peek();

			try {
				unidad.atacar(grupo.peek());
			} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
				System.out.println(e.getMessage());
			}
			unidad.recibirAtaque(grupo.peek().getAtaque());

			// Si se desmaya lo desencolo y no lo vuelvo a encolar
			if (unidad.getEstado() == Unidad.Estado.DESMAYADO) {
				this.soldados.poll();
			}
		}

		/* Si nuestro ejercito perdió lanzo una exception */
		if (this.soldados.isEmpty()) {
			this.setEstado(true);
			throw new EjercitoDesmayadoException("El ejercito fue derrotado");
		} else {
			System.out.println("Vencedor: Ejercito propio");
		}

		// Si se redujo la vida lo encolo
		if (unidad.getSalud() < unidad.getSaludInicial() && !this.soldados.isEmpty()) {
			this.soldados.add(this.soldados.poll());
		}

	}

	/*
	 * Método que ejecuta el ataque correspondiente de cada instancia
	 * 
	 * @param enemigo - Ejercito
	 */
	@Override
	public void atacar(Ejercito enemigo) throws FueraRangoException, MeditandoException {
		try {
			this.soldados.peek().atacar(enemigo);
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Método que efectúa la resolución del ataque
	 * 
	 * @param ataque - int
	 */
	protected void recibirAtaque(int ataque) {
		this.soldados.peek().recibirAtaque(ataque);
	}

	/* Método que efectúa el descanso del ejercito */
	public void descansar() {
		for (Ejercito unidad : this.soldados) {
			unidad.descansar();
		}
	}

	public PriorityQueue<Ejercito> getSoldados() {
		return this.soldados;
	}

	/* Método que agrega una unidad o un grupo al ejercito */
	public void reclutar(Ejercito unidad) {
		this.soldados.add(unidad);
	}

	public int getSalud() {
		return this.soldados.peek().getSalud();
	}

	public int getSaludInicial() {
		return this.soldados.peek().getSaludInicial();
	}

	public int getAtaque() {
		return this.soldados.peek().getAtaque();
	}

	public int getPosicion() {
		return this.soldados.peek().getPosicion();
	}

	@Override
	public Unidad.Estado getEstado() {
		return this.soldados.peek().getEstado();
	}

	public boolean isDesmayado() {
		return estado;
	}

	private void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getCantidad() {
		return this.soldados.size();
	}
}