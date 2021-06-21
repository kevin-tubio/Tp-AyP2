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

public class ReralopesTest {

	private Reralopes reralopesEnRango;
	private Reralopes reralopesFueraRango;
	private Wrives wrives;
	private Nortaichian nortaichian;
	private Radeiteran raideiterean;

	@Before
	public void init() {
		this.reralopesEnRango = new Reralopes();
		this.reralopesEnRango.setPosicion(15);

		this.reralopesFueraRango = new Reralopes();
		this.reralopesFueraRango.setPosicion(1);

		this.wrives = new Wrives();
		this.wrives.setPosicion(15);

		this.nortaichian = new Nortaichian();
		this.nortaichian.setPosicion(15);

		this.raideiterean = new Radeiteran();
		this.raideiterean.setPosicion(15);
	}

	@Test
	public void rarelopesAsierta2deCada4AtaquesPrueba001() throws FueraRangoException {
		Reralopes atacante = new Reralopes(); // Reralopes: da�o de ataque = 27
		// Reralopes: da�o al recibir ataque = ataque, salud inicial 53

		assertEquals(53, atacar(atacante, new Reralopes())); // atacado sufre 0, le erra;
		assertEquals(53, atacar(atacante, new Reralopes())); // atacado sufre 0, le erra;
		assertEquals(26, atacar(atacante, new Reralopes())); // atacado sufre 27, acierta;
		assertEquals(26, atacar(atacante, new Reralopes())); // atacado sufre 27, acierta;
		assertEquals(53, atacar(atacante, new Reralopes())); // atacado sufre 0, le erra;
		assertEquals(53, atacar(atacante, new Reralopes())); // atacado sufre 0, le erra;
		assertEquals(26, atacar(atacante, new Reralopes())); // atacado sufre 27, acierta;
		assertEquals(26, atacar(atacante, new Reralopes())); // atacado sufre 27, acierta;
	}

	private int atacar(Reralopes atacante, Reralopes atacado) throws FueraRangoException {
		atacado.setPosicion(10); // hacer que el atacado este dentro del rango de ataque
		try {
			atacante.atacar(atacado);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
		return atacado.getSalud();
	}

	/* Test sobre el método atacar y recibir ataque */
	@Test
	public void atacarYRecibirEnRango() {
		try {
			this.reralopesEnRango.atacar(this.reralopesEnRango);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DISPONIBLE, this.reralopesEnRango.getEstado());
	}

	@Test
	public void atacarFueraRango() {
		try {
			this.reralopesEnRango.atacar(this.reralopesFueraRango);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DISPONIBLE, this.reralopesEnRango.getEstado());
	}

	@Test
	public void atacarPersonajes() {
		try {
			this.reralopesEnRango.atacar(this.wrives);
			this.reralopesEnRango.atacar(this.nortaichian);
			this.reralopesEnRango.atacar(this.raideiterean);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(108, this.wrives.getSalud());
		assertEquals(66, this.nortaichian.getSalud());
		assertEquals(9, this.raideiterean.getSalud());
	}

	@Test
	public void descansarYAtacar() {
		this.reralopesEnRango.descansar();

		// Luego de descanzar duplica su daño
		assertEquals(54, this.reralopesEnRango.getAtaque());

		try {
			this.reralopesEnRango.atacar(this.nortaichian);
		} catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}

}
