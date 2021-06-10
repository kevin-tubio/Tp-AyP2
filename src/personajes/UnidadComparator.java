package personajes;

import java.util.Comparator;

public class UnidadComparator implements Comparator<Ejercito> {

	@Override
	public int compare(Ejercito unidad1, Ejercito unidad2) {
		if (unidad1.getSalud() < unidad2.getSalud()) {
			return 1;
		} else if (unidad1.getSalud() > unidad2.getSalud()) {
			return -1;
		}
		return 0;
	}

}
