package pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.FueraRangoException;
import personajes.Nortaichian;
import personajes.Radeiteran;
import personajes.Reralopes;
import personajes.Unidad;
import personajes.Wrives;

public class RadeiteranTest {

	private Radeiteran radeiteranEnRango;
	private Radeiteran radeiteranFueraRango;
	private Wrives wrives;
	private Reralopes reralopes;
	private Nortaichian nortaichian;

	@Before
	public void init() {
		this.radeiteranEnRango = new Radeiteran();
		this.radeiteranEnRango.setPosicion(18);

		this.radeiteranFueraRango = new Radeiteran();
		this.radeiteranFueraRango.setPosicion(16);

		this.wrives = new Wrives();
		this.wrives.setPosicion(18);

		this.reralopes = new Reralopes();
		this.reralopes.setPosicion(18);

		this.nortaichian = new Nortaichian();
		this.nortaichian.setPosicion(18);
	}

	@Test
	public void atacarYRecibir() {
		try {
			this.radeiteranEnRango.atacar(this.radeiteranEnRango);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DESMAYADO, this.radeiteranEnRango.getEstado());
	}

	@Test
	public void atacarFueraRango() {
		try {
			this.radeiteranEnRango.atacar(this.radeiteranFueraRango);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void comprobarAumentoAtaque() {
		try {
			this.radeiteranEnRango.atacar(this.nortaichian);
			assertEquals(59, this.radeiteranEnRango.getAtaque());

			this.radeiteranEnRango.atacar(this.wrives);
			assertEquals(65, this.radeiteranEnRango.getAtaque());

			this.radeiteranEnRango.atacar(this.nortaichian);
			assertEquals(74, this.radeiteranEnRango.getAtaque());
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}

}
