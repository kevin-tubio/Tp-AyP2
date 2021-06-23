package pruebasMapa;

import static org.junit.Assert.*;
import org.junit.Test;

import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import mapa.Pueblo;
import mapa.PuebloPropio;
import mapa.PuebloAliado;
import mapa.PuebloEnemigo;
import personajes.*;

public class PuebloTest {

	@Test
	public void prueba001() throws FueraRangoException, EjercitoDesmayadoException {
		Pueblo[] listaDePueblos = new Pueblo[3];
		
		Grupo ejercitoNativo = new Grupo();
		ejercitoNativo.reclutar(new Reralopes());
		
		listaDePueblos[0] = new PuebloPropio(ejercitoNativo);
		listaDePueblos[1] = new PuebloAliado(new Reralopes());
		
		listaDePueblos[0].visitarPueblo(ejercitoNativo);
		assertEquals(ejercitoNativo, listaDePueblos[0].obtenerEjercitoNativo());
		
		listaDePueblos[1].visitarPueblo(ejercitoNativo);
		assertEquals(2, ejercitoNativo.getCantidad());
	}
	
	@Test
	public void prueba002() throws FueraRangoException, EjercitoDesmayadoException {
		
		Grupo ejercitoNativo = new Grupo();
		ejercitoNativo.reclutar(new Reralopes());
		
		Pueblo puebloEnemigo = new PuebloEnemigo(new Grupo());
		puebloEnemigo.visitarPueblo(ejercitoNativo);
		assertEquals(1, ejercitoNativo.getCantidad());
	}
	
	@Test
	public void prueba003() throws FueraRangoException {
		Grupo ejercitoNativo = new Grupo();
		ejercitoNativo.reclutar(new Reralopes());
		
		Grupo ejercitoEnemigo = new Grupo();
		ejercitoEnemigo.reclutar(new Reralopes());
		
		try {
			ejercitoNativo.pelear(ejercitoEnemigo);
			assertEquals(1, ejercitoNativo.getCantidad());
		} catch (EjercitoDesmayadoException e) {
			fail();
		}
	}
}
