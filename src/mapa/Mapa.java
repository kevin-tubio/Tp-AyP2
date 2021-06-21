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

	public ArrayDeque<Pueblo> obtenerTrayecto() throws DestinoInalcanzableException {
		return this.grafo.calcularTrayecto();
	}

	public int calcularDuracionDelTrayecto() {
		return this.grafo.getDistanciaAlDestino();
	}
}
