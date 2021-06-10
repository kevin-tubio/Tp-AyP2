package pruebas;

import static org.junit.Assert.*;
import org.junit.Test;

import excepciones.FueraRangoException;
import excepciones.MeditandoException;
import personajes.Wrives;

public class PersonajeWrivesTest {

	@Test
	public void wrivesAtacaConDobleDanioCada2AtaquesPrueba001() throws FueraRangoException, MeditandoException {
		Wrives atacante = new Wrives(); //Wrives: daño de ataque = 113
		//Wrives: daño al recibir ataque = ataque*2, salud inicial 108

		assertEquals(-118, atacar(atacante, new Wrives())); //atacante ataca con 113, atacado sufre 226;
		assertEquals(-344, atacar(atacante, new Wrives())); //atacante ataca con 226, atacado sufre 452;
		assertEquals(-118, atacar(atacante, new Wrives())); //atacante ataca con 113, atacado sufre 226;
		assertEquals(-344, atacar(atacante, new Wrives())); //atacante ataca con 226, atacado sufre 452;
		assertEquals(-118, atacar(atacante, new Wrives())); //atacante ataca con 113, atacado sufre 226;
		assertEquals(-344, atacar(atacante, new Wrives())); //atacante ataca con 226, atacado sufre 452;
	}

	private int atacar(Wrives atacante, Wrives atacado) throws FueraRangoException, MeditandoException {
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado);
		return atacado.comprobarSalud();
	}
}
