package pruebas;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import excepciones.DesmayadoException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;
import personajes.Nortaichian;
import personajes.Radeiteran;
import personajes.Reralopes;
import personajes.Unidad;
import personajes.Wrives;

public class WrivesTest {
	private Wrives wriveEnRango;
	private Wrives wriveFueraRango;
	private Reralopes reralopes;
	private Nortaichian nortaichian;
	private Radeiteran raideiterean;

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
	}

	/* Test sobre el método atacar y recibir ataque */
	@Test
	public void atacarYRecibirEnRango() {
		try {
			this.wriveEnRango.atacar(this.wriveEnRango);
		} catch (FueraRangoException | MeditandoException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DESMAYADO, this.wriveEnRango.getEstado());
	}

	@Test
	public void atacarFueraRango() {
		try {
			this.wriveEnRango.atacar(this.wriveFueraRango);
		} catch (FueraRangoException | MeditandoException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DISPONIBLE, this.wriveEnRango.getEstado());
	}

	@Test
	public void atacarPersonajes() {
		try {
			this.wriveEnRango.atacar(this.reralopes);
			this.wriveEnRango.atacar(this.nortaichian);
			// Segundo ataque, al atacar duplica su ataque
			this.wriveEnRango.atacar(this.raideiterean);
		} catch (FueraRangoException | MeditandoException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DESMAYADO, this.reralopes.getEstado());
		assertEquals(Unidad.Estado.DESMAYADO, this.nortaichian.getEstado());
		assertEquals(Unidad.Estado.DESMAYADO, this.raideiterean.getEstado());
	}

	@Test
	public void descansarYAtacar() {
		this.wriveEnRango.descansar();

		try {
			this.wriveEnRango.atacar(this.nortaichian);
		} catch (FueraRangoException | MeditandoException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void atacarYDescansar() {
		try {
			this.wriveEnRango.atacar(this.reralopes);
		} catch (FueraRangoException | MeditandoException | DesmayadoException e) {
			System.out.println(e.getMessage());
		}

		this.wriveEnRango.descansar();

		assertEquals(158, this.wriveEnRango.getSalud());
	}

}
