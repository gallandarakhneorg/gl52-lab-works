package fr.utbm.de52.tp3;

import java.util.Random;

import fr.utbm.de52.graph.model.arraylist.ArrayListGraph;
import fr.utbm.de52.graph.model.arraylist.ArrayListGraphEdge;
import fr.utbm.de52.graph.model.arraylist.ArrayListGraphNode;
import fr.utbm.de52.graph.observer.GraphDataEvent;
import fr.utbm.de52.graph.observer.GraphListener;

/** Main program for TP3 of GL52.
 * 
 * @author sgalland
 */
public class Main {

	/** Main program.
	 *
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		final var random = new Random();
	
		final var graph = new ArrayListGraph<Integer>();

		final var observer = new GraphListener() {

			@Override
			public void dataAdded(GraphDataEvent event) {
				System.out.println("DATA-ADDED(" + event.getSource() + "): " + event.getData()); //$NON-NLS-1$ //$NON-NLS-2$
			}

			@Override
			public void dataRemoved(GraphDataEvent event) {
				System.out.println("DATA-REMOVED(" + event.getSource() + "): " + event.getData()); //$NON-NLS-1$ //$NON-NLS-2$
			}

		};
		graph.addGraphListener(observer);
		
		final var n0 = createNode("n0", graph, random); //$NON-NLS-1$
		final var n1 = createNode("n1", graph, random); //$NON-NLS-1$
		final var n2 = createNode("n2", graph, random); //$NON-NLS-1$
		final var n3 = createNode("n3", graph, random); //$NON-NLS-1$
		final var n4 = createNode("n4", graph, random); //$NON-NLS-1$
		final var n5 = createNode("n5", graph, random); //$NON-NLS-1$

		createEdge("e1", n0, n1, graph, random); //$NON-NLS-1$
		createEdge("e2", n0, n2, graph, random); //$NON-NLS-1$
		createEdge("e3", n1, n3, graph, random); //$NON-NLS-1$
		createEdge("e4", n2, n3, graph, random); //$NON-NLS-1$
		createEdge("e5", n2, n4, graph, random); //$NON-NLS-1$
		createEdge("e6", n2, n0, graph, random); //$NON-NLS-1$
		createEdge("e7", n3, n5, graph, random); //$NON-NLS-1$
		createEdge("e8", n3, n3, graph, random); //$NON-NLS-1$
		createEdge("e9", n4, n5, graph, random); //$NON-NLS-1$
		createEdge("e10", n4, n1, graph, random); //$NON-NLS-1$
		createEdge("e11", n5, n0, graph, random); //$NON-NLS-1$

		System.out.println(graph.toString());
	}

	private static ArrayListGraphNode<Integer> createNode(String name,
			ArrayListGraph<Integer> graph, Random random) {
		final var node = graph.addNode(name);
		final var nb = random.nextInt(20) + 5;
		for (var i = 0; i < nb; ++i) {
			node.addData(Integer.valueOf(random.nextInt()));
		}
		return node;
	}

	private static ArrayListGraphEdge<Integer> createEdge(String name,
			ArrayListGraphNode<Integer> a, ArrayListGraphNode<Integer> b, ArrayListGraph<Integer> graph, Random random) {
		final var edge = graph.addEdge(name, a, b);
		final var nb = random.nextInt(20) + 5;
		for (var i = 0; i < nb; ++i) {
			edge.addData(Integer.valueOf(random.nextInt()));
		}
		return edge;
	}

}
