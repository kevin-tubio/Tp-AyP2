package mapa;

import java.util.ArrayDeque;
import excepciones.DestinoInalcanzableException;

public class Mapa {

	private Grafo grafo;
	private static Mapa instancia;

	private Mapa(Grafo grafo) {
		this.grafo = grafo;
	}

	public static Mapa obtenerMapa(Grafo grafo) {
		if(instancia == null) {
			instancia = new Mapa(grafo);
		}
		return instancia;
	}

	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * post: devuelve una pila de pueblos ordenada segun el trayecto de la conquista.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	public ArrayDeque<Pueblo> obtenerTrayecto() throws DestinoInalcanzableException {
		return this.grafo.calcularTrayecto();
	}

	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * post: devuelve la cantidad de dias que tomaria recorrer el trayecto sin parar en cada pueblo.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	public int calcularDuracionDelTrayecto() {
		return this.grafo.getDistanciaAlDestino();
	}
}
