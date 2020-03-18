package fr.utbm.gl52.graph.iterator;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import fr.utbm.gl52.graph.model.GraphNode;

/** Iterator on data in a data structure.
 * 
 * @author sgalland
 * @param <D> the type of the data to iterate on.
 */
public class DataIterator<D> implements Iterator<D> {

	private final Iterator<? extends GraphNode<?, ?, D>> nodes;

	private final Deque<D> candidates = new LinkedList<>();
	
	/** Constructor the iterator based on the nodes replied by the given iterator.
	 *
	 * @param nodes is the iterator on the nodes to go through.
	 */
	public DataIterator(Iterator<? extends GraphNode<?, ?, D>> nodes) {
		assert nodes != null;
		this.nodes = nodes;
		searchNextCandidates();
	}

	private void searchNextCandidates() {
		while (this.candidates.isEmpty() && this.nodes.hasNext()) {
			final GraphNode<?, ?, D> node = this.nodes.next();
			this.candidates.addAll(node.getData());
		}
	}

	@Override
	public boolean hasNext() {
		return !this.candidates.isEmpty();
	}

	@Override
	public D next() {
		final D data = this.candidates.remove();
		if (data == null) {
			throw new NoSuchElementException();
		}
		searchNextCandidates();
		return data;
	}

}
