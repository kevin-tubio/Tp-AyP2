package personajes;

import java.util.PriorityQueue;
import excepciones.EjercitoDesmayadoException;
import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;

public class Grupo extends Ejercito {

	private PriorityQueue<Ejercito> aliados;
	private PriorityQueue<Ejercito> propios;

	public Grupo() {
		this.aliados = new PriorityQueue<Ejercito>(new UnidadComparator());
		this.propios = new PriorityQueue<Ejercito>(new UnidadComparator());
	}

	public void pelear(Grupo enemigo) throws EjercitoDesmayadoException {
		while(this.getCantidad() != 0 && enemigo.getCantidad() != 0) {
			this.setPosicion(18);
			enemigo.setPosicion(18);
			try {
				atacar(enemigo);
 				if(enemigo.getEstado() != Unidad.Estado.DESMAYADO) {
					enemigo.atacar(this);
				}
			} 
			catch (FueraRangoException | EstadoPiedraException e) {
				System.out.println(e.getMessage());
			} 
			catch (MeditandoException e) {
				this.recibirAtaque(enemigo.getAtaque());
			}
		}
		if(this.getCantidad() == 0) {
			throw new EjercitoDesmayadoException("Nuestro ejercito fue derrotado");
		}
		if(!aliados.isEmpty()) {
			aliados.offer(aliados.poll());
		}
		else {
			propios.offer(propios.poll());
		}
	}


	@Override
	public void atacar(Ejercito enemigo) throws FueraRangoException, MeditandoException, EstadoPiedraException {
		if(!aliados.isEmpty()) {
			aliados.peek().atacar(enemigo);
		}
		else if(!propios.isEmpty()) {
			propios.peek().atacar(enemigo);
		}	
	}

	/*
	 * Método que efectúa la resolución del ataque
	 * 
	 * @param ataque - int
	 */
	protected void recibirAtaque(int ataque) {
		if(!aliados.isEmpty()) {
			aliados.peek().recibirAtaque(ataque);
			if(aliados.peek().getEstado() == Unidad.Estado.DESMAYADO) {
				aliados.poll();
			}
		}
		else if(!propios.isEmpty()){
			propios.peek().recibirAtaque(ataque);
			if(propios.peek().getEstado() == Unidad.Estado.DESMAYADO) {
				propios.poll();
			}
		}
	}

	/* Método que efectúa el descanso del ejercito */
	public void descansar() {
		for (Ejercito unidad : this.aliados) {
			unidad.descansar();
		}

		for (Ejercito unidad : this.propios) {
			unidad.descansar();
		}
	}

	/* Método que agrega una unidad o un grupo al ejercito */
	public void reclutar(Ejercito unidad) {
		this.aliados.offer(unidad);
	}

	public void reclutarPropio(Ejercito unidad) {
		this.propios.offer(unidad);
	}

	public int getSalud() {
		if(!aliados.isEmpty()) {
			return aliados.peek().getSalud();
		}
		else if(!propios.isEmpty()) {
			return propios.peek().getSalud();
		}
		return 0;
	}

	public int getSaludInicial() {
		if(!aliados.isEmpty()) {
			return aliados.peek().getSaludInicial();
		}
		else if(!propios.isEmpty()) {
			return propios.peek().getSaludInicial();
		}
		return 0;
	}

	public int getAtaque() {
		if(!aliados.isEmpty()) {
			return aliados.peek().getAtaque();
		}
		else if(!propios.isEmpty()) {
			return propios.peek().getAtaque();
		}
		return 0;
	}

	public int getPosicion() {
		if(!aliados.isEmpty()) {
			return aliados.peek().getPosicion();
		}
		else if(!propios.isEmpty()) {
			return propios.peek().getPosicion();
		}
		return 0;
	}

	@Override
	public Unidad.Estado getEstado() {
		if(!aliados.isEmpty()) {
			return aliados.peek().getEstado();
		}
		else if(!propios.isEmpty()) {
			return propios.peek().getEstado();
		}
		return Unidad.Estado.DESMAYADO;
	}

	public int getCantidad() {
		return propios.size() + aliados.size();
	}

	@Override
	public void setPosicion(int posicion) {
		if(!aliados.isEmpty()) {
			aliados.peek().setPosicion(posicion);
		}
		else if(!propios.isEmpty()) {
			propios.peek().setPosicion(posicion);
		}
	}
	
	public boolean isDesmayado() {
		return getCantidad() == 0;
	}
	
	public PriorityQueue<Ejercito> getSoldados() {
		if(!aliados.isEmpty()) {
			return aliados;
		}
		return propios;
	}
}