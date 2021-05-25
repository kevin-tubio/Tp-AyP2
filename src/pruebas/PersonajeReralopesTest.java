package pruebas;

import static org.junit.Assert.*;
import org.junit.Test;

import personajes.FueraRangoException;
import personajes.Reralopes;

public class PersonajeReralopesTest {

	@Test
	public void rarelopesAsierta2deCada4AtaquesPrueba001() throws FueraRangoException {
		Reralopes atacante = new Reralopes(); //Reralopes: daño de ataque = 27
		//Reralopes: daño al recibir ataque = ataque, salud inicial 53
		
		assertEquals(53, atacar(atacante, new Reralopes())); //atacado sufre 0, le erra;
		assertEquals(53, atacar(atacante, new Reralopes())); //atacado sufre 0, le erra;
		assertEquals(26, atacar(atacante, new Reralopes())); //atacado sufre 27, acierta;
		assertEquals(26, atacar(atacante, new Reralopes())); //atacado sufre 27, acierta;
		assertEquals(53, atacar(atacante, new Reralopes())); //atacado sufre 0, le erra;
		assertEquals(53, atacar(atacante, new Reralopes())); //atacado sufre 0, le erra;
		assertEquals(26, atacar(atacante, new Reralopes())); //atacado sufre 27, acierta;
		assertEquals(26, atacar(atacante, new Reralopes())); //atacado sufre 27, acierta;
	}
	
	private int atacar(Reralopes atacante, Reralopes atacado) throws FueraRangoException {
		atacado.setPosicion(10); // hacer que el atacado este dentro del rango de ataque
		atacante.atacar(atacado);
		return atacado.comprobarSalud();
	}
}
