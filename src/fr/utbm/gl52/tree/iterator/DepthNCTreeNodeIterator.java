package fr.utbm.gl52.tree.iterator;

import fr.utbm.gl52.tree.model.TreeNode;

/** Depth-first N-Children implementation of the iterator on tree nodes.
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 * @see "https://en.wikipedia.org/wiki/Tree_traversal"
 */
public class DepthNCTreeNodeIterator<N extends TreeNode<N, ?>> extends AbstractArrayBasedTreeNodeIterator<N> {

	/** Constructor the iterator from the given root.
	 *
	 * @param root the root from which to start.
	 */
	public DepthNCTreeNodeIterator(N root) {
		super(root);
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
