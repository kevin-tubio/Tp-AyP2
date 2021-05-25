package personajes;

public abstract class Personaje {
	
	private int maxSalud;
	private int saludInicial;
	
	private int[] rangoAtaque;
	private int posicion;
	
	private String tipoAtaque;
	private int danioAtaque;
	private int contadorDeAtaques;

	public Personaje(int saludInicial, String tipoAtaque, int[] rangoAtaque, int danioAtaque) {
		this.setSalud(saludInicial);
		this.setMaxSalud(saludInicial);
		this.tipoAtaque = tipoAtaque;
		this.rangoAtaque = rangoAtaque;
		this.setAtaque(danioAtaque);
		this.contadorDeAtaques = 0;
	}

	public abstract void atacar(Personaje personaje) throws FueraRangoException;
	
	public void recibirAtaque(int ataque) {
		this.saludInicial -= ataque;
	}

	public abstract void descansar();
	
	public int getMaxSalud() {
		return maxSalud;
	}

	protected void setMaxSalud(int maxSalud) {
		this.maxSalud = maxSalud;
	}
	
	public int comprobarSalud() {
		return saludInicial;
	}

	protected void setSalud(int salud) {
		this.saludInicial = salud;
	}

	public int[] getRango() {
		return rangoAtaque;
	}

	public int getAtaque() {
		return danioAtaque;
	}

	protected void setAtaque(int danioAtaque) {
		this.danioAtaque = danioAtaque;
	}

	public String getTipoAtaque() {
		return tipoAtaque;
	}

	public int getContadorAtaques() {
		return contadorDeAtaques;
	}
	
	protected void setContadorAtaques(int ataques) {
		this.contadorDeAtaques = ataques;
	}


	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
}
