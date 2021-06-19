package pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.EjercitoDesmayadoException;
import personajes.Grupo;
import personajes.Nortaichian;
import personajes.Radeiteran;
import personajes.Reralopes;
import personajes.Wrives;

public class GrupoTest {

	private Wrives wriveEnRango;
	private Wrives wriveFueraRango;
	private Reralopes reralopes;
	private Nortaichian nortaichian;
	private Radeiteran raideiterean;
	private Grupo grupoPropio;
	private Grupo grupoEnemigo;

	@Before
	public void init() {
		this.wriveEnRango = new Wrives();
		this.wriveEnRango.setPosicion(15);

		this.wriveFueraRango = new Wrives();
		this.wriveFueraRango.setPosicion(1);

		this.reralopes = new Reralopes();
		this.reralopes.setPosicion(15);

		this.nortaichian = new Nortaichian();
		this.nortaichian.setPosicion(15);

		this.raideiterean = new Radeiteran();
		this.raideiterean.setPosicion(15);

		this.grupoPropio = new Grupo();
		this.grupoEnemigo = new Grupo();

		this.grupoPropio.reclutar(wriveEnRango);
		this.grupoPropio.reclutar(reralopes);
		this.grupoPropio.reclutar(nortaichian);
		this.grupoPropio.reclutar(raideiterean);

		this.grupoEnemigo.reclutar(wriveEnRango);
		this.grupoEnemigo.reclutar(reralopes);
		this.grupoEnemigo.reclutar(nortaichian);
		this.grupoEnemigo.reclutar(raideiterean);
	}

	@Test
	public void luchar() {
		try {
			this.grupoPropio.pelear(this.grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}

		/* Si desmayado es true, significa que el ejercito fue derrotado */
		assertEquals(true, this.grupoPropio.isDesmayado());
	}

	@Test
	public void agregar() {
		this.grupoPropio.reclutar(wriveEnRango);
		this.grupoPropio.reclutar(reralopes);
		this.grupoPropio.reclutar(nortaichian);
		this.grupoPropio.reclutar(raideiterean);

		/* El size del ejercito es = 8 debido al reclutamiento en el método init() */
		assertEquals(8, this.grupoPropio.getCantidad());
	}

}
