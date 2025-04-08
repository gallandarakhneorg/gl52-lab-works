package fr.utbm.de52.graph.model;

/** Abstract implementation of a node in a graph.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the node.
 */
public abstract class AbstractGraphNode<N extends AbstractGraphNode<N, E, D>, E extends AbstractGraphEdge<N, E, D>, D>
		extends AbstractGraphElement<D> implements GraphNode<N, E, D> {

	/** Constructor.
	 *
	 * @param name the name of the element.
	 * @param graph the graph.
	 */
	public AbstractGraphNode(String name, AbstractGraph<?, ?, D> graph) {
		super(name, graph);
	}

	@Override
	public String toString() {
		final var text = new StringBuilder();
		var first = true;
		text.append(getName()).append(" {"); //$NON-NLS-1$
		for (final var edge : getStartingEdges()) {
			if (first) {
				first = false;
			} else {
				text.append(", "); //$NON-NLS-1$
			}
			text.append(edge.getName());
		}
		text.append("}"); //$NON-NLS-1$
		return text.toString();
	}

}
