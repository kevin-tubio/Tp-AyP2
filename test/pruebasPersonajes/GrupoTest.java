package pruebasPersonajes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import excepciones.EjercitoDesmayadoException;
import excepciones.EstadoPiedraException;
import excepciones.FueraRangoException;
import excepciones.MeditandoException;
import personajes.Ejercito;
import personajes.Grupo;
import personajes.Nortaichian;
import personajes.Radeiteran;
import personajes.Reralopes;
import personajes.Unidad;
import personajes.Wrives;

public class GrupoTest {

	private Wrives wriveEnRango;
	private Reralopes reralopes;
	private Nortaichian nortaichian;
	private Radeiteran raideiterean;
	private Grupo grupoPropio;
	private Grupo grupoEnemigo;

	@Before
	public void init() {
		this.wriveEnRango = new Wrives();

		this.reralopes = new Reralopes();

		this.nortaichian = new Nortaichian();

		this.raideiterean = new Radeiteran();

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
	public void prueba001() {
		try {
			this.grupoPropio.pelear(this.grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}

		/* Si desmayado es true, significa que el ejercito fue derrotado */
		assertEquals(false, this.grupoPropio.isDesmayado());
	}

	@Test
	public void prueba002() {
		this.grupoPropio.reclutar(wriveEnRango);
		this.grupoPropio.reclutar(reralopes);
		this.grupoPropio.reclutar(nortaichian);
		this.grupoPropio.reclutar(raideiterean);

		/* El size del ejercito es = 8 debido al reclutamiento en el método init() */
		assertEquals(8, this.grupoPropio.getCantidad());
	}

	@Test
	public void prueba003() {
		try {
			/*
			 * Se settea la posición debido a que por defecto es 0 y su modificación
			 * automática se encuentra dentro del método pelear de la clase Grupo. Este caso
			 * es uno en particular
			 */
			Ejercito unidad = this.grupoPropio.getSoldados().peek();
			unidad.setPosicion(18);

			this.grupoPropio.atacar(this.grupoEnemigo.getSoldados().peek());
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

		Unidad unidad = (Unidad) this.grupoEnemigo.getSoldados().peek();

		assertEquals(0, unidad.getSalud());
	}

	@Test
	public void prueba004() {
		try {
			/*
			 * Se settea la posición debido a que por defecto es 0 y su modificación
			 * automática se encuentra dentro del método pelear de la clase Grupo. Este caso
			 * es uno en particular
			 */

			Ejercito unidad = this.grupoPropio.getSoldados().peek();
			unidad.setPosicion(18);

			this.grupoEnemigo.atacar(this.grupoPropio.getSoldados().peek());
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

		Unidad unidad = (Unidad) this.grupoPropio.getSoldados().peek();

		assertEquals(0, unidad.getSalud());
	}

	@Test
	public void prueba005() {
		assertEquals(108, this.grupoPropio.getSalud());
	}

	@Test
	public void prueba006() {
		try {
			this.grupoEnemigo.atacar(this.grupoPropio.getSoldados().peek());
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

		assertEquals(108, this.grupoPropio.getSaludInicial());
	}

	@Test
	public void prueba007() {
		assertEquals(113, this.grupoPropio.getAtaque());
	}

	@Test
	public void prueba008() {
		/* Por defecto la posición es 0 , se ve modificada cuando el grupo ataca */
		assertEquals(0, this.grupoPropio.getPosicion());
	}

	@Test
	public void prueba009() {
		assertEquals(Unidad.Estado.DISPONIBLE, this.grupoPropio.getEstado());
	}

	@Test
	public void prueba010() {
		/* revisión de mensajes lanzados en consola */
		this.grupoPropio.descansar();
	}

	@Test
	public void prueba011() {
		/* Creo un nuevo grupo que va a contener dos grupos dentro de sus soldados */
		Grupo grupo = new Grupo();

		/* Recluto dos grupos */
		grupo.reclutar(this.grupoPropio);
		grupo.reclutar(this.grupoPropio);

		/*
		 * Setteo la posicion del grupo (este método aplica sobre el primer elemento del
		 * primer grupo)
		 */
		grupo.setPosicion(18);

		/*
		 * Obtengo el primer grupo de soldados y lo casteo a Grupo debido a que es una
		 * cola de Ejercito
		 */
		Grupo grupo1 = (Grupo) grupo.getSoldados().poll();

		/*
		 * Compruebo que su posición sea 18 (modificación previa con el método
		 * setPosicion)
		 */
		assertEquals(18, grupo1.getPosicion());

		/* Lo desencolo */
		grupo1.getSoldados().poll();

		/* Compruebo que del mismo grupo, el siguiente tenga posición 0 */
		assertEquals(0, grupo1.getPosicion());

		/*
		 * Con esto compruebo que el método setPosicion afecta a una unidad dentro del
		 * grupo
		 */
	}

	@Test
	public void prueba012() {
		Ejercito unidad = new Wrives();
		Grupo grupoPropio = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		grupoPropio.reclutarPropio(unidad);
		grupoEnemigo.reclutar(unidad);
		grupoPropio.setPosicion(18);

		try {
			grupoEnemigo.atacar(grupoPropio);
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void prueba013() {
		Ejercito unidad = new Wrives();
		Grupo grupoPropio = new Grupo();

		grupoPropio.reclutarPropio(unidad);
		grupoPropio.descansar();
	}

	@Test
	public void prueba014() {
		Ejercito unidad = new Nortaichian();

		Grupo grupo = new Grupo();
		grupo.reclutarPropio(unidad);

		grupo.getSoldados();

		assertEquals(66, grupo.getSalud());
		assertEquals(66, grupo.getSaludInicial());
		assertEquals(18, grupo.getAtaque());
		assertEquals(Unidad.Estado.DISPONIBLE, grupo.getEstado());
	}

	@Test
	public void prueba015() {
		Grupo grupo = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		Ejercito unidad = new Wrives();
		unidad.setPosicion(18);

		grupo.reclutarPropio(unidad);
		grupoEnemigo.reclutar(unidad);
		try {
			grupo.atacar(grupoEnemigo);
		} catch (FueraRangoException | MeditandoException | EstadoPiedraException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void prueba016() {
		Grupo grupo = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		Ejercito unidad = new Wrives();
		unidad.setPosicion(18);

		grupo.reclutarPropio(unidad);
		grupoEnemigo.reclutar(unidad);
		try {
			grupo.pelear(grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void prueba017() {
		Grupo grupo = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		Ejercito unidad = new Wrives();
		unidad.setPosicion(18);

		grupoEnemigo.reclutar(unidad);
		try {
			grupo.pelear(grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void prueba018() {
		Grupo grupo = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		Ejercito unidad = new Nortaichian();
		unidad.descansar();
		unidad.setPosicion(18);

		grupo.reclutar(unidad);
		unidad.setPosicion(1);
		grupoEnemigo.reclutar(unidad);

		try {
			grupo.pelear(grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void prueba019() {
		Grupo grupo = new Grupo();
		Grupo grupoEnemigo = new Grupo();

		Ejercito unidad = new Wrives();
		unidad.descansar();
		unidad.setPosicion(18);

		grupo.reclutar(unidad);
		unidad.setPosicion(1);
		grupoEnemigo.reclutar(unidad);

		try {
			grupo.pelear(grupoEnemigo);
		} catch (EjercitoDesmayadoException e) {
			System.out.println(e.getMessage());
		}
	}

}
