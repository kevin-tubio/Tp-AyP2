package sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import excepciones.FormatoInvalidoException;
import excepciones.RutaInvalidaException;
import mapa.*;
import personajes.*;

public class InterpretadorDeArchivos {

	private int numeroDeLinea;

	public Mapa crearMapa(String ruta) throws FormatoInvalidoException, RutaInvalidaException {
		BufferedReader buffer = null;
		try {
			buffer = new BufferedReader(new FileReader(ruta));
			return Mapa.obtenerMapa(crearGrafo(buffer, crearArregloPueblos(buffer)));	
		}
		catch (FileNotFoundException e) {
			throw new RutaInvalidaException("No se encontro el archivo en: " + ruta);
		}
		catch(NumberFormatException e) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Se esperaba un numero");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally { 
			try{
				if(buffer != null) {
					buffer.close(); 
				}	
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return null; 
	}

	private Pueblo[] crearArregloPueblos(BufferedReader buffer) throws FormatoInvalidoException, IOException {
		
		String lineaActual = verificarLineaActual(buffer);
		Pueblo[] listaDePueblos = new Pueblo[Integer.parseInt(lineaActual)];
		
		return inicializarArregloPueblos(buffer, listaDePueblos);
	}

	private Pueblo[] inicializarArregloPueblos(BufferedReader buffer, Pueblo[] arreglo) throws FormatoInvalidoException, IOException {
		int aparicionesDePropio = 0;
		
		for(int i = 0; i < arreglo.length; i++) {	
			String lineaActual = verificarLineaActual(buffer);	
			String[] datosLinea = lineaActual.split(" ");
			verificarNumeroDeDatos(datosLinea, 4);
			
			if((Integer.parseInt(datosLinea[0])) != i+1) {
				throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": numero de pueblo erroneo, los pueblos deben estar ordenados");
			}
			
			aparicionesDePropio = verificarAparicionesDePropio(datosLinea[3], aparicionesDePropio);
			if(i == arreglo.length-1 && aparicionesDePropio == 0) {
				throw new FormatoInvalidoException("Algun pueblo debe ser marcado como propio");
			}
			
			Ejercito ejercitoNativo = crearEjercito(Integer.parseInt(datosLinea[1]), datosLinea[2]);
			arreglo[i] = crearPueblo(ejercitoNativo, datosLinea[3]);
		}
		
		return arreglo;
	}

	private Ejercito crearEjercito(int cantidadDeSoldados, String tipo) throws FormatoInvalidoException {
		Grupo nuevoEjercito = new Grupo();
		switch(tipo) {
		case "Wrives":
			for(int i = 0; i < cantidadDeSoldados; i++) {
				nuevoEjercito.reclutar(new Wrives());
			}
			break;

		case "Radeiteran":
			for(int i = 0; i < cantidadDeSoldados; i++) {
				nuevoEjercito.reclutar(new Radeiteran());
			}
			break;
			
		case "Reralopes":
			for(int i = 0; i < cantidadDeSoldados; i++) {
				nuevoEjercito.reclutar(new Reralopes());
			}
			break;
			
		case "Nortaichian":
			for(int i = 0; i < cantidadDeSoldados; i++) {
				nuevoEjercito.reclutar(new Nortaichian());
			}
			break;
			
		default:
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": raza de guerrero invalida");
		}
		return nuevoEjercito;
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
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": relacion entre pueblos invalida");
		}
	}
	
	private Grafo crearGrafo(BufferedReader buffer, Pueblo[] pueblos) throws IOException, FormatoInvalidoException {
		String lineaActual = verificarLineaActual(buffer);
		String[] datosLinea = lineaActual.split("->");
		verificarNumeroDeDatos(datosLinea, 2 , "Se esperaban tres parametros, contando el separador");
		
		int origen = Integer.parseInt(datosLinea[0].strip());
		int destino = Integer.parseInt(datosLinea[1].strip());
		Grafo grafo = new Grafo(pueblos);
		grafo.definirDestino(origen, destino);
		
		return crearCaminosParaGrafo(buffer, grafo);
	}

	private Grafo crearCaminosParaGrafo(BufferedReader buffer, Grafo grafo) throws FormatoInvalidoException, IOException {
		String lineaActual = verificarLineaActual(buffer);
		
		while(lineaActual != null) {
			if (!lineaActual.strip().isEmpty()) {
				String[] datoCaminos = lineaActual.split(" ");
				verificarNumeroDeDatos(datoCaminos, 3);
				
				int origen = Integer.parseInt(datoCaminos[0].strip());
				int destino = Integer.parseInt(datoCaminos[1].strip());
				int trayectoEnDias = Integer.parseInt(datoCaminos[2].strip());
				grafo.agregarCamino(origen, destino, trayectoEnDias);
			}
			lineaActual = buffer.readLine();
		}
		
		return grafo;
	}

	private String verificarLineaActual(BufferedReader buffer) throws FormatoInvalidoException, IOException {
		String lineaActual = buffer.readLine();
		numeroDeLinea++;
		if(lineaActual == null || lineaActual.strip().isEmpty()) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": no deberia estar vacia");
		}
		return lineaActual;
	}

	private void verificarNumeroDeDatos(String[] recibido, int esperado) throws FormatoInvalidoException {
		verificarNumeroDeDatos(recibido, esperado, ("se esperaba " + esperado + " parametros"));	
	}
	
	private void verificarNumeroDeDatos(String[] recibido, int esperado, String mensaje) throws FormatoInvalidoException {
		if(recibido.length != esperado) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": " + mensaje);
		}
		for(int i = 1; i <= esperado; i++) {
			if(recibido[i-1].strip().isEmpty()) {
				throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Falta el parametro " + i);
			}
		}
	}

	private int verificarAparicionesDePropio(String dato, int aparicionesDePropio) throws FormatoInvalidoException {
		if(dato.equals("propio")) {
			aparicionesDePropio++;
			if(aparicionesDePropio > 1) {
				throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Solo un pueblo puede ser marcado como propio");
			}
		}
		return aparicionesDePropio;
	}
}
