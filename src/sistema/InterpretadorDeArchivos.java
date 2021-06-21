package sistema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import excepciones.FormatoInvalidoException;
import excepciones.InterpretadorException;
import excepciones.RutaInvalidaException;
import mapa.*;
import personajes.*;

public class InterpretadorDeArchivos {

	private int numeroDeLinea;

	/**
	 * pre : la direccion 'ruta' contiene una archivo de entrada valido.
	 * 		 el archivo de entrada tiene un formato correcto.
	 * 
	 * post: interpreta el archivo de entrada de la dirreccion 'ruta' y crea 
	 * 		 un mapa segun las especificaciones del archivo.
	 * 		 lanza una excepcion y muestra un mensaje si el archivo no pudo ser interpretado correctamente.
	 * @param ruta
	 * @return
	 * @throws InterpretadorException
	 */
	public Mapa crearMapa(String ruta) throws InterpretadorException {
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

	/**
	 * pre : archivo de entrada valido.
	 * post: crea un arreglo vacio de tipo Pueblo, el tamanio del arreglo depende del archivo de entrada.
	 */
	private Pueblo[] crearArregloPueblos(BufferedReader buffer) throws FormatoInvalidoException, IOException {
		
		String lineaActual = verificarLineaActual(buffer);
		Pueblo[] listaDePueblos = new Pueblo[Integer.parseInt(lineaActual)];
		
		return inicializarArregloPueblos(buffer, listaDePueblos);
	}

	/**
	 * pre : archivo de entrada valido.
	 * post: crea pueblos segun el archivo de entrada y los almacena en 'arreglo', la cantidad, el tipo y la ocupacion
	 * 		 cada pueblo depende del archivo de entrada.
	 *
	 * @param arreglo
	 */
	private Pueblo[] inicializarArregloPueblos(BufferedReader buffer, Pueblo[] arreglo) throws FormatoInvalidoException, IOException {
		int aparicionesDePropio = 0;
		
		for(int i = 0; i < arreglo.length; i++) {	
			String lineaActual = verificarLineaActual(buffer).replaceAll("\\s+"," ").strip();
			String[] datosLinea = lineaActual.split(" ");
			verificarNumeroDeDatos(datosLinea, 4, "");
			
			if((Integer.parseInt(datosLinea[0])) != i+1) {
				throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Numero de pueblo erroneo, los pueblos deben estar ordenados");
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

	/**
	 * pre : archivo de entrada valido.
	 * post: crea un ejercito de personajes.
	 * @param cantidadDeSoldados
	 * @param tipo
	 */
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
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Raza de guerrero invalida");
		}
		return nuevoEjercito;
	}

	/** 
	 * pre : archivo de entrada valido.
	 * post: crea un pueblo segun la 'relacion' y almacena en el mismo a 'ejercitoNativo'.
	 * @param ejercitoNativo
	 * @param relacion
	 */
	private Pueblo crearPueblo(Ejercito ejercitoNativo, String relacion) throws FormatoInvalidoException {
		switch(relacion) {
		case "propio":
			return new Pueblo(ejercitoNativo);

		case "aliado":
			return new PuebloAliado(ejercitoNativo);

		case "enemigo":
			return new PuebloEnemigo(ejercitoNativo);
		
		default:
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Relacion entre pueblos invalida");
		}
	}
	
	/**
	 * pre : archivo de entrada valido.
	 * post: crea un grafo para contener los pueblos del arreglo 'pueblos'.
	 * 		 define el pueblo de origen y el pueblo de destino segun el archivo de entrada.
	 * @param pueblos
	 */
	private Grafo crearGrafo(BufferedReader buffer, Pueblo[] pueblos) throws IOException, FormatoInvalidoException {
		String lineaActual = verificarLineaActual(buffer).replaceAll("\\s+"," ").strip();
		String[] datosLinea = lineaActual.split(" -> ");
		verificarNumeroDeDatos(datosLinea, 2, ", obviando el separador");
		
		int origen = Integer.parseInt(datosLinea[0]);
		int destino = Integer.parseInt(datosLinea[1]);
		Grafo grafo = new Grafo(pueblos);
		grafo.definirDestino(origen, destino);
		
		return crearCaminosParaGrafo(buffer, grafo);
	}

	/**
	 * pre : archivo de entrada valido
	 * post: establece los caminos entre cada pueblo segun el archivo de entrada.
	 * @param grafo
	 */
	private Grafo crearCaminosParaGrafo(BufferedReader buffer, Grafo grafo) throws FormatoInvalidoException, IOException {
		String lineaActual = verificarLineaActual(buffer);
		
		while(lineaActual != null) {
			if (!lineaActual.strip().isEmpty()) {
				String[] datoCaminos = lineaActual.replaceAll("\\s+"," ").strip().split(" ");
				verificarNumeroDeDatos(datoCaminos, 3, "");
				
				int origen = Integer.parseInt(datoCaminos[0]);
				int destino = Integer.parseInt(datoCaminos[1]);
				int trayectoEnDias = Integer.parseInt(datoCaminos[2]);
				grafo.agregarCamino(origen, destino, trayectoEnDias);
			}
			lineaActual = buffer.readLine();
		}
		
		return grafo;
	}

	/**
	 * post: lanza una excepcion si la linea actual del archivo esta vacia o es nula.
	 */
	private String verificarLineaActual(BufferedReader buffer) throws FormatoInvalidoException, IOException {
		String lineaActual = buffer.readLine();
		numeroDeLinea++;
		if(lineaActual == null || lineaActual.strip().isEmpty()) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": No deberia estar vacia");
		}
		return lineaActual;
	}

	/**
	 * pre : archivo de entrada valido.
	 * post: lanza una excepcion si los datos interpretados del archivo ('recibido') no son tantos como 'esperado'.
	 * @param recibido
	 * @param esperado
	 * @param mensaje
	 * @throws FormatoInvalidoException
	 */
	private void verificarNumeroDeDatos(String[] recibido, int esperado, String mensaje) throws FormatoInvalidoException {
		if(recibido.length != esperado) {
			throw new FormatoInvalidoException("Linea " + numeroDeLinea + ": Se esperaban " + esperado + " parametros" + mensaje);
		}
	}

	/**
	 * pre : archivo de entrada valido.
	 * post: lanza una excepcion si en el archivo de entrada se hace referencia personajes propios mas de una vez.
	 * @param dato
	 * @param aparicionesDePropio
	 */
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
