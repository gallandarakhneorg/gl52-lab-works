package fr.utbm.gl52.graph.model.arraylist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.utbm.gl52.graph.factory.GraphFactory;
import fr.utbm.gl52.graph.model.AbstractGraph;

/** Implementation of a graph based on an {@code ArrayList}.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the graph.
 */
public class ArrayListGraph<D> extends AbstractGraph<ArrayListGraphNode<D>, ArrayListGraphEdge<D>, D> {

	private final Factory factory = new Factory();

	private final Collection<ArrayListGraphNode<D>> nodes = new ArrayList<>();

	private final Collection<ArrayListGraphEdge<D>> edges = new ArrayList<>();

	@Override
	public GraphFactory<ArrayListGraphNode<D>, ArrayListGraphEdge<D>> getFactory() {
		return this.factory;
	}

	@Override
	public Collection<ArrayListGraphNode<D>> getNodes() {
		return Collections.unmodifiableCollection(this.nodes);
	}

	@Override
	public Collection<ArrayListGraphEdge<D>> getEdges() {
		return Collections.unmodifiableCollection(this.edges);
	}

	@Override
	public void addNode(ArrayListGraphNode<D> node) {
		assert node != null;
		this.nodes.add(node);
	}

	@Override
	public void addEdge(ArrayListGraphEdge<D> edge, ArrayListGraphNode<D> start, ArrayListGraphNode<D> end) {
		assert edge != null;
		assert start != null;
		assert end != null;
		start.connectStartingEdge(edge);
		end.connectArrivingEdge(edge);
		this.edges.add(edge);
	}

	/** Implementation of a graph based on an {@code ArrayList}.
	 * 
	 * @author sgalland
	 */
	private class Factory implements GraphFactory<ArrayListGraphNode<D>, ArrayListGraphEdge<D>> {

		@Override
		public ArrayListGraphNode<D> createNode(String name) {
			return new ArrayListGraphNode<>(name, ArrayListGraph.this);
		}

		@Override
		public ArrayListGraphEdge<D> createEdge(String name) {
			return new ArrayListGraphEdge<>(name, ArrayListGraph.this);
		}

	}

}
