package cr.ac.ucr.ecci.ci1221.FatPusheen.collection.set;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Iterable;

public interface Conjunto<T> extends Iterable<T> {

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
	Conjunto<T> union(Conjunto<T> A, Conjunto<T> B);

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
	Conjunto<T> intersection(Conjunto<T> A, Conjunto<T> B);

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
	Conjunto<T> difference(Conjunto<T> A, Conjunto<T> B);

	/**
	 * Retorna un boolean que dice si el elemento esta en el conjunto o no
	 * 
	 * @param dato
	 *            lo que se quiere buscar en el conjunto
	 * @return un boolean que dice si el elemento esta en el conjunto o no
	 */
	boolean contains(T dato);

	/**
	 * Limpia la lista a dejarla sin datos
	 */
	void clear();

	/**
	 * Añade un dato al conjunto asignado al this
	 * 
	 * @param dato
	 *            el elemento que se quiere agregar al conjunto
	 */
	void add(T dato);

	/**
	 * Elimina un dato al conjunto asignado al this
	 * 
	 * @param dato
	 *            el elemento que se quiere eliminar del conjunto
	 */
	void remove(T dato);

	/**
	 * Retorna un boolean que dice si el conjunto asignado al this es igual en
	 * todos sus elementos al conjunto A
	 * 
	 * @param A
	 *            el conjunto que se compara con el asignado en this
	 * @return boolean que dice si el conjunto asignado al this es igual en
	 *         todos sus elementos al conjunto A
	 */
	boolean Equals(Conjunto<T> A);

	/**
	 * Retorna un boolean si el conjunto asignado al this esta vacio
	 * 
	 * @return un boolean si el conjunto asignado al this esta vacio
	 */
	boolean isEmpty();
	
	/**
	 * Retorna el tamaño del conjunto
	 * 
	 * @return el tamaño del conjunto
	 */
	int size();

}
