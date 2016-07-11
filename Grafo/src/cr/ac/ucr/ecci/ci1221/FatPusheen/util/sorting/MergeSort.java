package cr.ac.ucr.ecci.ci1221.FatPusheen.util.sorting;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class MergeSort<T extends Comparable<T>> {

	private T[] auxT;

	@SuppressWarnings("unchecked")
	public void mergeSortTopBottom(List<T> lista) {
		auxT = (T[]) new Comparable[lista.size()];
		separador(lista, 1, lista.size());
	}

	private void separador(List<T> lista, int inicio, int ultimo) {

		if (ultimo <= inicio) {
			return;
		}
		int mitad = (ultimo - inicio) / 2 + inicio;
		separador(lista, inicio, mitad);
		separador(lista, mitad + 1, ultimo);
		merge(lista, inicio, mitad, ultimo);

	}

	private void merge(List<T> lista, int inicio, int mitad, int ultimo) {
		int i = inicio;
		int b = mitad + 1;

		for (int x = inicio; x <= lista.size(); x++) {
			auxT[x - 1] = lista.get(x);
		}
		for (int y = inicio; y <= ultimo; y++) {
			if (i > mitad) {
				lista.set(y, auxT[b++ - 1]);
			} else if (b > ultimo) {
				lista.set(y, auxT[i++ - 1]);
			} else if (auxT[b - 1].compareTo(auxT[i - 1]) < 0) {
				lista.set(y, auxT[b++ - 1]);
			} else {
				lista.set(y, auxT[i++ - 1]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void mergeSortBottomTop(List<T> lista) {
		int tamano = lista.size();
		auxT = (T[]) new Comparable[tamano];
		for (int i = 1; i < tamano; i = i + i) {
			for (int z = 0; z < tamano - i; z += i + i) {
				merge(lista, z + 1, z + i, Math.min(z + i + i, tamano));
			}
		}
	}
}
