package cr.ac.ucr.ecci.ci1221.FatPusheen.util.sorting;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class SelectionSort<T extends Comparable<T>> {

	public void selectionSort(List<T> lista) {
		for (int i = 1; i <= lista.size(); i++) {
			int minimo = i;

			for (int x = i + 1; x <= lista.size(); x++) {
				if (lista.get(x).compareTo(lista.get(minimo)) < 0) {
					minimo = x;
				}
			}
			if (i != minimo) {
				swap(lista, i, minimo);
			}
		}
	}

	private void swap(List<T> lista, int i, int minimo) {
		T aux = lista.get(i);
		lista.set(i, lista.get(minimo));
		lista.set(minimo, aux);
	}

}
