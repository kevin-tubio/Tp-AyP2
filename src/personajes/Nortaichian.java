package personajes;

public class Nortaichian extends Personaje {

	private boolean enfurecido, dePiedra;
	
	public Nortaichian() {
		super(66, "Arco", new int[]{16, 22}, 18);
		this.dePiedra = false;
		this.enfurecido = false;
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
