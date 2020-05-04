package fr.utbm.gl52.graph.model.arraylist;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.utbm.gl52.graph.model.AbstractGraphNode;
import fr.utbm.gl52.util.CollectionUtils;

/** Implementation of a node in a graph that is based on an {@code ArrayList}.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the node.
 */
public class ArrayListGraphNode<D> extends AbstractGraphNode<ArrayListGraphNode<D>, ArrayListGraphEdge<D>, D> {

	private final Collection<WeakReference<ArrayListGraphEdge<D>>> startEdges = new ArrayList<>();
	
	private final Collection<WeakReference<ArrayListGraphEdge<D>>> endEdges = new ArrayList<>();

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
		return Collections.unmodifiableCollection(
				CollectionUtils.transform(this.startEdges,
					(currentElement) -> currentElement.get()));
	}

	@Override
	public Collection<ArrayListGraphEdge<D>> getArrivingEdges() {
		return Collections.unmodifiableCollection(
				CollectionUtils.transform(this.endEdges,
					(currentElement) -> currentElement.get()));
	}

	@Override
	public void connectStartingEdge(ArrayListGraphEdge<D> edge) {
		edge.setStartingNode(this);
		this.startEdges.add(new WeakReference<>(edge));
	}

	@Override
	public void connectArrivingEdge(ArrayListGraphEdge<D> edge) {
		edge.setEndingNode(this);
		this.endEdges.add(new WeakReference<>(edge));
	}

}
