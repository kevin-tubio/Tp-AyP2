package pruebas;

import static org.junit.Assert.*;
import org.junit.Test;
import excepciones.FormatoInvalidoException;
import excepciones.RutaInvalidaException;
import sistema.InterpretadorDeArchivos;

public class InterpretadorDeArchivosTest {

	@Test
	public void prueba001() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba1.txt");
		} 
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage();
		}
		catch (RutaInvalidaException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 2: Falta el parametro 1", mensaje);
	}

	@Test
	public void prueba002() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba2.txt");
		}
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage();
		}
		catch (RutaInvalidaException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 6: Se esperaban tres parametros, contando el separador", mensaje);
	}

	@Test
	public void prueba003() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba3.txt");
		}
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage(); 
		}
		catch (RutaInvalidaException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 1: no deberia estar vacia", mensaje);
	}

	@Test
	public void prueba004() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba4.txt");
		}
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage();
		}
		catch (RutaInvalidaException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Linea 4: Falta el parametro 2", mensaje);
	}

	@Test
	public void prueba005() {
		InterpretadorDeArchivos interpretador = new InterpretadorDeArchivos();
		String mensaje = "";
		
		try {
			interpretador.crearMapa("archivoDeEntrada/invalido/prueba5.txt");
		}
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage();
		}
		catch (RutaInvalidaException e) {
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
		catch (FormatoInvalidoException e) {
			mensaje = e.getMessage();
		}
		catch (RutaInvalidaException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("Algun pueblo debe ser marcado como propio", mensaje);
	}
}
