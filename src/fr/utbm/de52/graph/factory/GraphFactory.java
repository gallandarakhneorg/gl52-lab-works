package fr.utbm.de52.graph.factory;

import fr.utbm.de52.graph.model.GraphEdge;
import fr.utbm.de52.graph.model.GraphNode;

/** Factory of graph elements.
 * 
 * @author sgalland
 * @param <N> the type of the node to be created.
 * @param <E> the type of the edge to be created.
 */
public interface GraphFactory<N extends GraphNode<N, E, ?>, E extends GraphEdge<N, E, ?>> {

	/** Create a node.
	 *
	 * @param name the name of the node.
	 * @return the new node.
	 */
	N createNode(String name);

	/** Create an edge.
	 *
	 * @param name the name of the edge.
	 * @return the new edge.
	 */
	E createEdge(String name);

}
