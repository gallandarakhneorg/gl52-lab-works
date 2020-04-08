package fr.utbm.gl52.tree.model;

import java.util.Iterator;

import fr.utbm.gl52.tree.iterator.DataIterator;
import fr.utbm.gl52.tree.iterator.DepthNCTreeNodeIterator;
import fr.utbm.gl52.tree.observer.TreeListener;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public interface Tree<N extends TreeNode<N, D>, D> extends Iterable<N> {

	/** Replies the root node.
	 *
	 * @return the root node, never {@code null}.
	 */
	N getRoot();

	/** Insert the data into the tree.
	 *
	 * @param data the data to insert into the tree.
	 * @return the node into which the data was added.
	 */
	default N insertData(D data) {
		return getRoot().insertData(data);
	}

	/** Replies the maximal depth of the tree.
	 *
	 * @return the maximal depth, i.e. the greater number of levels into the nodes' hierarchy.
	 */
	int getMaximumDepth();

	/** Replies an iterator on the data that are stored into the tree.
	 *
	 * @return the data iterator.
	 */
	default Iterator<D> dataIterator() {
		return new DataIterator<>(iterator());
	}

	@Override
	default Iterator<N> iterator() {
		return new DepthNCTreeNodeIterator<>(getRoot());
	}

	/** Add an observer on the events related to data.
	 *
	 * @param observer the observer to add.
	 */
	default void addTreeListener(TreeListener observer) {
		getRoot().addTreeListener(observer);
	}

	/** Add an observer on the events related to data.
	 *
	 * @param observer the observer to add.
	 */
	default void removeTreeListener(TreeListener observer) {
		getRoot().removeTreeListener(observer);
	}

}
