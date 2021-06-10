package excepciones;

public class RutaInvalidaException extends Exception {

	private static final long serialVersionUID = -2411865903353147924L;
	
	public RutaInvalidaException(String msj) {
		super(msj);
	}
}
