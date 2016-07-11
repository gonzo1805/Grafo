package cr.ac.ucr.ecci.ci1221.FatPusheen.collection.set;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.LinkedList;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Iterator;

public class LinkedListSetImpl<T> implements Conjunto<T> {

	/**
	 * Atributo de la clase, la lista donde se almacenaran los datos del
	 * conjunto
	 */
	List<T> lista;

	public LinkedListSetImpl() {
		lista = new LinkedList<T>();
	}

	/**
	 * Une todos los datos no repetidos de los conjuntos A y B y lo retorna en
	 * uno tercero
	 * 
	 * @param A
	 *            conjunto a unir
	 * @param B
	 *            conjunto a unir
	 * @return el conjunto con la union de todos los datos no repetidos de los
	 *         conjuntos A y B
	 */
	@Override
	public Conjunto<T> union(Conjunto<T> A, Conjunto<T> B) {
		if (A.isEmpty() == true && B.isEmpty() == true) {// Comprobacion vacios
			ArrayIndexOutOfBoundsException f = new ArrayIndexOutOfBoundsException("Ambas listas estan vacias");
			throw f;
		} else if (A.isEmpty() == true) {
			return B;
		} else if (B.isEmpty() == true) {
			return A;
		}
		Conjunto<T> resultado = new LinkedListSetImpl<T>();// Conjunto nuevo
		Iterator<T> itA = A.iterator();// Iterador A
		Iterator<T> itB = B.iterator();// Iterador B
		while (itA.hasNext()) {// Iteramos por A
			resultado.add(itA.next());// Añadimos todo a resultado
		}
		while (itB.hasNext()) {// Iteramos por B
			T dato = itB.next();
			if (!A.contains(dato)) {// Lo que no esta en A
				resultado.add(dato);// Lo añade a resultado
			}
		}
		return resultado;// Retorna resultado
	}

	/**
	 * Interseca los datos de los conjuntos A y B y los retorna en un tercer
	 * conjunto, la interseccion son los datos que estan en el cconjunto A y al
	 * mismo tiempo en el B
	 * 
	 * @param A
	 *            conjunto a intersecar
	 * @param B
	 *            conjunto a intersecar
	 * @return el conjunto con la interseccion de los datos de los conjuntos A y
	 *         B
	 */
	@Override
	public Conjunto<T> intersection(Conjunto<T> A, Conjunto<T> B) {
		if (A.isEmpty() == true && B.isEmpty() == true) {// Comprobacion vacios
			ArrayIndexOutOfBoundsException f = new ArrayIndexOutOfBoundsException("Ambas listas estan vacias");
			throw f;
		} else if (A.isEmpty() == true) {
			return B;
		} else if (B.isEmpty() == true) {
			return A;
		}
		Conjunto<T> resultado = new LinkedListSetImpl<T>();// Nuevo conjunto
		Iterator<T> itA = A.iterator();// Iterador A

		T datoA;

		while (itA.hasNext()) {// Iteramos por A
			datoA = itA.next();
			if (B.contains(datoA)) {// Si esta en B
				resultado.add(datoA);// Se añade a resultado
			}
		}
		return resultado;// Return resultado
	}

	/**
	 * Hace una diferencia entre los datos del conjunto A a los del conjunto B y
	 * los retorna en uno tercero, la diferencia son todos los datos que estan
	 * en el conjunto A y que no estan en B
	 * 
	 * @param A
	 *            elemento base para la diferencia
	 * @param B
	 *            elemento que "resta" en la diferencia
	 * @return datos resultantes de la diferencia de A con B
	 */
	@Override
	public Conjunto<T> difference(Conjunto<T> A, Conjunto<T> B) {
		if (A.isEmpty() == true && B.isEmpty() == true) {// Comprobacion vacio
			ArrayIndexOutOfBoundsException f = new ArrayIndexOutOfBoundsException("Ambas listas estan vacias");
			throw f;
		} else if (A.isEmpty() == true) {
			return B;
		} else if (B.isEmpty() == true) {
			return A;
		}
		Conjunto<T> resultado = new LinkedListSetImpl<T>();// Conjunto nuevo

		Iterator<T> itA = A.iterator();// Iterador A
		Iterator<T> itB = B.iterator();// Iterador B

		while (itA.hasNext()) {// Iteramos A
			resultado.add(itA.next());// Lo añadimos todo a resultado
		}

		while (itB.hasNext()) {// Iteramos B
			T dato = itB.next();

			if (resultado.contains(dato)) {// Lo que esta en B que esta en
											// resultado
				resultado.remove(dato);// Lo quitamos de resultado
			}
		}

		return resultado;// Retorna resultado
	}

	/**
	 * Retorna un boolean si el conjunto asignado al this esta vacio
	 * 
	 * @return un boolean si el conjunto asignado al this esta vacio
	 */
	@Override
	public boolean isEmpty() {
		return this.lista.isEmpty();// Vacia?
	}

	/**
	 * Retorna un boolean que dice si el elemento esta en el conjunto o no
	 * 
	 * @param dato
	 *            lo que se quiere buscar en el conjunto
	 * @return un boolean que dice si el elemento esta en el conjunto o no
	 */
	@Override
	public boolean contains(T dato) {
		if (this.lista.size() == 0) {// Si lista vacia
			return false;
		}
		for (int i = 1; i <= this.lista.size(); i++) {// Va por toda la lista
			if (this.lista.get(i).equals(dato)) {// Si encuenta coincidencia
				return true;// Es true
			}
		}
		return false;// De lo contrario false
	}

	/**
	 * Limpia la lista a dejarla sin datos
	 */
	@Override
	public void clear() {
		this.lista.clear();// Limpia
	}

	/**
	 * Añade un dato al conjunto asignado al this
	 * 
	 * @param dato
	 *            el elemento que se quiere agregar al conjunto
	 */
	@Override
	public void add(T dato) {
		this.lista.add(dato);// Añade
	}

	/**
	 * Elimina un dato al conjunto asignado al this
	 * 
	 * @param dato
	 *            el elemento que se quiere eliminar del conjunto
	 */
	@Override
	public void remove(T dato) {
		if (this.contains(dato) == false) {// Si no esta en la lista
			ArrayIndexOutOfBoundsException f = new ArrayIndexOutOfBoundsException("El elemento no esta en la lista");
			throw f;
		} else {
			this.lista.remove(dato);// Elimina el dato
		}

	}

	/**
	 * Retorna un boolean que dice si el conjunto asignado al this es igual en
	 * todos sus elementos al conjunto A
	 * 
	 * @param A
	 *            el conjunto que se compara con el asignado en this
	 * @return boolean que dice si el conjunto asignado al this es igual en
	 *         todos sus elementos al conjunto A
	 */
	@Override
	public boolean Equals(Conjunto<T> A) {
		Iterator<T> itThis = this.iterator();// Iterador this
		Iterator<T> itA = this.iterator();// Iterador A

		while (itThis.hasNext()) {// Itera this
			if (!A.contains(itThis.next())) {// Si algo no coincide
				return false;// No son iguales
			}
		}
		while (itA.hasNext()) {// Itera A
			if (!this.contains(itA.next())) {// Si algo no coincide
				return false;// No son iguales
			}
		}
		return true;
	}

	/**
	 * Instanciador del iterador
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = this.lista.iterator();
		return it;
	}
	
	/**
	 * Retorna el tamaño del conjunto
	 * 
	 * @return el tamaño del conjunto
	 */
	@Override
	public int size(){
		return this.lista.size();
	}

}
