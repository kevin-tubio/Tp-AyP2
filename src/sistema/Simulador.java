package sistema;

import java.util.ArrayDeque;
import excepciones.DestinoInalcanzableException;
import excepciones.EjercitoDesmayadoException;
import excepciones.FueraRangoException;
import excepciones.InterpretadorException;
import mapa.Mapa;
import mapa.Pueblo;
import personajes.Ejercito;

public class Simulador {

	public Simulador() {
		Mapa.resetearMapa();
	}
	
	/**
	 * pre : 'ruta' es la direccion de un archivo valido
	 * post: simula la conquista de la tierra de fantasia, imprime por pantalla si la conquista es factible y la duracion de la misma.
	 * @param ruta
	 * @throws EjercitoDesmayadoException
	 */
	public void simularConquista(String ruta) throws EjercitoDesmayadoException, InterpretadorException {
		try {	
			Mapa mapa = crearMapa(ruta);
			
			ArrayDeque<Pueblo> pilaDePueblos = mapa.obtenerTrayecto();

			recorrerPueblos(pilaDePueblos, mapa);
		}
		catch (DestinoInalcanzableException e) {
			System.out.println(e.getMessage());
		}
		catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * pre : 'ruta' es la direccion de un archivo valido
	 * post: simula la conquista alternativa de la tierra de fantasia, imprime por pantalla si la conquista es factible y la duracion de la misma.
	 * @param ruta
	 * @throws EjercitoDesmayadoException
	 */
	public void simularConquistaAlternativa(String ruta) throws EjercitoDesmayadoException, InterpretadorException {
		try {	
			Mapa mapa = crearMapa(ruta);
			
			ArrayDeque<Pueblo> pilaDePueblos = mapa.obtenerTrayectoAlternativo();

			recorrerPueblos(pilaDePueblos, mapa);
		}
		catch (DestinoInalcanzableException e) {
			System.out.println(e.getMessage());
		}
		catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * post: devuelve la referencia al ejercito del primer elemento de la pila de pueblos 'trayecto'
	 * @param trayecto
	 */
	private Ejercito obtenerEjercitoPropio(ArrayDeque<Pueblo> trayecto) throws FueraRangoException, EjercitoDesmayadoException {
		return trayecto.pop().obtenerEjercitoNativo();
	}
	
	/**
	 * post: simula el recorrido por los pueblos del mapa y si el ejercito sobrevive lo informa y calcula la duracion de la conquista.
	 * @param pilaDePueblos
	 * @param mapa
	 * @throws FueraRangoException
	 * @throws EjercitoDesmayadoException
	 */
	private void recorrerPueblos(ArrayDeque<Pueblo> pilaDePueblos, Mapa mapa) throws FueraRangoException, EjercitoDesmayadoException {
		Ejercito nuestroEjercito = obtenerEjercitoPropio(pilaDePueblos);
		int duracion = mapa.calcularDuracionDelTrayecto() + pilaDePueblos.size();

		while(!pilaDePueblos.isEmpty()) {
			pilaDePueblos.pop().visitarPueblo(nuestroEjercito);
		}
		System.out.println("La conquista es factible");
		System.out.println("La conquista toma " + duracion + " dias");
	}
	
	/**
	 * pre : 'ruta' es la direccion de un archivo valido
	 * post: crea un mapa a apartir de un archivo de entrada localizado en la ruta 'ruta'
	 * @param ruta
	 * @return
	 * @throws InterpretadorException
	 */
	private Mapa crearMapa(String ruta) throws InterpretadorException {
		InterpretadorDeArchivos interp = new InterpretadorDeArchivos();
		return interp.crearMapa(ruta);
	}
}
