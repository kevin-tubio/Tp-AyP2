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

	/**
	 * pre : 'ruta' es la direccion de un archivo valido
	 * post: simula la conquista de la tierra de fantasia, imprime por pantalla si la conquista es factible y la duracion de la misma.
	 * @param ruta
	 * @throws EjercitoDesmayadoException
	 */
	public void simularConquista(String ruta) throws EjercitoDesmayadoException {
		try {	
			InterpretadorDeArchivos interp = new InterpretadorDeArchivos();

			Mapa mapa = null;
			mapa = interp.crearMapa(ruta);
			if(mapa == null) {
				throw new InterpretadorException("No se ha podido crear el mapa con el archivo de entrada provisto");
			}
			
			ArrayDeque<Pueblo> pilaDePueblos = mapa.obtenerTrayecto();

			Ejercito nuestroEjercito = obtenerEjercitoPropio(pilaDePueblos);
			int duracion = mapa.calcularDuracionDelTrayecto() + pilaDePueblos.size();

			while(!pilaDePueblos.isEmpty()) {
				pilaDePueblos.pop().visitarPueblo(nuestroEjercito);
			}
			System.out.println("La conquista es factible");
			System.out.println("La conquista toma " + duracion + " dias");
		}
		catch (InterpretadorException e) {
			System.out.println("Problema con el archivo de entrada: " + e.getMessage());
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
}
