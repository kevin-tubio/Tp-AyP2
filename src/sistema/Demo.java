package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import excepciones.EjercitoDesmayadoException;
import excepciones.InterpretadorException;

public class Demo {

	private static BufferedReader buffer;
	
	public static void main(String[] args) {
		String ruta = null;
		try {
			buffer = new BufferedReader(new InputStreamReader(System.in));
			boolean ejecutando = true;

			while (ejecutando) {
				try {
					imprimirOpcionesMenu();
					int opcion = Integer.parseInt(buffer.readLine());
					switch (opcion) {
					case 1:
						presentarOpcion(1, "Escriba la ruta y nombre del archivo Ej: directorio/archivo.txt");
						Simulador simulador = new Simulador();
						ruta = buffer.readLine();
						print("");
						simulador.simularConquista(ruta);
						esperarEnter();
						break;

					case 2:
						presentarOpcion(opcion, "Progama Terminado.");
						ejecutando = false;
						break;

					default:
						print("", "Opcion invalida");
						esperarEnter();
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Debe ingresar un numero");
					esperarEnter();
				} 
				catch (EjercitoDesmayadoException e) {
					print("La conquista no es factible");
					print("-----------------------------------------------------------------");
					ofrecerAlternativa(buffer, ruta);
					esperarEnter();
				}
				catch (InterpretadorException e) {
					System.out.println("Problema con el archivo de entrada: " + e.getMessage());
					System.out.println("Pruebe con un archivo valido");
					esperarEnter();
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		finally {
			try{
				if (buffer != null) {
					buffer.close();
				}	
			}
			catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void ofrecerAlternativa(BufferedReader buffer, String ruta) throws IOException {
		boolean ejecutando = true;
		while (ejecutando) {
			try {
				print("¿Desea reintentar la conquista usando otro camino?", "si / no");
				switch (buffer.readLine()) {
				case "si":
					Simulador simulador = new Simulador();
					print("");
					simulador.simularConquistaAlternativa(ruta);
					ejecutando = false;
					break;
					
				case "no":
					print("");
					ejecutando = false;
					break;
				
				default:
					print("", "Opcion invalida");
					esperarEnter();
				}
			}
			catch (EjercitoDesmayadoException e) {
				print("La conquista no es factible por el camino alternativo, pruebe con otro archivo");
				ejecutando = false;
			}
			catch (InterpretadorException e) {
				System.out.println("Problema con el archivo de entrada: " + e.getMessage());
			}
		}
	}

	private static void imprimirOpcionesMenu() {
		print("|***************************************************************|");
		print("|-------------Elija una opcion ingresando un entero-------------|");
		print("|                                                               |");
		print("| (1) Simular Conquista                                         |");
		print("| (2) Salir                                                     |");
		print("|_______________________________________________________________|");
	}

	private static void presentarOpcion(int numeroDeOpcion, String mensaje) {
		print("*****************************************************************", ("Opcion: " + numeroDeOpcion),
				"-----------", "");
		if (mensaje.length() > 0) {
			print(mensaje, "-----------------------------------------------------------------");
		}
	}
	
	private static void print(String... mensaje) {
		for (String msg : mensaje) {
			System.out.println(msg);
		}
	}
	
	private static void esperarEnter() throws IOException {
		esperarEnter("-----------------------------------------------------------------");
	}

	private static void esperarEnter(String mensaje) throws IOException {
		print(mensaje, "Pulse enter para continuar");
		buffer.readLine();
	}
}
