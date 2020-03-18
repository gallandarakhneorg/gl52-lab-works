package fr.utbm.gl52.graph.model.arraylist;

import java.lang.ref.WeakReference;

import fr.utbm.gl52.graph.model.AbstractGraphEdge;

/** Implementation of an edge into a graph that is based on an {@code ArrayList}.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the graph.
 */
public class ArrayListGraphEdge<D> extends AbstractGraphEdge<ArrayListGraphNode<D>, ArrayListGraphEdge<D>, D> {

	/** Constructor.
	 *
	 * @param name the name of the element.
	 * @param graph the graph.
	 */
	public ArrayListGraphEdge(String name, ArrayListGraph<D> graph) {
		super(name, graph);
	}

	/** Change the starting node.
	 *
	 * @param node the node or {@code null}.
	 */
	void setStartingNode(ArrayListGraphNode<D> node) {
		if (node == null) {
			this.startNode = null;
		} else {
			this.startNode = new WeakReference<>(node);
		}
	}
	
	/** Change the ending node.
	 *
	 * @param node the node or {@code null}.
	 */
	void setEndingNode(ArrayListGraphNode<D> node) {
		if (node == null) {
			this.endNode = null;
		} else {
			this.endNode = new WeakReference<>(node);
		}
	}

}
