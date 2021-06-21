package mapa;

import java.util.PriorityQueue;
import excepciones.DestinoInalcanzableException;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class Grafo {

	private Pueblo[] pueblos;
	private LinkedList<Camino>[] caminosAdyacentes;
	private int origen, destino;
	private int distanciaAlDestino;

	@SuppressWarnings("unchecked")
	public Grafo(Pueblo[] pueblos) {
		this.pueblos = pueblos;
		this.caminosAdyacentes = new LinkedList[pueblos.length];
		for(int i = 0; i < this.caminosAdyacentes.length; i++) {
			this.caminosAdyacentes[i] = new LinkedList<Camino>();
		}
	}

	public void agregarCamino(int origen, int destino, int trayectoEnDias) {
		caminosAdyacentes[origen-1].add(new Camino(origen, destino, trayectoEnDias));
	}

	public void definirDestino(int origen, int destino) {
		this.origen = origen-1;
		this.destino = destino-1;
	}
		
	public ArrayDeque<Pueblo> calcularTrayecto() throws DestinoInalcanzableException {
		
		PriorityQueue<Camino> caminosAEvaluar = new PriorityQueue<Camino>();
		int[] distancia = new int[pueblos.length];
		int[] predecesor = new int[pueblos.length];
		boolean[] visitado = new boolean[pueblos.length];

		inicializarDistancias(distancia);
		
		caminosAEvaluar.add(new Camino(origen+1, origen+1, 0));

		while(!caminosAEvaluar.isEmpty()) {
			Camino camino = caminosAEvaluar.poll();

			if(!visitado[camino.origen()]) {
				visitado[camino.origen()] = true;
				for(Camino adyacente : caminosAdyacentes[camino.origen()]) {
					if(verificarSiCaminoEsMasCorto(adyacente, visitado, distancia)) {
						distancia[adyacente.destino()] = distancia[adyacente.origen()] + adyacente.trayectoEnDias();
						predecesor[adyacente.destino()] = adyacente.origen()+1;
						caminosAEvaluar.add(new Camino(adyacente.destino()+1, adyacente.destino()+1, distancia[adyacente.destino()]));
					}
				}
			}
		}

		this.distanciaAlDestino = distancia[destino];
		
		return devolverTrayecto(predecesor);
	}
	
	private int[] inicializarDistancias(int[] distancia) {
		for(int i = 0; i < pueblos.length; i++) {
			distancia[i] = Integer.MAX_VALUE;
		}
		distancia[origen] = 0;

		return distancia;
	}

	private boolean verificarSiCaminoEsMasCorto(Camino ad, boolean[] visitado, int[] dist){
		return !visitado[ad.destino()] && dist[ad.origen()] + ad.trayectoEnDias() < dist[ad.destino()];
	}

	private ArrayDeque<Pueblo> devolverTrayecto(int[] predecesor) throws DestinoInalcanzableException {
		ArrayDeque<Pueblo> trayecto = new ArrayDeque<Pueblo>();
		int aux = destino;
		while(predecesor[aux]-1 != -1) {
			trayecto.push(pueblos[aux]);
			aux = predecesor[aux]-1;
		}

		if(aux != origen) {
			throw new DestinoInalcanzableException("No hay ningun camino desde el pueblo de origen al pueblo de destino");
		}
		trayecto.push(pueblos[origen]);
		return trayecto;
	}

	public int getDistanciaAlDestino() {
		return distanciaAlDestino;
	}
}
