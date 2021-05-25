package personajes;

public abstract class Personaje {
	
	private int salud;
	private String tipoAtaque;
	private int[] rango;
	private int ataque;
	private int posicion;
	private int ataques = 1;
	private int maxSalud;

	public Personaje(int salud, String tipoAtaque, int[] rango, int ataque) {
		this.setSalud(salud);
		this.setTipoAtaque(tipoAtaque);
		this.setRango(rango);
		this.setAtaque(ataque);
		this.setMaxSalud(salud);
	}

	public int getSalud() {
		return salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	public int[] getRango() {
		return rango;
	}

	private void setRango(int[] rango) {
		this.rango = rango;
	}

	public int getAtaque() {
		return ataque;
	}

	protected void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public String getTipoAtaque() {
		return tipoAtaque;
	}

	private void setTipoAtaque(String tipoAtaque) {
		this.tipoAtaque = tipoAtaque;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getAtaques() {
		return ataques;
	}
	
	public void setAtaques(int ataques) {
		this.ataques = ataques;
	}

	public int getMaxSalud() {
		return maxSalud;
	}

	protected void setMaxSalud(int maxSalud) {
		this.maxSalud = maxSalud;

	}

	protected boolean puedeAtacar(Personaje personaje) {
		return this.getRango()[0] <= personaje.getPosicion() && personaje.getPosicion() <= this.getRango()[1];
	}

	public void recibirAtaque(int ataque) {
		this.salud -= ataque;
	}

	public abstract void atacar(Personaje personaje) throws FueraRangoException;
	
	public abstract void descansar();

}
