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

	public void simularConquista(String ruta) throws EjercitoDesmayadoException {
		try {	
			InterpretadorDeArchivos interp = new InterpretadorDeArchivos();

			Mapa mapa = null;
			mapa = interp.crearMapa(ruta);
			if(mapa == null) {
				throw new InterpretadorException("No se ha podido crear el mapa segun archivo de entrada");
			}
			
			ArrayDeque<Pueblo> pilaDePueblos = mapa.obtenerTrayecto();

			Ejercito nuestroEjercito = obtenerEjercitoPropio(pilaDePueblos);

			while(!pilaDePueblos.isEmpty()) {
				nuestroEjercito = pilaDePueblos.pop().visitarPueblo(nuestroEjercito);
			}
			System.out.println("La conquista es factible");	
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

	private Ejercito obtenerEjercitoPropio(ArrayDeque<Pueblo> trayecto) throws FueraRangoException, EjercitoDesmayadoException {
		return trayecto.pop().visitarPueblo(null);
	}
}
