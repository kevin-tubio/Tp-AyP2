package personajes;

public abstract class Ejercito extends Personaje {
	
	public Ejercito(int salud, String tipoAtaque, int[] rango, int ataque) {
		super(salud, tipoAtaque, rango, ataque);
		// TODO Auto-generated constructor stub
	}

	public void reclutar(Personaje personaje) {
		//TODO
	}
	
	public void reclutar(Ejercito ejercito) {
		//TODO
	}
	
	public void ordenarFormacion() {
		//TODO
	}
	
	public void abandonarCaido() {
		//TODO
	}
	
	@Override
	public void atacar(Personaje personaje) throws FueraRangoException {
		// TODO Auto-generated method stub
	}

	@Override
	public void descansar() {
		// TODO Auto-generated method stub
	}

	@Override
	public void recibirAtaque(int ataque) {
		// TODO Auto-generated method stub
	}
}
