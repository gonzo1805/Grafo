package cr.ac.ucr.ecci.ci1221.FatPusheen.graph;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Iterator;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.LinkedList;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list.List;

public class Main {

	public static void main(String args[]) {

		graphI<Integer> grafo = new graphI<Integer>();
		grafo.addVertex(1);
		grafo.addVertex(5);
		grafo.addVertex(4);
		grafo.addVertex(2);
		grafo.addVertex(3);
		grafo.addVertex(6);
		// grafo.addEdge(1, 2, 1);
		grafo.addEdge(5, 6, 5);
		grafo.addEdge(1, 3, 5);
		grafo.addEdge(1, 4, 1);
		// grafo.addEdge(2, 5, 1);
		grafo.addEdge(2, 3, 4);
		grafo.addEdge(3, 4, 2);
		// grafo.addEdge(4, 5, 1);
		List<LinkedList<Integer>> x = grafo.getComponents();
		Iterator<LinkedList<Integer>> it1 = x.iterator();

		while (it1.hasNext()) {
			Iterator<Integer> it2 = it1.next().iterator();
			System.out.println("| Componente nuevo |");
			while (it2.hasNext()) {
				System.out.print(it2.next() + " ");
			}
		}

		System.out.println();
		//imprimeColores(grafo.graphColors());

		List<Integer> v = grafo.dijstra(1);
		Iterator<Integer> it = v.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		int[] f = grafo.dijsktra(1);
		for (int i = 0; i < f.length; i++) {
			System.out.print(f[i] + " ");
		}

	}

	public static void imprimeColores(LinkedList<Integer>[] con) {
		for (int i = 1; i < con.length; i++) {
			Iterator<Integer> it = con[i].iterator();
			System.out.print(i + "{");
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}
			System.out.println("}");
		}
	}
}
