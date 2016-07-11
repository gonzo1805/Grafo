package cr.ac.ucr.ecci.ci1221.FatPusheen.util.sorting;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class QuickSort<T extends Comparable<T>> {

	public void quickSort(List<T> lista, int inicio, int ultimo) {
		if (inicio < ultimo) {
			int p = partidor(lista, inicio, ultimo);
			quickSort(lista, inicio, p - 1);
			quickSort(lista, p + 1, ultimo);
		}
	}

	/**
	 * Los diferentes casos del pivote estaran comentados dentro mismo codigo,
	 * nombrando el caso con documentacion y luego el codigo en forma de
	 * comentario
	 */
	public int partidor(List<T> lista, int inicio, int ultimo) {

		int posPiv = 0;
		/**
		 * Caso pivote al medio de la lista (default) Ultimo - inicio / 2 nos da
		 * la mitad entre esos dos numeros Al sumarle inicio lo reacomodamos en
		 * el ambito que ocupamos
		 */
		posPiv = ((ultimo - inicio)) / 2 + inicio;
		/**
		 * Caso pivote al final de la lista
		 */
		// posPiv = ultimo;
		/**
		 * Caso pivote al inicio de la lista
		 */
		// posPiv = inicio;
		/**
		 * Caso pivote random dentro del rango de la lista, la funcion usada da
		 * un numero al azar en el rango dado por los parametros de inicio y
		 * ultimo
		 */
		// posPiv = ThreadLocalRandom.current().nextInt(inicio, ultimo);

		T pivote = lista.get(posPiv);
		int pi = inicio;
		int pd = ultimo;

		while (pi < pd) {

			while (lista.get(pi).compareTo(pivote) < 0) {
				pi++;
			}

			while (lista.get(pd).compareTo(pivote) > 0) {
				pd--;
			}

			if (pd == pi)
				break;

			if (lista.get(pi).compareTo(lista.get(pd)) == 0) {
				pi++;
				pd--;
			}

			if (pi < pd) {
				cambiaNumeros(lista, pi, pd);
			}
		}

		lista.set(pi, pivote);
		return pi;
	}

	private void cambiaNumeros(List<T> lista, int i, int d) {
		T aux = lista.get(i);
		lista.set(i, lista.get(d));
		lista.set(d, aux);
	}

	/*
	 * public boolean comprobador(List<T> lista){ for (int i=0;
	 * i<lista.length-1; i++){ if (i == lista.length) break; if (lista[i] >
	 * lista[i+1]){ return false; } } return true; }
	 */

}
