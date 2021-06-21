package personajes;

import java.util.LinkedList;
import java.util.PriorityQueue;

import excepciones.DesmayadoException;
import excepciones.EjercitoDesmayadoException;
import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public class Grupo extends Ejercito {

	private LinkedList<PriorityQueue<Ejercito>> soldados;
	private PriorityQueue<Ejercito> aliados;
	private PriorityQueue<Ejercito> propios;
	private boolean estado;

	public Grupo() {
		super();
		this.soldados = new LinkedList<PriorityQueue<Ejercito>>();
		this.aliados = new PriorityQueue<Ejercito>(new UnidadComparator());
		this.propios = new PriorityQueue<Ejercito>(new UnidadComparator());
		this.estado = false;
		this.soldados.add(this.aliados);
		this.soldados.add(this.propios);
	}

	/*
	 * Método que inicializa la pelea contra un ejercito enemigo
	 * 
	 * @param ejercitoEnemigo - Grupo
	 */
	public void pelear(Grupo ejercitoEnemigo) throws EjercitoDesmayadoException {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		if (ejercito.isEmpty()) {
			this.setEstado(true);
			throw new EjercitoDesmayadoException("El ejercito fue derrotado");
		} else {
			PriorityQueue<Ejercito> grupo = ejercitoEnemigo.getSoldados();

			Ejercito unidad = null;
			Ejercito enemigo = null;

			while (!ejercito.isEmpty()) {

				unidad = ejercito.peek();
				unidad.setPosicion(18);

				enemigo = grupo.peek();
				enemigo.setPosicion(18);

				try {
					unidad.atacar(enemigo);
				} catch (FueraRangoException | MeditandoException | EstadoPiedraException | DesmayadoException e) {
					System.out.println(e.getMessage());
				}
				unidad.recibirAtaque(grupo.peek().getAtaque());

				// Si se desmaya lo desencolo y no lo vuelvo a encolar
				if (unidad.getEstado() == Unidad.Estado.DESMAYADO) {
					ejercito.poll();
				}
			}

			/* Si nuestro ejercito perdió lanzo una exception */
			if (ejercito.isEmpty()) {
				this.setEstado(true);
				throw new EjercitoDesmayadoException("El ejercito fue derrotado");
			} else {
				System.out.println("Vencedor: Ejercito propio");
			}

			// Si se redujo la vida lo encolo
			if (unidad.getSalud() < unidad.getSaludInicial() && !ejercito.isEmpty()) {
				ejercito.add(ejercito.poll());
			}
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
			PriorityQueue<Ejercito> ejercito = this.getSoldados();

			ejercito.peek().atacar(enemigo);
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Método que efectúa la resolución del ataque
	 * 
	 * @param ataque - int
	 */
	protected void recibirAtaque(int ataque) {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		ejercito.peek().recibirAtaque(ataque);
	}

	/* Método que efectúa el descanso del ejercito */
	public void descansar() {
		for (Ejercito unidad : this.soldados.get(0)) {
			unidad.descansar();
		}

		for (Ejercito unidad : this.soldados.get(1)) {
			unidad.descansar();
		}

	}

	public PriorityQueue<Ejercito> getSoldados() {
		PriorityQueue<Ejercito> ejercito = null;

		/* Si el grupo aliado está vacío se obtiene del propio */
		if (!this.soldados.get(0).isEmpty()) {
			ejercito = this.soldados.get(0);
		} else {
			ejercito = this.soldados.get(1);
		}

		return ejercito;
	}

	/* Método que agrega una unidad o un grupo al ejercito */
	public void reclutar(Ejercito unidad) {
		this.soldados.get(0).add(unidad);
	}

	public int getSalud() {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		return ejercito.peek().getSalud();
	}

	public int getSaludInicial() {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		return ejercito.peek().getSaludInicial();
	}

	public int getAtaque() {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		return ejercito.peek().getAtaque();
	}

	public int getPosicion() {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		return ejercito.peek().getPosicion();
	}

	@Override
	public Unidad.Estado getEstado() {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		return ejercito.peek().getEstado();
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

	@Override
	public void setPosicion(int i) {
		PriorityQueue<Ejercito> ejercito = this.getSoldados();

		Ejercito unidad = ejercito.peek();
		unidad.setPosicion(i);
	}

}