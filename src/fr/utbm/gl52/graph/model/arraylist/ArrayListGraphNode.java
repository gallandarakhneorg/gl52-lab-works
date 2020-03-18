package fr.utbm.gl52.graph.model.arraylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.utbm.gl52.graph.model.AbstractGraphNode;

/** Implementation of a node in a graph that is based on an {@code ArrayList}.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the node.
 */
public class ArrayListGraphNode<D> extends AbstractGraphNode<ArrayListGraphNode<D>, ArrayListGraphEdge<D>, D> {

	private final Collection<ArrayListGraphEdge<D>> startEdges = new ArrayList<>();
	
	private final Collection<ArrayListGraphEdge<D>> endEdges = new ArrayList<>();

	/** Constructor.
	 *
	 * @param name the name of the element.
	 * @param graph the graph.
	 */
	public ArrayListGraphNode(String name, ArrayListGraph<D> graph) {
		super(name, graph);
	}

	@Override
	public Collection<ArrayListGraphEdge<D>> getStartingEdges() {
		return Collections.unmodifiableCollection(this.startEdges);
	}

	@Override
	public Collection<ArrayListGraphEdge<D>> getArrivingEdges() {
		return Collections.unmodifiableCollection(this.endEdges);
	}

	@Override
	public void connectStartingEdge(ArrayListGraphEdge<D> edge) {
		edge.setStartingNode(this);
		this.startEdges.add(edge);
	}

	@Override
	public void connectArrivingEdge(ArrayListGraphEdge<D> edge) {
		edge.setEndingNode(this);
		this.endEdges.add(edge);
	}

}
