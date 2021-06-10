
public abstract class Personaje {
	private int salud;
	private String tipoAtaque;
	private int[] rango;
	private int ataque;
	private final int ataqueInicial;
	private int posicion;
	private int ataques = 1;
	private int maxSalud;
	private boolean esPropio;
	private Estado estado = Personaje.Estado.DISPONIBLE;

	enum Estado {
		DISPONIBLE, DESMAYADO
	}

	public Personaje(int salud, String tipoAtaque, int[] rango, int ataque) {
		this.setSalud(salud);
		this.setTipoAtaque(tipoAtaque);
		this.setRango(rango);
		this.setAtaque(ataque);
		this.setMaxSalud(salud);
		this.ataqueInicial = ataque;
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
		if((this.salud - ataque) <= 0) {
			this.setEstado(Personaje.Estado.DESMAYADO);
		}else {
			this.salud -= ataque;
		}
	}

	public void propio() {
		this.esPropio = true;
	}

	public void aliado() {
		this.esPropio = false;
	}

	public boolean esPropio() {
		return this.esPropio;
	}

	public abstract void atacar(Personaje personaje) throws FueraRangoException;

	public abstract void descansar();

	public int getAtaqueInicial() {
		return ataqueInicial;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
