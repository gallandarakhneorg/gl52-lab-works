package fr.utbm.gl52.graph.model;

import java.util.Collection;

/** This interface represents a node in a graph.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the node.
 */
public interface GraphNode<N extends GraphNode<N, E, D>, E extends GraphEdge<N, E, D>, D> extends GraphElement<D> {

	/** Replies this node casted as N.
	 *
	 * @return this node.
	 */
	@SuppressWarnings("unchecked")
	default N asN() {
		return (N) this;
	}

	/** Replies the edges that are starting from this node.
	 *
	 * @return the unmodifiable collection of edges.
	 */
	Collection<E> getStartingEdges();

	/** Replies the edges that are arriving to this node.
	 *
	 * @return the unmodifiable collection of edges.
	 */
	Collection<E> getArrivingEdges();

	/** Connect the starting point of the given edge to this node.
	 *
	 * @param edge the edge to connect.
	 */
	void connectStartingEdge(E edge);

	/** Connect the arriving point of the given edge to this node.
	 *
	 * @param edge the edge to connect.
	 */
	void connectArrivingEdge(E edge);

}
