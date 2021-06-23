package pruebas;

import static org.junit.Assert.*;
import org.junit.Test;

import excepciones.EjercitoDesmayadoException;
import excepciones.InterpretadorException;
import mapa.Mapa;
import sistema.Simulador;

public class SimuladorTest {

	@Test(expected = InterpretadorException.class)
	public void Prueba001() throws EjercitoDesmayadoException, InterpretadorException {
		Simulador simu = new Simulador();
		simu.simularConquista("");
	}
	
	@Test
	public void Prueba002() {
		Simulador simu = new Simulador();
		try {
			simu.simularConquista("archivoDeEntrada/valido/noFactible1.txt");
		} catch (EjercitoDesmayadoException e) {
			assertEquals("Nuestro ejercito fue derrotado", e.getMessage());
		} catch (InterpretadorException e) {
			fail();
		}
	}
	
	@Test
	public void Prueba003() {
		Simulador simu = new Simulador();
		try {
			simu.simularConquista("archivoDeEntrada/valido/factible1.txt");
			assertTrue(true);
		} catch (EjercitoDesmayadoException | InterpretadorException e) {
			fail();
		}
	}
	
	@Test
	public void Prueba004() {
		Simulador simu = new Simulador();
		try {
			simu.simularConquistaAlternativa("archivoDeEntrada/valido/noFactible1.txt");
			assertTrue(true);
		} catch (EjercitoDesmayadoException | InterpretadorException e) {
			fail();
		}
	}
	
	@Test
	public void Prueba005() {
		Simulador simu = new Simulador();
		try {
			simu.simularConquista("archivoDeEntrada/invalido/prueba11.txt");
			assertTrue(true);
			Mapa.resetearMapa();
			simu.simularConquistaAlternativa("archivoDeEntrada/invalido/prueba11.txt");
			assertTrue(true);
		} catch (EjercitoDesmayadoException | InterpretadorException e) {
			fail();
		}
	}
}
