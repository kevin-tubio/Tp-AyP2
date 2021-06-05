package mapa;

import java.util.PriorityQueue;
import excepciones.DestinoInalcanzableException;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class Grafo {

	private Pueblo[] listaDePueblos;
	private LinkedList<Camino>[] listaDeAdyacencia;
	private int origen, destino;

	@SuppressWarnings("unchecked")
	public Grafo(Pueblo[] listaDePueblos) {
		this.listaDePueblos = listaDePueblos;
		this.listaDeAdyacencia = new LinkedList[listaDePueblos.length];
		for(int i = 0; i < this.listaDeAdyacencia.length; i++) {
			this.listaDeAdyacencia[i] = new LinkedList<Camino>();
		}
	}

	public void agregarCamino(int origen, int destino, int trayectoEnDias) {
		Camino nueva = new Camino(origen, destino, trayectoEnDias, listaDePueblos[destino]);
		listaDeAdyacencia[origen].add(nueva);
	}

	public ArrayDeque<Pueblo> calcularTrayecto() throws DestinoInalcanzableException {
		PriorityQueue<Camino> caminoMasCorto = new PriorityQueue<Camino>();
		int[] distancia = new int[listaDePueblos.length];
		int[] predecesor = new int[listaDePueblos.length];
		boolean[] puebloVisitado = new boolean[listaDePueblos.length];

		for(int i = 0; i < listaDePueblos.length; i++) {
			distancia[i] = Integer.MAX_VALUE;
		}
		distancia[origen] = 0;
		caminoMasCorto.add(new Camino(origen, origen, 0, listaDePueblos[origen]));

		while(!caminoMasCorto.isEmpty()) {

			Camino camino = caminoMasCorto.poll();

			if(!puebloVisitado[camino.getOrigen()]) {
				puebloVisitado[camino.getOrigen()] = true;
				for(Camino adyacente : listaDeAdyacencia[camino.getOrigen()]) {
					int desde = adyacente.getOrigen();
					int hasta = adyacente.getDestino();
					if(!puebloVisitado[hasta]) {
						if(distancia[desde] + adyacente.getTrayectoEnDias() < distancia[adyacente.getDestino()]) {
							distancia[hasta] = distancia[desde] + adyacente.getTrayectoEnDias();
							predecesor[hasta] = desde+1;
							caminoMasCorto.add(new Camino(hasta, hasta, adyacente.getTrayectoEnDias(), listaDePueblos[hasta]));
						}
					}
				}
			}
		}

		ArrayDeque<Pueblo> trayecto = new ArrayDeque<Pueblo>();
		int aux = destino;
		while(predecesor[aux]-1 != -1) {
			trayecto.push(listaDePueblos[aux]);
			aux = predecesor[aux]-1;
		}

		if(aux != origen) {
			throw new DestinoInalcanzableException("No hay ningun camino desde el pueblo de origen al pueblo de destino");
		}

		trayecto.push(listaDePueblos[origen]);
		return trayecto;
	}

	public void definirDestino(int origen, int destino) {
		this.origen = origen-1;
		this.destino = destino-1;
	}
}
