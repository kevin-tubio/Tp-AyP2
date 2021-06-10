package sistema;

import java.util.ArrayDeque;
import excepciones.DestinoInalcanzableException;
import excepciones.EjercitoDesmayadoException;
import excepciones.FormatoInvalidoException;
import excepciones.FueraRangoException;
import excepciones.RutaInvalidaException;
import mapa.Mapa;
import mapa.Pueblo;
import personajes.Ejercito;

public class Simulador {

	public void simularConquista(String ruta) throws EjercitoDesmayadoException {
		try {	
			InterpretadorDeArchivos interp = new InterpretadorDeArchivos();

			Mapa mapa = interp.crearMapa(ruta);
			
			ArrayDeque<Pueblo> pilaDePueblos = mapa.obtenerTrayecto();

			Ejercito nuestroEjercito = obtenerEjercitoPropio(pilaDePueblos);

			while(!pilaDePueblos.isEmpty()) {
				nuestroEjercito = pilaDePueblos.pop().visitarPueblo(nuestroEjercito);
			}
			System.out.println("La conquista es factible");	
		}
		catch (FormatoInvalidoException | RutaInvalidaException e) {
			System.out.println("Problema con el archivo de entrada: " + e.getMessage());
		}
		catch (DestinoInalcanzableException e) {
			System.out.println(e.getMessage());
		}
		catch (FueraRangoException e) {
			System.out.println(e.getMessage());
		}
	}

	private Ejercito obtenerEjercitoPropio(ArrayDeque<Pueblo> trayecto) throws FueraRangoException, EjercitoDesmayadoException {
		return trayecto.pop().visitarPueblo(null);
	}
}
