package fr.utbm.gl52.tree.model;

import java.util.Iterator;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;
import fr.utbm.gl52.tree.iterator.DepthNLRTreeNodeIterator;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public abstract class AbstractTree<N extends AbstractTreeNode<N, D>, D> implements Tree<N, D> {

	private final TreeNodeFactory<N> factory;

	private N root;

	/** Constructor of a tree.
	 *
	 * @param factory the factory of nodes.
	 */
	public AbstractTree(TreeNodeFactory<N> factory) {
		assert factory != null;
		this.factory = factory;
	}

	/** Replies the node factory.
	 *
	 * @return the factory.
	 */
	protected TreeNodeFactory<N> getFactory() {
		return this.factory;
	}

	@Override
	public final N getRoot() {
		if (this.root == null) {
			this.root = getFactory().createRootNode();
		}
		return this.root;
	}

	@Override
	public int getMaximumDepth() {
		return getRoot().getMaximumDepth();
	}

	@Override
	public Iterator<N> iterator() {
		return new DepthNLRTreeNodeIterator<>(getRoot());
	}

	@Override
	public String toString() {
		final Iterator<D> iterator = dataIterator();
		final StringBuilder text = new StringBuilder();
		boolean first = true;
		text.append("{"); //$NON-NLS-1$
		while (iterator.hasNext()) {
			final D data = iterator.next();
			if (first) {
				first = false;
			} else {
				text.append(", "); //$NON-NLS-1$
			}
			text.append(data);
		}
		text.append("}"); //$NON-NLS-1$
		return text.toString();
	}

}
