package sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import excepciones.FormatoInvalidoException;
import mapa.*;
import personajes.*;

public class InterpretadorDeArchivos {

	private int numeroDeLinea;
	
	public Mapa crearMapaAPartirDeArchivo(String ruta) throws FormatoInvalidoException {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(ruta));
			try {
				return Mapa.obtenerMapa(crearGrafo(buffer, crearArregloPueblos(buffer)));
			}
			finally { buffer.close(); }
		}
		catch(NumberFormatException e) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": mal formateada");
		}
		catch (FileNotFoundException e) {
			System.err.println("No se encontro el archivo en: " + ruta);
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	private Pueblo[] crearArregloPueblos(BufferedReader buffer) throws FormatoInvalidoException, IOException {
		String lineaAInterpretar = buffer.readLine();
		numeroDeLinea++;
		comprobarLineaActual(lineaAInterpretar);
		
		int cantidadDePueblos = Integer.parseInt(lineaAInterpretar);
		Pueblo[] arregloDePueblos = new Pueblo[cantidadDePueblos];
		
		for(int i = 0; i < cantidadDePueblos; i++) {
			lineaAInterpretar = buffer.readLine();
			numeroDeLinea++;
			comprobarLineaActual(lineaAInterpretar);
			
			String[] dato = lineaAInterpretar.split(" ");
			if((Integer.parseInt(dato[0])) != i+1) {
				throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": numero de pueblo erroneo");
			}
			
			int cantidadDeSoldados = Integer.parseInt(dato[1]);
			Ejercito ejercitoNativo = crearEjercito(cantidadDeSoldados, dato[2]);
			arregloDePueblos[i] = crearPueblo(ejercitoNativo, dato[3]);
		}
		return arregloDePueblos;
	}

	private Ejercito crearEjercito(int cantidadDeSoldados, String tipo) throws FormatoInvalidoException {
		//TODO
		return null;
	}
	
	private Pueblo crearPueblo(Ejercito ejercitoNativo, String relacion) throws FormatoInvalidoException {
		switch(relacion) {
		case "propio":
			return new Pueblo(ejercitoNativo);

		case "aliado":
			return new PuebloAliado(ejercitoNativo);

		case "enemigo":
			return new PuebloEnemigo(ejercitoNativo);
		
		default:
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": relacion entre pueblos mal formateada");
		}
	}
	
	private Grafo crearGrafo(BufferedReader buffer, Pueblo[] pueblos) throws IOException, FormatoInvalidoException {
		Grafo grafo = new Grafo(pueblos);
		//TODO
		return grafo;
	}
	
	private void comprobarLineaActual(String lineaAInterpretar) throws FormatoInvalidoException {
		if(lineaAInterpretar == null || lineaAInterpretar.strip().isEmpty()) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": no deberia estar vacia");
		}
	}
}
