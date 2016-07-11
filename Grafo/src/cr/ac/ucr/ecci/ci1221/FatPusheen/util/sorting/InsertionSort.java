package cr.ac.ucr.ecci.ci1221.FatPusheen.util.sorting;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class InsertionSort<T extends Comparable<T>> {

	public void insertionSort(List<T> lista) {
		int x;
		for (int i = 1; i <= lista.size(); i++) {
			x = i;
			int cambio = i;
			while (x != 0) {
				if (lista.get(x).compareTo(lista.get(cambio)) > 0) {
					swap(lista, cambio, x);
					cambio = x;
				}
				x--;
			}
		}
	}

	private void swap(List<T> lista, int i, int minimo) {
		T aux = lista.get(i);
		lista.set(i, lista.get(minimo));
		lista.set(minimo, aux);
	}
}
