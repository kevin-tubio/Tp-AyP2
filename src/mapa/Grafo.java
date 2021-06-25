package mapa;

import java.util.PriorityQueue;
import excepciones.DestinoInalcanzableException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
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

	/**
	 * pre : 'origen' y 'destino' estan dentro del rango del arreglo de pueblos 'pueblos'.
	 * post: agrega un camino a la lista 'caminosAdyacentes' para representar la coneccion entre 2 pueblos.
	 * @param origen
	 * @param destino
	 * @param trayectoEnDias
	 */
	public void agregarCamino(int origen, int destino, int trayectoEnDias) {
		caminosAdyacentes[origen-1].add(new Camino(origen, destino, trayectoEnDias));
	}

	/**
	 * post: establece el indice del pueblo de destino y el pueblo de origen.
	 * @param origen
	 * @param destino
	 */
	public void definirDestino(int origen, int destino) {
		this.origen = origen-1;
		this.destino = destino-1;
	}
	
	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * post: calcula la distancia minima a cada pueblo y registra las secuencia de pueblos a recorrer para lograr dicha distancia.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	public ArrayDeque<Pueblo> calcularTrayecto() throws DestinoInalcanzableException {
		return dijkstra();
	}
	
	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * post: calcula el trayecto a recorrer priorizando los pueblos aliados.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	public ArrayDeque<Pueblo> calcularTrayectoAlternativo() throws DestinoInalcanzableException {
		PriorityQueue<Camino> cola = new PriorityQueue<Camino>();
		int[] cantidad = new int[pueblos.length];
		int[] distancia = new int[pueblos.length];
		int[] predecesor = new int[pueblos.length];
		
		inicializarDistancias(cantidad);
		cantidad[origen] = obtenerCantidad(origen);
		
		cola.offer(new Camino(origen+1, origen+1, 0));
		
		while(!cola.isEmpty()) {
			for(Camino adyacente : caminosAdyacentes[cola.poll().destino()]) {
				if(cantidad[adyacente.destino()] == Integer.MAX_VALUE) {
	 				cantidad[adyacente.destino()] = cantidad[adyacente.origen()] + obtenerCantidad(adyacente.destino());
	 				distancia[adyacente.destino()] = distancia[adyacente.origen()] + adyacente.trayectoEnDias();
	 				predecesor[adyacente.destino()] = adyacente.origen()+1;
					cola.offer(new Camino(adyacente.origen()+1, adyacente.destino()+1, cantidad[adyacente.destino()]));
				}
				else if(cantidad[adyacente.origen()] + obtenerCantidad(adyacente.destino()) < cantidad[adyacente.destino()]) {
					cantidad[adyacente.destino()] = cantidad[adyacente.origen()] + obtenerCantidad(adyacente.destino());
					distancia[adyacente.destino()] = distancia[adyacente.origen()] + adyacente.trayectoEnDias();
					predecesor[adyacente.destino()] = adyacente.origen()+1;
				}
			}
		}
		
		return devolverTrayecto(predecesor);
	}
	
	/**
	 * post: devuelve la cantidad de habitantes de un pueblo.
	 * @param origen
	 * @return
	 */
	private int obtenerCantidad(int origen) {
		int i = pueblos[origen].censarPueblo(); 
		return (pueblos[origen].getClass().equals(PuebloEnemigo.class) ? i : -i);
	}

	/**
	 * post: establece la distancia entre cada pueblo como infinita.
	 * @param distancia
	 * @return
	 */
	private int[] inicializarDistancias(int[] distancia) {
		Arrays.fill(distancia, Integer.MAX_VALUE);
		distancia[origen] = 0;

		return distancia;
	}

	/**
	 * post: comprueba si un pueblo adyacente a otro fue visitado y si la distancia entre el 
	 * 		 pueblo mas el costo del camino es menor a la distancia del pueblo adyacente.  
	 * @param ad
	 * @param visitado
	 * @param dist
	 * @return
	 */
	private boolean verificarSiCaminoEsMasCorto(Camino ad, boolean[] visitado, int[] dist){
		return !visitado[ad.destino()] && dist[ad.origen()] + ad.trayectoEnDias() < dist[ad.destino()];
	}

	/**
	 * post: transforma un arreglo de pueblos predecesores en una pila de pueblos segun el trayecto de la conquista.
	 * @param predecesor
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	private ArrayDeque<Pueblo> devolverTrayecto(int[] predecesor) throws DestinoInalcanzableException {
		ArrayDeque<Pueblo> trayecto = new ArrayDeque<Pueblo>();
		ArrayDeque<Integer> secuenciaDePueblos = new ArrayDeque<Integer>();
		int aux = destino;
		while(predecesor[aux]-1 != -1) {
			secuenciaDePueblos.push(aux);
			trayecto.push(pueblos[aux]);
			aux = predecesor[aux]-1;
		}
		if(aux != origen) {
			throw new DestinoInalcanzableException("No hay ningun camino desde el pueblo de origen al pueblo de destino");
		}
		secuenciaDePueblos.push(origen);
		trayecto.push(pueblos[origen]);
		
		calcularDistancia(secuenciaDePueblos);
		
		return trayecto;
	}
	
	/**
	 * post: calcula la distancia en dias desde el pueblo de origen hasta el pueblo de distino segun la 'secuenciaDePueblos'. 
	 * @param secuenciaDePueblos
	 */
	private void calcularDistancia(ArrayDeque<Integer> secuenciaDePueblos) {
		int distancia = 0;
		while(!secuenciaDePueblos.isEmpty()) {
			Iterator<Camino> i = caminosAdyacentes[secuenciaDePueblos.pop()].iterator();
			boolean buscandoDistancia = true;
			while(!secuenciaDePueblos.isEmpty() && i.hasNext() && buscandoDistancia) {
				Camino actual = i.next();
				if(actual.destino() == secuenciaDePueblos.peek()) {
					distancia += actual.trayectoEnDias();
					buscandoDistancia = false;
				}
			}
		}
		
		this.distanciaAlDestino = distancia;
	}

	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * 		 se calculo previamente el trayecto.
	 * post: devuelve la cantidad de dias que tomaria recorrer el trayecto sin parar en cada pueblo.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	public int getDistanciaAlDestino() {
		return distanciaAlDestino;
	}
	
	/**
	 * pre : el pueblo destino es alcanzable ya que hay un camino que lo conecta directa o indirectamente con el pueblo de origen.
	 * post: calcula la distancia minima a cada pueblo y registra las secuencia de pueblos a recorrer para lograr dicha distancia.
	 * @return
	 * @throws DestinoInalcanzableException
	 */
	private ArrayDeque<Pueblo> dijkstra() throws DestinoInalcanzableException {
		PriorityQueue<Camino> caminosAEvaluar = new PriorityQueue<Camino>();
		int[] distancia = new int[pueblos.length];
		int[] predecesor = new int[pueblos.length];
		boolean[] visitado = new boolean[pueblos.length];

		inicializarDistancias(distancia);
		
		caminosAEvaluar.offer(new Camino(origen+1, origen+1, 0));

		while(!caminosAEvaluar.isEmpty()) {
			Camino camino = caminosAEvaluar.poll();

			if(!visitado[camino.origen()]) {
				visitado[camino.origen()] = true;
				for(Camino adyacente : caminosAdyacentes[camino.origen()]) {
					if(verificarSiCaminoEsMasCorto(adyacente, visitado, distancia)) {
						distancia[adyacente.destino()] = distancia[adyacente.origen()] + adyacente.trayectoEnDias();
						predecesor[adyacente.destino()] = adyacente.origen()+1;
						caminosAEvaluar.offer(new Camino(adyacente.destino()+1, adyacente.destino()+1, distancia[adyacente.destino()]));
					}
				}
			}
		}

		this.distanciaAlDestino = distancia[destino];
		
		return devolverTrayecto(predecesor);
	}
}
