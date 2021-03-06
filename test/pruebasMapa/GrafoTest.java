package pruebasMapa;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayDeque;
import mapa.Grafo;
import mapa.Mapa;
import mapa.Pueblo;
import mapa.PuebloPropio;
import personajes.Grupo;
import personajes.Wrives;
import mapa.PuebloAliado;
import mapa.PuebloEnemigo;
import excepciones.DestinoInalcanzableException;

public class GrafoTest {

	@Test
	public void prueba001() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[4];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloEnemigo(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 10);
		grafo.agregarCamino(1, 3, 20);
		grafo.agregarCamino(2, 3, 5);
		grafo.agregarCamino(3, 4, 7);
		grafo.definirDestino(1, 4);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[1] == resultado.pop());
		assertTrue(listaDePueblos[2] == resultado.pop());
		assertTrue(listaDePueblos[3] == resultado.pop());
		assertEquals(22, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba002() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[11];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloEnemigo(null);
		listaDePueblos[3] = new PuebloAliado(null);
		listaDePueblos[4] = new PuebloAliado(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		listaDePueblos[6] = new PuebloEnemigo(null);
		listaDePueblos[7] = new PuebloAliado(null);
		listaDePueblos[8] = new PuebloEnemigo(null);
		listaDePueblos[9] = new PuebloAliado(null);
		listaDePueblos[10] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(3, 7, 5);
		grafo.agregarCamino(3, 6, 5);
		grafo.agregarCamino(4, 8, 10);
		grafo.agregarCamino(5, 8, 10);
		grafo.agregarCamino(6, 10, 10);
		grafo.agregarCamino(7, 10, 10);
		grafo.agregarCamino(9, 11, 5);
		grafo.agregarCamino(8, 11, 11);
		grafo.agregarCamino(1, 2, 5);
		grafo.agregarCamino(2, 5, 5);
		grafo.agregarCamino(5, 9, 10);
		grafo.agregarCamino(6, 9, 3);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(2, 4, 5);
		grafo.agregarCamino(10, 11, 11);
		grafo.definirDestino(1, 11);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[2] == resultado.pop());
		assertTrue(listaDePueblos[5] == resultado.pop());
		assertTrue(listaDePueblos[8] == resultado.pop());
		assertTrue(listaDePueblos[10] == resultado.pop());
		assertEquals(18, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba003() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 10);
		grafo.agregarCamino(1, 4, 10);
		grafo.agregarCamino(2, 3, 5);
		grafo.agregarCamino(4, 5, 5);
		grafo.agregarCamino(3, 6, 1);
		grafo.agregarCamino(5, 6, 1);
		grafo.definirDestino(1, 6);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[1] == resultado.pop());
		assertTrue(listaDePueblos[2] == resultado.pop());
		assertTrue(listaDePueblos[5] == resultado.pop());
		assertEquals(16, grafo.getDistanciaAlDestino());
	}
		
	@Test
	public void prueba004() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloEnemigo(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloAliado(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 5);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(2, 3, 1);
		grafo.agregarCamino(2, 4, 14);
		grafo.agregarCamino(2, 5, 3);
		grafo.agregarCamino(3, 2, 1);
		grafo.agregarCamino(3, 4, 3);
		grafo.agregarCamino(3, 5, 14);
		grafo.agregarCamino(4, 5, 5);
		grafo.agregarCamino(4, 6, 11);
		grafo.agregarCamino(5, 4, 5);
		grafo.agregarCamino(5, 6, 11);
		grafo.definirDestino(1, 6);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[1] == resultado.pop());
		assertTrue(listaDePueblos[4] == resultado.pop());
		assertTrue(listaDePueblos[5] == resultado.pop());
		assertEquals(19, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba005() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloEnemigo(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		listaDePueblos[4] = new PuebloAliado(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(1, 2, 5);
		grafo.agregarCamino(2, 3, 1);
		grafo.agregarCamino(2, 4, 14);
		grafo.agregarCamino(2, 5, 3);
		grafo.agregarCamino(3, 2, 1);
		grafo.agregarCamino(3, 4, 3);
		grafo.agregarCamino(3, 5, 14);
		grafo.agregarCamino(4, 5, 5);
		grafo.agregarCamino(4, 6, 11);
		grafo.agregarCamino(5, 4, 5);
		grafo.agregarCamino(5, 6, 11);
		grafo.definirDestino(1, 6);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[2] == resultado.pop());
		assertTrue(listaDePueblos[3] == resultado.pop());
		assertTrue(listaDePueblos[5] == resultado.pop());
		assertEquals(19, grafo.getDistanciaAlDestino());
	}
	
	@Test(expected = DestinoInalcanzableException.class)
	public void prueba006() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 4, 10);
		grafo.agregarCamino(1, 2, 10);
		grafo.agregarCamino(2, 3, 5);
		grafo.agregarCamino(4, 5, 5);
	
		grafo.definirDestino(1, 6);
		
		grafo.calcularTrayecto();
	}
	
	@Test(expected = DestinoInalcanzableException.class)
	public void prueba007() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
	
		grafo.agregarCamino(2, 3, 5);
		grafo.agregarCamino(4, 5, 5);
		grafo.agregarCamino(3, 6, 1);
		grafo.agregarCamino(5, 6, 1);
		grafo.definirDestino(1, 6);
		
		grafo.calcularTrayecto();
	}
	
	@Test(expected = DestinoInalcanzableException.class)
	public void prueba008() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[6];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloEnemigo(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 4, 10);
		grafo.agregarCamino(1, 2, 10);
		grafo.agregarCamino(3, 6, 1);
		grafo.agregarCamino(5, 6, 1);
		grafo.definirDestino(1, 6);
		
		grafo.calcularTrayecto();
	}
	
	@Test
	public void prueba009() throws DestinoInalcanzableException {
	
		Pueblo[] listaDePueblos = new Pueblo[11];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloEnemigo(null);
		listaDePueblos[2] = new PuebloAliado(null);
		listaDePueblos[3] = new PuebloAliado(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		listaDePueblos[5] = new PuebloAliado(null);
		listaDePueblos[6] = new PuebloAliado(null);
		listaDePueblos[7] = new PuebloEnemigo(null);
		listaDePueblos[8] = new PuebloAliado(null);
		listaDePueblos[9] = new PuebloAliado(null);
		listaDePueblos[10] = new PuebloEnemigo(null);
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 5);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(1, 4, 5);
		grafo.agregarCamino(2, 5, 10);
		grafo.agregarCamino(2, 6, 10);
		grafo.agregarCamino(3, 2, 7);
		grafo.agregarCamino(3, 4, 7);
		grafo.agregarCamino(4, 6, 4);
		grafo.agregarCamino(4, 7, 4);
		grafo.agregarCamino(5, 8, 3);
		grafo.agregarCamino(5, 9, 3);
		grafo.agregarCamino(6, 5, 6);
		grafo.agregarCamino(6, 7, 6);
		grafo.agregarCamino(7, 9, 9);
		grafo.agregarCamino(7, 10, 9);
		grafo.agregarCamino(8, 9, 1);
		grafo.agregarCamino(8, 11, 2);
		grafo.agregarCamino(9, 11, 8);
		grafo.agregarCamino(9, 10, 8);
		grafo.agregarCamino(10, 11, 4);
		grafo.definirDestino(1, 11);
		
		ArrayDeque<Pueblo> resultado = grafo.calcularTrayecto();
		
		assertTrue(listaDePueblos[0] == resultado.pop());
		assertTrue(listaDePueblos[1] == resultado.pop());
		assertTrue(listaDePueblos[4] == resultado.pop());
		assertTrue(listaDePueblos[7] == resultado.pop());
		assertTrue(listaDePueblos[10] == resultado.pop());
		assertEquals(20, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba010() throws DestinoInalcanzableException {
	
		Pueblo[] listaDePueblos = new Pueblo[5];
		
		listaDePueblos[0] = new PuebloPropio(null);
		listaDePueblos[1] = new PuebloAliado(null);
		listaDePueblos[2] = new PuebloEnemigo(null);
		listaDePueblos[3] = new PuebloAliado(null);
		listaDePueblos[4] = new PuebloEnemigo(null);
		
		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 2);
		grafo.agregarCamino(1, 3, 6);
		grafo.agregarCamino(1, 4, 7);
		grafo.agregarCamino(2, 4, 3);
		grafo.agregarCamino(2, 5, 6);
		grafo.agregarCamino(3, 5, 1);
		grafo.agregarCamino(4, 5, 5);
		grafo.definirDestino(1, 5);
		
		Mapa mapa = Mapa.obtenerMapa(grafo);
		
		ArrayDeque<Pueblo> trayecto = mapa.obtenerTrayecto();
		assertTrue(listaDePueblos[0] == trayecto.pop());
		assertTrue(listaDePueblos[2] == trayecto.pop());
		assertTrue(listaDePueblos[4] == trayecto.pop());
		assertEquals(7, grafo.getDistanciaAlDestino()); 
		
		ArrayDeque<Pueblo> trayectoAlternativo = mapa.obtenerTrayectoAlternativo();
		assertTrue(listaDePueblos[0] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[1] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[4] == trayectoAlternativo.pop());
		assertEquals(8, grafo.getDistanciaAlDestino());
		Mapa.resetearMapa();
	}

	@Test
	public void prueba011() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[5];

		Grupo propio = crearEjercitoReralopes(50);
		Grupo aliado1 = crearEjercitoRadeiteran(70);
		Grupo aliado2 = crearEjercitoReralopes(50);
		Grupo enemigo1 = crearEjercitoWrives(30);
		Grupo enemigo2 = crearEjercitoNortaichian(50);

		listaDePueblos[0] = new PuebloPropio(propio);
		listaDePueblos[1] = new PuebloAliado(aliado1);
		listaDePueblos[2] = new PuebloEnemigo(enemigo1);
		listaDePueblos[3] = new PuebloAliado(aliado2);
		listaDePueblos[4] = new PuebloEnemigo(enemigo2);

		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 2);
		grafo.agregarCamino(1, 3, 6);
		grafo.agregarCamino(1, 4, 7);
		grafo.agregarCamino(2, 4, 3);
		grafo.agregarCamino(2, 5, 6);
		grafo.agregarCamino(3, 5, 1);
		grafo.agregarCamino(4, 5, 5);
		grafo.definirDestino(1, 5);

		ArrayDeque<Pueblo> trayectoAlternativo = grafo.calcularTrayectoAlternativo();
		assertTrue(listaDePueblos[0] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[1] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[3] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[4] == trayectoAlternativo.pop());
		assertEquals(10, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba012() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[4];

		Grupo propio = crearEjercitoWrives(100);
		Grupo aliado1 = crearEjercitoReralopes(30);
		Grupo enemigo1 = crearEjercitoNortaichian(400);
		Grupo enemigo2 = crearEjercitoNortaichian(90);

		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 10);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(2, 3, 5);
		grafo.agregarCamino(3, 4, 7);
		grafo.definirDestino(1, 4);

		listaDePueblos[0] = new PuebloPropio(propio);
		listaDePueblos[1] = new PuebloAliado(aliado1);
		listaDePueblos[2] = new PuebloEnemigo(enemigo1);
		listaDePueblos[3] = new PuebloEnemigo(enemigo2);

		ArrayDeque<Pueblo> trayectoAlternativo = grafo.calcularTrayectoAlternativo();
		assertTrue(listaDePueblos[0] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[1] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[2] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[3] == trayectoAlternativo.pop());
		assertEquals(22, grafo.getDistanciaAlDestino());
	}
	
	@Test
	public void prueba013() throws DestinoInalcanzableException {
		Pueblo[] listaDePueblos = new Pueblo[5];

		Grupo propio = crearEjercitoWrives(100);
		Grupo aliado1 = crearEjercitoReralopes(30);
		Grupo aliado2 = crearEjercitoNortaichian(400);
		Grupo aliado3 = crearEjercitoRadeiteran(20);
		Grupo enemigo1 = crearEjercitoNortaichian(90);

		Grafo grafo = new Grafo(listaDePueblos);
		grafo.agregarCamino(1, 2, 3);
		grafo.agregarCamino(1, 3, 5);
		grafo.agregarCamino(1, 4, 7);
		grafo.agregarCamino(1, 5, 2);
		grafo.agregarCamino(2, 3, 3);
		grafo.agregarCamino(2, 5, 1);
		grafo.agregarCamino(3, 4, 2);
		grafo.agregarCamino(3, 5, 2);
		grafo.agregarCamino(4, 5, 8);
		grafo.definirDestino(1, 5);

		listaDePueblos[0] = new PuebloPropio(propio);
		listaDePueblos[1] = new PuebloAliado(aliado1);
		listaDePueblos[2] = new PuebloAliado(aliado2);
		listaDePueblos[3] = new PuebloAliado(aliado3);
		listaDePueblos[4] = new PuebloEnemigo(enemigo1);

		ArrayDeque<Pueblo> trayectoAlternativo = grafo.calcularTrayectoAlternativo();
		assertTrue(listaDePueblos[0] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[1] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[2] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[3] == trayectoAlternativo.pop());
		assertTrue(listaDePueblos[4] == trayectoAlternativo.pop());
		assertEquals(16, grafo.getDistanciaAlDestino());
	}
	
	private Grupo crearEjercitoWrives(int numero) {
		Grupo nuevo = new Grupo();
		for(int i = 0; i < numero; i++) {
			nuevo.reclutarPropio(new Wrives());
		}
		return nuevo;
	}
	
	private Grupo crearEjercitoNortaichian(int numero) {
		Grupo nuevo = new Grupo();
		for(int i = 0; i < numero; i++) {
			nuevo.reclutarPropio(new Wrives());
		}
		return nuevo;
	}
	
	private Grupo crearEjercitoRadeiteran(int numero) {
		Grupo nuevo = new Grupo();
		for(int i = 0; i < numero; i++) {
			nuevo.reclutarPropio(new Wrives());
		}
		return nuevo;
	}
	
	private Grupo crearEjercitoReralopes(int numero) {
		Grupo nuevo = new Grupo();
		for(int i = 0; i < numero; i++) {
			nuevo.reclutarPropio(new Wrives());
		}
		return nuevo;
	}
}
