package fr.utbm.de52.graph.model;

import java.util.Collection;
import java.util.Iterator;

import fr.utbm.de52.graph.factory.GraphFactory;
import fr.utbm.de52.graph.observer.GraphListener;

/** This interface represents a graph. Each node and edge may contains data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the graph.
 */
public interface Graph<N extends GraphNode<N, E, D>, E extends GraphEdge<N, E, D>, D> extends Iterable<N> {

	/** Replies the graph element factory that is used by this graph.
	 *
	 * @return the factory.
	 */
	GraphFactory<N, E> getFactory();

	/** Replies the unmodifiable collection of the graph nodes.
	 *
	 * @return the nodes.
	 */
	Collection<N> getNodes();

	/** Replies the unmodifiable collection of the graph edges.
	 *
	 * @return the edges.
	 */
	Collection<E> getEdges();

	/** Add a node into the graph.
	 *
	 * @param node the node to add.
	 */
	void addNode(N node);

	/** Add a node into the graph.
	 *
	 * @param name the name of the element.
	 * @return the created node.
	 */
	default N addNode(String name) {
		final var node = getFactory().createNode(name);
		addNode(node);
		return node;
	}

	/** Add an edge into the graph.
	 *
	 * @param name the name of the element.
	 * @param start the starting node for the edge.
	 * @param end the end node for the edge.
	 * @return the created edge.
	 */
	default E addEdge(String name, N start, N end) {
		final var edge = getFactory().createEdge(name);
		addEdge(edge, start, end);
		return edge;
	}

	/** Add an edge into the graph.
	 *
	 * @param edge the edge to add.
	 * @param start the starting node for the edge.
	 * @param end the end node for the edge.
	 */
	void addEdge(E edge, N start, N end);

	/** Replies an iterator on the data that are stored into the graph.
	 *
	 * @return the data iterator.
	 */
	Iterator<D> dataIterator();

	@Override
	Iterator<N> iterator();

	/** Add an observer on the events related to the graph.
	 *
	 * @param observer the observer to add.
	 */
	void addGraphListener(GraphListener observer);

	/** Add an observer on the events related to the graph.
	 *
	 * @param observer the observer to remove.
	 */
	void removeGraphListener(GraphListener observer);

}
