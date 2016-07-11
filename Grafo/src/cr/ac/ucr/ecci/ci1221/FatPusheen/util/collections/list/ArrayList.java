package cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Iterator;

/**
 * Implementation of a List model using arrays.
 *
 * @param <E>
 *            the type of the elements that the list holds.
 */
public class ArrayList<E> implements List<E> {

	private int cantidadDatos;
	private E[] lista;

	public ArrayList() {
		lista = (E[]) new Object[50];
		cantidadDatos = 0;
	}

	public ArrayList(int tamano) {
		lista = (E[]) new Object[tamano];
		cantidadDatos = 0;
	}

	/**
	 * Adds the given element to the given position.
	 *
	 * @param element
	 *            the element.
	 * @param position
	 *            the position.
	 */
	@Override
	public void add(E element, int position) {
		position = position - 1;
		if (position == cantidadDatos){
			add(element);
			return;
		}
		if (cantidadDatos == 0){
			lista[0] = element;
			cantidadDatos++;
			return;
		}
		if (position > cantidadDatos) {
			position = cantidadDatos + 1;
		}
		if (cantidadDatos + 1 >= lista.length) {

			this.aumentaTamaño();
			E aux = lista[position - 1];
			for (int i = position - 1; i < cantidadDatos; i++) {
				lista[i + 1] = aux;
				aux = lista[i + 2];
			}
			lista[position - 1] = element;
			cantidadDatos++;
			lista[cantidadDatos] = aux;

		} else if (cantidadDatos + 1 < lista.length) {
			E aux = lista[cantidadDatos-1];
			for (int i = cantidadDatos; i > position; i--) {
				lista[i] = lista[i-1];
			}
			lista[position] = element;
			cantidadDatos++;
			

		}

	}

	public void aumentaTamaño() {
		E[] lista2 = (E[]) new Object[lista.length * 2];
		for (int i = 0; i < cantidadDatos; i++) {
			if (i < cantidadDatos) {
				lista2[i] = lista[i];
			}
			lista = lista2;
		}
	}

	/**
	 * removes the element at the given position.
	 *
	 * @param position
	 *            the position.
	 */
	@Override
	public void remove(int position) {
		position = position - 1;
		if (position == 0) {
			for (int i = 0; i < cantidadDatos; i++) {
				lista[i] = lista[i + 1];
			}
		} else {
			for (int i = position - 1; i < cantidadDatos; i++) {
				lista[i] = lista[i + 1];
			}
		}
		cantidadDatos--;
	}

	/**
	 * Returns the initial position of the given element.
	 *
	 * @param element
	 *            the element to find.
	 * @return the position of the found element.
	 */
	@Override
	public int find(E element) {
		for (int i = 1; i <= cantidadDatos; i++) {
			if (lista[i-1] == element) {
				return i;
			}
		}
		System.out.println("No se encontro en la lista el elemento dado");
		return -1;
	}

	/**
	 * Returns the element at the given position.
	 *
	 * @param position
	 *            the position.
	 * @return the element at the given position.
	 */
	@Override
	public E get(int position) {
		position = position - 1;
		return lista[position];
	}

	/**
	 * Replaces the element in the given position with the given element.
	 *
	 * @param position
	 *            the position
	 * @param element
	 *            the element to replace.
	 * @return the replaced element.
	 */
	@Override
	public E set(int position, E element) {
		position = position - 1;
		if (position > cantidadDatos) {
			System.out.println("Se intenta sobreescribir en una posicion que la lista no posee, se retornara null");
			return null;
		}
		E auxiliar = lista[position];
		lista[position] = element;
		return auxiliar;
	}

	/**
	 * Adds an element to the collection.
	 *
	 * @param element
	 *            the element to add to the collection.
	 */
	@Override
	public void add(E element) {
		if (lista.length < cantidadDatos + 1){
			aumentaTamaño();
		}
		lista[cantidadDatos] = element;
		cantidadDatos++;
	}

	/**
	 * Removes an element to the collection.
	 *
	 * @param element
	 *            the element to remove to the collection.
	 */
	@Override
	public E remove(E element) {
		int i = find(element);
		E elemento = get(i);
		remove(i);
		return elemento;
	}

	/**
	 * Checks if the collection contains the given Element.
	 *
	 * @param element
	 *            the element to look for.
	 * @return true if the element is in the collection, false otherwise.
	 */
	@Override
	public boolean contains(E element) {
		return (find(element) != -1);
	}

	/**
	 * Returns the size of the collection.
	 *
	 * @return the size of the collection.
	 */
	@Override
	public int size() {
		return cantidadDatos;
	}

	/**
	 * Whether the collection is empty or not.
	 *
	 * @return Whether the collection is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return (cantidadDatos == 0);
	}

	/**
	 * Removes all the elements from the collection.
	 */
	@Override
	public void clear() {
		lista = (E[]) new Object[100];
		cantidadDatos = 0;
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new It<E>();
	}

	/**
	 * Sorts a collection.
	 */
	@Override
	public void sort() {

	}

	private class It<E> implements Iterator<E> {
		int actual = 0;

		public It() {
		}

		@Override
		public boolean hasNext() {
			return actual < cantidadDatos;
		}

		@Override
		public E next() {
			if (actual >= cantidadDatos) {
				System.out.println("Ya esta en el final de la lista");
				return null;
			} else {
				E retorno = (E) lista[actual];
				actual++;
				return retorno;
			}

		}
	}
}
