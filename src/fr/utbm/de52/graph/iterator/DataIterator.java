package fr.utbm.de52.graph.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.utbm.de52.graph.model.GraphNode;

/** Iterator on data in a data structure.
 * 
 * @author sgalland
 * @param <D> the type of the data to iterate on.
 */
public class DataIterator<D> implements Iterator<D> {

	private final Iterator<? extends GraphNode<?, ?, D>> nodes;

	private Iterator<D> candidates;
	
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
		while ((this.candidates == null || !this.candidates.hasNext()) && this.nodes.hasNext()) {
			final var node = this.nodes.next();
			this.candidates = node.getData().iterator();
		}
	}

	@Override
	public boolean hasNext() {
		return this.candidates != null && this.candidates.hasNext();
	}

	@Override
	public D next() {
		final var data = this.candidates.next();
		if (data == null) {
			throw new NoSuchElementException();
		}
		searchNextCandidates();
		return data;
	}

}
