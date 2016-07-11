package cr.ac.ucr.ecci.ci1221.FatPusheen.graph;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cr.ac.ucr.ecci.ci1221.FatPusheen.collection.set.Conjunto;
import cr.ac.ucr.ecci.ci1221.FatPusheen.collection.set.LinkedListSetImpl;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Iterator;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.LinkedList;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class graphI<T> {

	Map<T, Node<T>> matriz;

	public graphI() {
		matriz = new HashMap<T, Node<T>>();
	}

	public void addVertex(T dato) {
		if (contains(dato)) {
			System.out.println("El elemento ya esta en el grafo, no se insertara");
		} else {
			matriz.put(dato, new Node<T>(dato));
		}
	}

	public void addEdge(T comienzo, T destino, int peso) {
		addEdge(matriz.get(comienzo), matriz.get(destino), peso);
		addEdge(matriz.get(destino), matriz.get(comienzo), peso);
	}

	public void addEdge(Node<T> comienzo, Node<T> destino, int peso) {
		comienzo.arista.add(new Arista<T>(comienzo, destino, peso));
	}

	public void changeValueEdge(Node<T> comienzo, Node<T> destino, int peso) {

		List<Arista<T>> aristas = comienzo.getAristas();
		Iterator<Arista<T>> itA = aristas.iterator();

		while (itA.hasNext()) {
			Arista<T> arista = itA.next();
			if (arista.nodo2.equals(destino)) {
				arista.setPeso(peso);
			}
		}
	}

	public boolean contains(T dato) {
		return matriz.containsKey(dato);
	}

	public Node<T> getNodo(T dato) {
		return matriz.get(dato);
	}

	public List<Arista<T>> getAdjacency(T dato) {
		return matriz.get(dato).arista;
	}

	public int getNumberAdjacency(T dato) {
		return getAdjacency(dato).size();
	}

	public int graphSize() {
		return matriz.size();
	}

	public LinkedList<T> breadthSearch(T dato) {
		LinkedList<T> visitados = new LinkedList<T>();
		ArrayDeque<T> cola = new ArrayDeque<T>();
		return breadthSearch(visitados, cola, dato, 1);
	}

	private LinkedList<T> breadthSearch(LinkedList<T> visitados, ArrayDeque<T> cola, T dato, int cantidad) {
		if (cola.size() == 0 && cantidad != 1) {
			return visitados;
		} else {
			if (cantidad == 1) {
				visitados.add(dato, cantidad);
				cantidad++;
				cola.offer(dato);
				return breadthSearch(visitados, cola, dato, cantidad);
			} else {
				T momento = cola.poll();
				if (momento != null) {
					Iterator<Arista<T>> itM = getAdjacency(momento).iterator();
					while (itM.hasNext()) {
						T insercion = itM.next().nodo2.vertice;
						if (!visitados.contains(insercion)) {
							cola.offer(insercion);
							visitados.add(insercion, cantidad);
							cantidad++;
							visitados = breadthSearch(visitados, cola, momento, cantidad);
						}
					}
				}
				return breadthSearch(visitados, cola, momento, cantidad);
			}
		}
	}

	public LinkedList<T> deepSearch(T dato) {
		LinkedList<T> visitados = new LinkedList<T>();
		return deepSearch(visitados, dato);
	}

	private LinkedList<T> deepSearch(LinkedList<T> visitados, T dato) {
		visitados.add(dato);
		if (faltaVisitar(visitados, matriz.get(dato)) == null) {
			return visitados;
		} else {
			while (faltaVisitar(visitados, matriz.get(dato)) != null) {
				Node<T> nodo = faltaVisitar(visitados, matriz.get(dato));
				visitados = deepSearch(visitados, nodo.vertice);
			}
		}
		return visitados;
	}

	public LinkedList<LinkedList<T>> getComponents() {
		LinkedList<LinkedList<T>> lista = new LinkedList<LinkedList<T>>();
		Set<T> set = matriz.keySet();
		T[] arraySet = (T[]) set.toArray();

		for (int i = 0; i < arraySet.length; i++) {
			lista.add(deepSearch(arraySet[i]));
		}
		LinkedList<LinkedList<T>> listaRet = new LinkedList<LinkedList<T>>();
		Iterator<LinkedList<T>> it = lista.iterator();
		while (it.hasNext()) {
			LinkedList<T> datos1 = it.next();
			noLikeComponents(datos1, listaRet);

		}
		return listaRet;
	}

	public int numberOfComponents() {
		LinkedList<LinkedList<T>> lista = getComponents();
		LinkedList<LinkedList<T>> listaRet = new LinkedList<LinkedList<T>>();
		Iterator<LinkedList<T>> it = lista.iterator();
		while (it.hasNext()) {
			LinkedList<T> datos1 = it.next();
			noLikeComponents(datos1, listaRet);

		}
		return listaRet.size();
	}

	private void noLikeComponents(LinkedList<T> lista, LinkedList<LinkedList<T>> listaRet) {
		if (listaRet.isEmpty()) {
			listaRet.add(lista);
			return;
		} else {
			Iterator<LinkedList<T>> it = listaRet.iterator();
			while (it.hasNext()) {
				if (it.next().contains(lista.get(1))) {
					return;
				}
			}
		}
		listaRet.add(lista);
	}

	public boolean sameComponent(T dato, T dato2) {
		LinkedList<LinkedList<T>> lista = getComponents();
		boolean retorno = false;
		Iterator<LinkedList<T>> it = lista.iterator();
		while (it.hasNext()) {
			retorno = sameComponent(dato, dato2, it.next());
			if (retorno) {
				return retorno;
			}
		}
		return retorno;
	}

	private boolean sameComponent(T dato, T dato2, LinkedList<T> lista) {
		if (lista.contains(dato) && lista.contains(dato2)) {
			return true;
		} else {
			return false;
		}
	}

	private Node<T> faltaVisitar(List<T> visitados, Node<T> dato) {
		List<Arista<T>> lista = dato.arista;
		for (int i = 1; i <= lista.size(); i++) {
			if (!visitados.contains(lista.get(i).nodo2.vertice)) {
				return lista.get(i).nodo2;
			}
		}
		return null;
	}

	public List<T> minimalSpanningTreeKruskal() {
		Collection<Node<T>> nodos = matriz.values();
		List<Arista<T>> todasAristas = new LinkedList<Arista<T>>();
		java.util.Iterator<graphI<T>.Node<T>> it = nodos.iterator();
		while (it.hasNext()) {
			List<Arista<T>> a = it.next().arista;
			Iterator<Arista<T>> itA = a.iterator();
			while (itA.hasNext()) {
				todasAristas.add(itA.next());
			}
		}
		selectionSortArista(todasAristas);// De preferencia hacer las entradas
											// de manera ordenada si hay tiempo
		int i = todasAristas.size();
		while (i != 0) {
			todasAristas.remove(i);
			i = i - 2;
		}

		Map<T, T> conexos = new HashMap<T, T>();
		Set<T> se = matriz.keySet();
		java.util.Iterator<T> iterador = se.iterator();

		while (iterador.hasNext()) {
			T dat = iterador.next();
			conexos.put(dat, dat);
		}
		List<T> fin = new LinkedList<T>();
		for (int f = 1; f <= todasAristas.size(); f++) {
			Node<T> nodo1 = todasAristas.get(f).nodo1;
			Node<T> nodo2 = todasAristas.get(f).nodo2;

			if (find(nodo1.vertice, conexos) != find(nodo2.vertice, conexos)) {
				union(nodo1.vertice, nodo2.vertice, conexos);
				fin.add(nodo1.vertice);
				fin.add(nodo2.vertice);
			}
		}
		return eliminaRepetidos(fin);
	}

	private List<T> eliminaRepetidos(List<T> fin) {
		List<T> retorno = new LinkedList<T>();
		Iterator<T> itFin = fin.iterator();
		while (itFin.hasNext()) {
			T dato = itFin.next();
			if (!retorno.contains(dato)) {
				retorno.add(dato);
			}
		}
		return retorno;
	}

	private T find(T dato, Map<T, T> conexos) {
		if (conexos.get(dato).equals(dato)) {
			return dato;
		} else
			return find(conexos.get(dato), conexos);
	}

	private void union(T dato, T dato2, Map<T, T> conexos) {
		dato = find(dato, conexos);
		dato2 = find(dato2, conexos);
		conexos.replace(dato, dato2);
	}

	private void selectionSortArista(List<Arista<T>> lista) {
		for (int i = 1; i <= lista.size(); i++) {
			int minimo = i;

			for (int x = i + 1; x <= lista.size(); x++) {
				if (lista.get(x).peso < (lista.get(minimo).peso)) {
					minimo = x;
				}
			}
			if (i != minimo) {
				swap(lista, i, minimo);
			}
		}
	}

	public List<T> minimalSpanningTreePrim(T dato) {

		Map<T, T> conexos = new HashMap<T, T>();
		List<Arista<T>> aristas = new LinkedList<Arista<T>>();

		Set<T> se = matriz.keySet();
		java.util.Iterator<T> iterador = se.iterator();
		while (iterador.hasNext()) {
			T dat = iterador.next();
			conexos.put(dat, dat);
		}

		List<Arista<T>> inicial = matriz.get(dato).arista;
		List<T> fin = new LinkedList<T>();

		Iterator<Arista<T>> itIn = inicial.iterator();
		while (itIn.hasNext()) {
			aristas.add(itIn.next());
		}
		selectionSortArista(aristas);

		while (!aristas.isEmpty()) {
			Node<T> nodo1 = aristas.get(1).nodo1;
			Node<T> nodo2 = aristas.get(1).nodo2;
			aristas.remove(1);

			if (find(nodo1.vertice, conexos) != find(nodo2.vertice, conexos)) {

				union(nodo1.vertice, nodo2.vertice, conexos);
				fin.add(nodo1.vertice);
				fin.add(nodo2.vertice);

				List<Arista<T>> siguiente = nodo2.arista;
				Iterator<Arista<T>> itSi = siguiente.iterator();
				while (itSi.hasNext()) {
					aristas.add(itSi.next());
				}
				selectionSortArista(aristas);// De preferencia hacer las
												// entradas de manera ordenada
												// si hay tiempo
			}
		}
		return eliminaRepetidos(fin);
	}

	public LinkedList<T>[] graphColors() {
		Set<T> set = matriz.keySet();
		Map<T, NodoColores<T>> adyac = new HashMap<T, NodoColores<T>>();
		java.util.Iterator<T> itSet = set.iterator();
		List<NodoColores<T>> fin = new LinkedList<NodoColores<T>>();
		while (itSet.hasNext()) {
			T dato = itSet.next();
			List<Arista<T>> cambiante = matriz.get(dato).arista;

			Iterator<Arista<T>> itCambiante = cambiante.iterator();
			LinkedList<T> adyacencias = new LinkedList<T>();

			while (itCambiante.hasNext()) {
				Arista<T> arista = itCambiante.next();
				adyacencias.add(arista.nodo2.vertice);
			}
			adyac.put(dato, new NodoColores<T>(0, adyacencias, dato));
		}

		Conjunto<Integer> colores = new LinkedListSetImpl<Integer>();
		itSet = set.iterator();

		while (itSet.hasNext()) {
			T data = itSet.next();
			if (colores.size() == 0) {
				colores.add(1);
				adyac.get(data).color = 1;
			} else {
				asignaColor(colores, adyac.get(data), data, adyac);
			}
		}
		LinkedList<T>[] entrega = (LinkedList<T>[]) new LinkedList[colores.size() + 1];
		itSet = set.iterator();

		while (itSet.hasNext()) {
			T dato = itSet.next();
			if (entrega[adyac.get(dato).color] == null) {
				entrega[adyac.get(dato).color] = new LinkedList<T>();
			}
			entrega[adyac.get(dato).color].add(dato);
		}
		return entrega;
	}

	private void asignaColor(Conjunto<Integer> color, NodoColores<T> nodo, T dato, Map<T, NodoColores<T>> adyac) {
		Conjunto<Integer> conjunto = new LinkedListSetImpl<Integer>();
		Iterator<T> itNodo = nodo.adyacentes.iterator();

		while (itNodo.hasNext()) {
			T actual = itNodo.next();
			conjunto.add(adyac.get(actual).color);
		}

		conjunto = color.difference(color, conjunto);
		if (conjunto.size() != 0) {
			if (conjunto.contains(0)) {
				conjunto.remove(0);
			}
		}
		Iterator<Integer> c = conjunto.iterator();
		Integer colorFinal = 0;
		if (c.hasNext()) {
			colorFinal = c.next();
		}

		if (colorFinal == 0) {
			adyac.get(dato).color = color.size() + 1;
			color.add(color.size() + 1);
		} else {
			adyac.get(dato).color = colorFinal;
			if (!color.contains(colorFinal)) {
				color.add(colorFinal);
			}
		}
	}

	private T getData(int posicion) {
		Set<T> set = matriz.keySet();
		java.util.Iterator<T> it = set.iterator();
		int f = 1;
		while (it.hasNext()) {
			T data = it.next();
			if (f == posicion) {
				return data;
			}
			f++;
		}
		return null;
	}

	private int getWeight(int datoI, int datoF) {
		Set<T> datos = matriz.keySet();

		T dato1 = getData(datoI);
		T dato2 = getData(datoF);

		Iterator<Arista<T>> arista = matriz.get(dato1).arista.iterator();
		while (arista.hasNext()) {
			Arista<T> dato = arista.next();
			if (dato.nodo2.vertice.equals(dato2)) {
				return dato.peso;
			}
		}
		return Integer.MAX_VALUE;
	}

	private int position(T dato) {
		Set<T> set = matriz.keySet();
		java.util.Iterator<T> it = set.iterator();
		int f = 1;
		while (it.hasNext()) {
			T data = it.next();
			if (data.equals(dato)) {
				return f;
			}
			f++;
		}
		return f;
	}

	private int[] neighbors(int next) {
		Set<T> datos = matriz.keySet();
		java.util.Iterator<T> iterador = datos.iterator();
		int f = 1;

		while (iterador.hasNext()) {
			if (f != next) {
				f++;
				iterador.next();
			} else {
				T dato = iterador.next();
				int[] n = new int[matriz.get(dato).arista.size()];
				Iterator<Arista<T>> it = matriz.get(dato).arista.iterator();
				int x = 0;
				while (it.hasNext()) {
					n[x] = position(it.next().nodo2.vertice);
					x++;
				}
				return n;
			}
		}
		return null;
	}

	public List<T> dijstra(T dato) {
		int s = position(dato);
		int[] f = dijsktra(s);
		List<T> fin = new LinkedList<T>();
		for (int i = 0; i < f.length; i++) {
			if (f[i] == -1) {
				fin.add(dato);
			} else if (f[i] == 0) {
				fin.add(null);
			} else {
				fin.add(getData(f[i]));
			}
		}
		return fin;
	}

	public int[] dijsktra(int s) {

		Set<T> datos = matriz.keySet();
		java.util.Iterator<T> iterador = datos.iterator();

		int[] distancia = new int[matriz.size() + 1];
		int[] predecesor = new int[matriz.size() + 1];
		boolean[] visitados = new boolean[matriz.size() + 1];// Ya estan en
																// false

		for (int i = 0; i < distancia.length; i++) {
			distancia[i] = Integer.MAX_VALUE;
		}
		distancia[s] = 0;// De donde comenzamos

		for (int i = 0; i < distancia.length; i++) {
			int next = minVertex(distancia, visitados);
			if (next != -1) {
				visitados[next] = true;

				int[] n = neighbors(next);
				for (int j = 0; j < n.length; j++) {
					int v = n[j];
					int d = distancia[next] + getWeight(next, v);
					if (distancia[v] > d) {
						distancia[v] = d;
						predecesor[v] = next;
					}
				}
			}
		}
		int[] f = new int[predecesor.length - 1];
		for (int i = 1; i < predecesor.length; i++) {
			f[i - 1] = predecesor[i];
		}
		f[s-1] = -1;
		return f;
	}

	private int minVertex(int[] distancia, boolean[] v) {
		int x = Integer.MAX_VALUE;
		int y = -1;
		for (int i = 0; i < distancia.length; i++) {
			if (!v[i] && distancia[i] < x) {
				y = i;
				x = distancia[i];
			}
		}
		return y;
	}

	private void swap(List<Arista<T>> lista, int i, int minimo) {
		Arista<T> aux = lista.get(i);
		lista.set(i, lista.get(minimo));
		lista.set(minimo, aux);
	}

	private class NodoColores<T> {
		private T vertice;
		private int color;
		private LinkedList<T> adyacentes;

		public NodoColores(int color, LinkedList<T> adyacentes, T vertice) {
			this.color = color;
			this.adyacentes = adyacentes;
			this.vertice = vertice;
		}

		public T getVertice() {
			return this.vertice;
		}

		public void setVertice(T vertice) {
			this.vertice = vertice;
		}

		public List<T> getAdyacentes() {
			return this.adyacentes;
		}

		public void setPadre(LinkedList<T> adyacentes) {
			this.adyacentes = adyacentes;
		}

		public int getColor() {
			return this.color;
		}

		public void setColor(int color) {
			this.color = color;
		}
	}

	private class Node<T> {
		private T vertice;
		private List<Arista<T>> arista;

		public Node(T dato) {
			this.arista = new LinkedList<Arista<T>>();
			vertice = dato;
		}

		public T getVertice() {
			return this.vertice;
		}

		public void setVertice(T dato) {
			this.vertice = dato;
		}

		public List<Arista<T>> getAristas() {
			return this.arista;
		}

		public void setAristas(List<Arista<T>> aristas) {
			this.arista = aristas;
		}
	}

	private class Arista<T> {
		Node<T> nodo1;
		Node<T> nodo2;
		int peso;

		public Arista(Node<T> nodo1, Node<T> nodo2, int peso) {
			this.nodo1 = nodo1;
			this.nodo2 = nodo2;
			this.peso = peso;
		}

		public Node<T> getNodo1() {
			return this.nodo1;
		}

		public Node<T> getNodo2() {
			return this.nodo2;
		}

		public int getPeso() {
			return this.peso;
		}

		public void setNodo1(Node<T> nodo1) {
			this.nodo1 = nodo1;
		}

		public void setNodo2(Node<T> nodo2) {
			this.nodo2 = nodo2;
		}

		public void setPeso(int peso) {
			this.peso = peso;
		}
	}

}
