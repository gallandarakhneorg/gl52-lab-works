package fr.utbm.gl52.graph.model;

import java.lang.ref.WeakReference;

/** Abstract implementation of an edge into a graph.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the graph.
 */
public abstract class AbstractGraphEdge<N extends AbstractGraphNode<N, E, D>, E extends AbstractGraphEdge<N, E, D>, D> 
		extends AbstractGraphElement<D> implements GraphEdge<N, E, D> {

	protected WeakReference<N> startNode;
	
	protected WeakReference<N> endNode;

	/** Constructor.
	 *
	 * @param name the name of the element.
	 * @param graph the graph.
	 */
	public AbstractGraphEdge(String name, AbstractGraph<?, ?, D> graph) {
		super(name, graph);
	}

	@Override
	public N getStartingNode() {
		return this.startNode == null ? null : this.startNode.get();
	}
	
	@Override
	public N getEndingNode() {
		return this.endNode == null ? null : this.endNode.get();
	}

	@Override
	public String toString() {
		final StringBuilder text = new StringBuilder();
		final N a = getStartingNode();
		final N b = getEndingNode();
		text.append(a.getName());
		text.append(" -> "); //$NON-NLS-1$
		text.append(b.getName());
		return text.toString();
	}

}
