package pruebas;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import personajes.FueraRangoException;
import personajes.Wrives;

public class PersonajeWrivesTest {

	private Wrives atacante;
	private Wrives atacado;
	
	@Test
	public void wrivesAtacaConDobleDanioCada2AtaquesPrueba001() throws FueraRangoException {
		atacante = new Wrives(); //Wrives: daño de ataque = 113
		atacado = new Wrives(); //Wrives: daño al recibir ataque = ataque*2, salud inicial 108
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		
		assertEquals(108, atacado.comprobarSalud());
		atacante.atacar(atacado); // atacante ataca con 113
		
		assertEquals(-118, atacado.comprobarSalud()); //atacado sufre 226;
		
		atacado = new Wrives();
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado); // atacante ataca con 226
		assertEquals(-344, atacado.comprobarSalud()); //atacado sufre 452;
		
		atacado = new Wrives();
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado); // atacante ataca con 113
		assertEquals(-118, atacado.comprobarSalud()); //atacado sufre 226;
		
		atacado = new Wrives();
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado); // atacante ataca con 226
		assertEquals(-344, atacado.comprobarSalud()); //atacado sufre 452;
		
		atacado = new Wrives();
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado); // atacante ataca con 113
		assertEquals(-118, atacado.comprobarSalud()); //atacado sufre 226;
		
		atacado = new Wrives();
		atacado.setPosicion(20); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado); // atacante ataca con 226
		assertEquals(-344, atacado.comprobarSalud()); //atacado sufre 452;
	}

}
