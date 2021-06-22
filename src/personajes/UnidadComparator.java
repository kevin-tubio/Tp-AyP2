package personajes;

import java.util.Comparator;

public class UnidadComparator implements Comparator<Ejercito> {

	@Override
	public int compare(Ejercito unidad1, Ejercito unidad2) {
		return Integer.compare(unidad2.getSalud(), unidad1.getSalud());
	}
}
