package pruebasPersonajes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;
import personajes.Nortaichian;
import personajes.Radeiteran;
import personajes.Reralopes;
import personajes.Unidad;
import personajes.Wrives;

public class NortaichianTest {

	private Nortaichian nortaichianEnRango;
	private Nortaichian nortaichianFueraRango;
	private Wrives wrives;
	private Radeiteran radeiteran;
	private Reralopes reralopes;

	@Before
	public void init() {
		this.nortaichianEnRango = new Nortaichian();
		this.nortaichianEnRango.setPosicion(17);

		this.nortaichianFueraRango = new Nortaichian();
		this.nortaichianFueraRango.setPosicion(15);

		this.wrives = new Wrives();
		this.wrives.setPosicion(17);

		this.radeiteran = new Radeiteran();
		this.radeiteran.setPosicion(17);

		this.reralopes = new Reralopes();
		this.reralopes.setPosicion(17);
	}

	@Test
	public void prueba001() {
		try {
			this.nortaichianEnRango.atacar(this.wrives);
			this.nortaichianEnRango.atacar(this.nortaichianFueraRango);
		} catch (FueraRangoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DISPONIBLE, this.nortaichianEnRango.getEstado());
		assertEquals(72, this.wrives.getSalud());
	}

	@Test
	public void prueba002() {
		try {
			this.wrives.atacar(this.nortaichianEnRango);
			// Está enfurecido por lo cual su ataque se duplica
			assertEquals(36, this.nortaichianEnRango.getAtaque());

			// Descansa para volver a su estado normal y poder atacar
			this.nortaichianEnRango.descansar();
			this.nortaichianEnRango.atacar(this.wrives);
			this.nortaichianEnRango.atacar(this.reralopes);
			assertEquals(18, this.nortaichianEnRango.getAtaque());
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void prueba003() {
		try {
			this.nortaichianEnRango.descansar();
			this.nortaichianEnRango.atacar(this.nortaichianEnRango);
		} catch (FueraRangoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void prueba004() {
		try {
			this.nortaichianEnRango.atacar(this.nortaichianEnRango);
			// Al descansar recupera su maxSalud
			this.nortaichianEnRango.descansar();
		} catch (FueraRangoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(66, this.nortaichianEnRango.getSalud());
	}

	@Test
	public void prueba005() {
		try {
			this.wrives.atacar(this.nortaichianEnRango);
		} catch (FueraRangoException | MeditandoException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(Unidad.Estado.DESMAYADO, this.nortaichianEnRango.getEstado());
	}

	@Test
	public void prueba006() {
		this.nortaichianEnRango.descansar();
		this.nortaichianEnRango.recibirAtaque(1);
		this.nortaichianEnRango.recibirAtaque(1);
	}

}
