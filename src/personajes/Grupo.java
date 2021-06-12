package personajes;

import java.util.PriorityQueue;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public class Grupo extends Ejercito {

	private PriorityQueue<Ejercito> soldados;

	public Grupo() {
		super();
		this.soldados = new PriorityQueue<Ejercito>(new UnidadComparator());
	}

	public void pelear(Grupo ejercitoEnemigo) throws EjercitoDesmayadoException {
		PriorityQueue<Ejercito> grupo = ejercitoEnemigo.getSoldados();

		Ejercito unidad = null;

		while (!grupo.isEmpty()) {

			unidad = this.soldados.peek();

			try {
				unidad.atacar(grupo.peek());
			} catch (FueraRangoException e) {
				System.out.println(e.getMessage());
			} catch (MeditandoException e) {
				System.out.println(e.getMessage());
			}
			unidad.recibirAtaque(grupo.peek().getAtaque());

			if (this.soldados.isEmpty()) {
				throw new EjercitoDesmayadoException("El ejercito fue derrotado");
			}

			// Si se desmaya lo desencolo y no lo vuelvo a encolar
			if (unidad.getEstado() == Unidad.Estado.DESMAYADO) {
				this.soldados.poll();
			}
		}

		// Si se redujo la vida lo encolo
		if (unidad.getSalud() < unidad.getSaludInicial()) {
			this.soldados.add(this.soldados.poll());
		}
	}

	public void atacar(Unidad unidad) throws FueraRangoException, MeditandoException {
		this.soldados.peek().atacar(unidad);
	}

	public void recibirAtaque(int ataque) {
		this.soldados.peek().recibirAtaque(ataque);
	}

	public void descansar() {
		for (Ejercito unidad : this.soldados) {
			unidad.descansar();
		}
	}

	public PriorityQueue<Ejercito> getSoldados() {
		return this.soldados;
	}

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
	protected void atacar(Ejercito enemigo) throws FueraRangoException, MeditandoException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Unidad.Estado getEstado() {
		return this.soldados.peek().getEstado();
	}

}