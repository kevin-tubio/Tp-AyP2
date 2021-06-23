package pruebasSistema;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import excepciones.DestinoInalcanzableException;
import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import excepciones.InterpretadorException;
import mapa.Mapa;
import sistema.InterpretadorDeArchivos;

public class InterpretadorDeArchivosTest {

	@After
	public void after() {
		Mapa.resetearMapa();
	}
	
	
	@Test
	public void prueba001() throws DestinoInalcanzableException, FueraRangoException, EjercitoDesmayadoException {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		Mapa mapa = null;
		try{
			mapa = interpretador.crearMapa("archivoDeEntrada/invalido/consigna.txt");
		}
		catch (InterpretadorException e) {}
		
		assertTrue(mapa == null);
	}

	@Test
	public void prueba002() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba2.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 6: Se esperaban 2 parametros, obviando el separador", mensaje);
	}

	@Test
	public void prueba003() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba3.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 1: No deberia estar vacia", mensaje);
	}

	@Test
	public void prueba004() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba4.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 4: Se esperaban 4 parametros", mensaje);
	}

	@Test
	public void prueba005() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba5.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 5: Solo un pueblo puede ser marcado como propio", mensaje);
	}

	@Test
	public void prueba006() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba6.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Algun pueblo debe ser marcado como propio", mensaje);
	}

	@Test
	public void prueba007() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba1.txt");
		} 
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 2: Se esperaban 4 parametros", mensaje);
	}
	
	@Test
	public void prueba008() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba7.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 2: Se esperaba un numero", mensaje);
	}
	
	@Test
	public void prueba009() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba8.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 3: Numero de pueblo erroneo, los pueblos deben estar ordenados", mensaje);
	}
	
	@Test
	public void prueba010() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba9.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 4: Relacion entre pueblos invalida", mensaje);
	}
	
	@Test
	public void prueba011() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba10.txt");
		}
		catch (InterpretadorException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 2: Raza de guerrero invalida", mensaje);
	}
	
	@Test
	public void prueba012() throws DestinoInalcanzableException, FueraRangoException, EjercitoDesmayadoException {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		Mapa mapa = null;
		try{
			mapa = interpretador.crearMapa("archivoDeEntrada/valido/consigna.txt");
		}
		catch (InterpretadorException e) {
			fail();
		}		
		assertTrue(mapa != null);
	}
}
