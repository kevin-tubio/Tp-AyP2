package mapa;

public class Camino {

	private Pueblo destino;
	private int trayectoEnDias;

	public Camino(Pueblo destino, int trayectoEnDias) {
		this.destino = destino;
		this.trayectoEnDias = trayectoEnDias;
	}

	public Pueblo getDestino() {
		return destino;
	}

	public int getTrayectoEnDias() {
		return trayectoEnDias;
	}
}
