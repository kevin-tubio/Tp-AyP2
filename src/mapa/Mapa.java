package mapa;

public class Mapa {

	private Grafo grafo;
	private static Mapa instancia;
	
	private Mapa(Grafo grafo) {
		this.grafo = grafo;
	}

	public static Mapa obtenerMapa(Grafo grafo) {
		if(instancia != null) {
			return instancia;
		}
		return new Mapa(grafo);
	}
}
