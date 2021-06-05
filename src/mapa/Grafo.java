package mapa;

import java.util.PriorityQueue;
import java.util.LinkedList;

public class Grafo {

	private PriorityQueue<Pueblo> puebloMasCercano;
	private Pueblo[] listaDePueblos;
	private LinkedList<Camino>[] listaDeAdyacencia;
	
	@SuppressWarnings("unchecked")
	public Grafo(Pueblo[] listaDePueblos) {
		this.listaDePueblos = listaDePueblos;
		this.listaDeAdyacencia = new LinkedList[listaDePueblos.length];
		for(int i = 0; i < this.listaDeAdyacencia.length; i++) {
			this.listaDeAdyacencia[i] = new LinkedList<Camino>();
		}
	}
	
	public void agregarCamino(int origen, int destino, int trayectoEnDias) {
		Camino nueva = new Camino(listaDePueblos[destino-1], trayectoEnDias);
		listaDeAdyacencia[origen-1].add(nueva);
	}

	public void definirDestino(int partida, int destino) {
		//TODO
	}

	public Pueblo getPuebloMasCercano() {
		return puebloMasCercano.peek();
	}
}
