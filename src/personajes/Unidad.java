package personajes;

import excepciones.FueraRangoException;

public abstract class Unidad extends Ejercito {

	/*
	 * Estos campos son para un mejor manejo a la hora de resetear la salud inicial
	 * o aumentar la salud máxima
	 */
	private int maxSalud; // Referencia del tope de la salud
	private int salud; // Referencia de la salud actual (ésta es la que varía)
	private int saludInicial; // Referencia de la salud inicial

	/*
	 * Campos para un mejor manejo de el reseteo del ataque
	 */
	private int danioAtaque;
	private int ataqueInicial;

	private int[] rangoAtaque;
	private int posicion;
	private String tipoAtaque;
	private int contadorDeAtaques;
	private int cantAtaques;
	private Estado estado;

	public enum Estado {
		DISPONIBLE, DESMAYADO
	}

	public Unidad(int saludInicial, String tipoAtaque, int[] rangoAtaque, int danioAtaque) {
		this.setAtributosSalud(saludInicial);
		this.tipoAtaque = tipoAtaque;
		this.rangoAtaque = rangoAtaque;
		this.setAtributosAtaque(danioAtaque);
		this.contadorDeAtaques = 0;
		this.setEstado(Unidad.Estado.DISPONIBLE);
		this.cantAtaques = 0;
	}

	/*
	 * Inicializa los atributos de ataque y ataque incial
	 * 
	 * @param danioAtaque - Valor del ataque base
	 */
	private void setAtributosAtaque(int danioAtaque) {
		this.setAtaque(danioAtaque);
		this.setAtaqueInicial(danioAtaque);
	}

	/*
	 * Inicializa los atributos de salud, maxSalud y saludInicial
	 * 
	 * @param saludInicial - Valor base de la salud
	 */
	private void setAtributosSalud(int saludInicial) {
		this.setSalud(saludInicial);
		this.setMaxSalud(saludInicial);
		this.setSaludInicial(saludInicial);
	}

	/*
	 * Verifica si el personaje a atacar se encuentra dentro del rango
	 * 
	 * @param enemigo - Unidad al que se pretende atacar
	 */
	public boolean puedeAtacar(Ejercito enemigo) throws FueraRangoException {
		if (this.getRango()[0] <= enemigo.getPosicion() && enemigo.getPosicion() <= this.getRango()[1]) {
			return true;
		} else {
			throw new FueraRangoException("El enemigo se encuentra fuera de rango");
		}
	}

	private void setAtaqueInicial(int danioAtaque) {
		this.ataqueInicial = danioAtaque;
	}

	protected void setSalud(int salud) {
		this.salud = salud;
	}

	protected void setMaxSalud(int maxSalud) {
		this.maxSalud = maxSalud;
	}

	protected void setSaludInicial(int saludInicial) {
		this.saludInicial = saludInicial;
	}

	public int getSalud() {
		return this.salud;
	}

	public void recibirAtaque(int ataque) {
		if (this.getSalud() - ataque <= 0) {
			this.setEstado(Unidad.Estado.DESMAYADO);
			this.setSalud(0);
		} else {
			this.salud -= ataque;
		}
	}

	public int getMaxSalud() {
		return maxSalud;
	}

	public int getSaludInicial() {
		return this.saludInicial;
	}

	public int[] getRango() {
		return rangoAtaque;
	}

	public int getAtaque() {
		return danioAtaque;
	}

	public int getAtaqueInicial() {
		return this.ataqueInicial;
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

	@Override
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCantAtaques() {
		return cantAtaques;
	}

	public void setCantAtaques(int cantidad) {
		this.cantAtaques += cantidad;
	}

	public int getCantidad() {
		return 1;
	}
}