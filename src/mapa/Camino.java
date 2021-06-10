package mapa;

public class Camino implements Comparable<Camino> {

	private int origen;
	private int destino;
	private int trayectoEnDias;
	private Pueblo puebloDestino;

	public Camino(int origen, int destino, int trayectoEnDias, Pueblo[] pueblos) {
		this.origen = origen-1;
		this.destino = destino-1;
		this.trayectoEnDias = trayectoEnDias;
		this.puebloDestino = pueblos[destino-1];
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
		if(this.trayectoEnDias == o.trayectoEnDias) {
			return Integer.compare(o.puebloDestino.consultarPrioridad(), this.puebloDestino.consultarPrioridad());
		}
		return Integer.compare(trayectoEnDias, o.trayectoEnDias);
	}
}
