package personajes;

public class Nortaichian extends Personaje {

	private boolean enfurecido, dePiedra;
	
	public Nortaichian(int salud, String tipoAtaque, int[] rango, int ataque) {
		super(salud, tipoAtaque, rango, ataque);
		// TODO Auto-generated constructor stub
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
		super.recibirAtaque(ataque);
	}
}
