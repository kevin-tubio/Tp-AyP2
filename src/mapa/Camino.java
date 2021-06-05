package mapa;

public class Camino implements Comparable<Camino> {

	private int origen;
	private int destino;
	private int trayectoEnDias;
	private Pueblo puebloDestino;

	public Camino(int origen, int destino, int trayectoEnDias, Pueblo puebloDestino) {
		this.origen = origen;
		this.destino = destino;
		this.trayectoEnDias = trayectoEnDias;
		this.puebloDestino = puebloDestino;
	}

	public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}

	public int getTrayectoEnDias() {
		return trayectoEnDias;
	}

	@Override
	public int compareTo(Camino o) {
		if(this.trayectoEnDias == o.trayectoEnDias) {
			return Integer.compare(o.puebloDestino.consultarPrioridad(), this.puebloDestino.consultarPrioridad());
		}
		return Integer.compare(trayectoEnDias, o.trayectoEnDias);
	}
}
