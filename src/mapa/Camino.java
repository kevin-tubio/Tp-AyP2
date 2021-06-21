package mapa;

public class Camino implements Comparable<Camino> {

	private int origen;
	private int destino;
	private int trayectoEnDias;

	public Camino(int origen, int destino, int trayectoEnDias) {
		this.origen = origen-1;
		this.destino = destino-1;
		this.trayectoEnDias = trayectoEnDias;
	}

	public int origen() {
		return origen;
	}

	public int destino() {
		return destino;
	}

	public int trayectoEnDias() {
		return trayectoEnDias;
	}

	@Override
	public int compareTo(Camino o) {
		return Integer.compare(trayectoEnDias, o.trayectoEnDias);
	}
}
