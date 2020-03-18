package fr.utbm.gl52.graph.model;

/** This interface represents an edge into a graph.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the graph.
 */
public interface GraphEdge<N extends GraphNode<N, E, D>, E extends GraphEdge<N, E, D>, D> extends GraphElement<D> {

	/** Replies this edge casted as E.
	 *
	 * @return this edge.
	 */
	@SuppressWarnings("unchecked")
	default E asE() {
		return (E) this;
	}

	/** Replies the starting node.
	 *
	 * @return the starting node.
	 */
	N getStartingNode();
	
	/** Replies the ending node.
	 *
	 * @return the ending node.
	 */
	N getEndingNode();

}
