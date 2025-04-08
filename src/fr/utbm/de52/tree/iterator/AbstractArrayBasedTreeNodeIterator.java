package fr.utbm.de52.tree.iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.utbm.de52.tree.model.TreeNode;

/** Abstract implementation of the iterator on tree nodes.
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 */
public abstract class AbstractArrayBasedTreeNodeIterator<N extends TreeNode<N, ?>> implements Iterator<N> {

	/** Internal data structure.
	 */
	protected final Deque<N> memory = new ArrayDeque<>();
	
	/** Constructor the iterator from the given root.
	 *
	 * @param root the root from which to start.
	 */
	public AbstractArrayBasedTreeNodeIterator(N root) {
		offerElement(root);
	}
	
	@Override
	public boolean hasNext() {
		return !this.memory.isEmpty();
	}

	@Override
	public N next() {
		final var nextElement = consumeElement();
		if (nextElement == null) {
			throw new NoSuchElementException();
		}
		for (final var candidate : nextElement.getChildren()) {
			offerElement(candidate);
		}
		return nextElement;
	}

	/** Offer the element to the internal data-structure.
	 *
	 * @param element the element to offer to the internal data structure.
	 */
	protected abstract void offerElement(N element);
	
	/** Get and remove the next element from the internal data-structure.
	 *
	 * @return the element removed from the internal data structure.
	 */
	protected abstract N consumeElement();

}
