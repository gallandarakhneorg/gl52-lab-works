package fr.utbm.de52.tree.iterator;

import java.util.NoSuchElementException;

import fr.utbm.de52.tree.model.binary.AbstractBinaryTreeNode;

/** Depth-first LNR Implementation of the iterator on tree nodes that replies the left branch first, the node, and the right branch.
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 * @see "https://en.wikipedia.org/wiki/Tree_traversal"
 */
public class DepthLNRTreeNodeIterator<N extends AbstractBinaryTreeNode<N, ?>> extends AbstractArrayBasedTreeNodeIterator<N> {

	private N node;

	private N next;
	
	/** Constructor the iterator from the given root.
	 *
	 * @param root the root from which to start.
	 */
	public DepthLNRTreeNodeIterator(N root) {
		super(root);
		searchNextCandidate();
	}

	private void searchNextCandidate() {
		this.next = null;
		while (!this.memory.isEmpty() || this.node != null) {
			if (this.node == null) {
				final var nextCandidate = consumeElement();
				this.node = nextCandidate.getRightChild();
				this.next = nextCandidate;
				return;
			}
			offerElement(this.node);
			this.node = this.node.getLeftChild();
		}
	}
	
	@Override
	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public N next() {
		final var candidate = this.next;
		if (candidate == null) {
			throw new NoSuchElementException();
		}
		searchNextCandidate();
		return candidate;
	}

	@Override
	protected void offerElement(N element) {
		assert element != null;
		this.memory.push(element);
	}

	@Override
	protected N consumeElement() {
		if (this.memory.isEmpty()) {
			return null;
		}
		return this.memory.pop();
	}

}
