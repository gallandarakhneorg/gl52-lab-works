package fr.utbm.de52.tree.model;

import java.util.Collection;
import java.util.List;

import fr.utbm.de52.tree.observer.TreeListener;

/** This interface represents a node (and its children) into a tree of data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public interface TreeNode<N extends TreeNode<N, D>, D> {

	/** Replies this node casted as N.
	 *
	 * @return this node.
	 */
	@SuppressWarnings("unchecked")
	default N asN() {
		return (N) this;
	}

	/** Replies the parent node.
	 *
	 * @return the parent node, or {@code null} if this node is the root node.
	 */
	N getParent();
	
	/** Replies the node is the root node, i.e. the top-most node of the node hierarchy.
	 *
	 * @return {@code true} if the node is the root node.
	 */
	default boolean isRootNode() {
		return getParent() == null;
	}

	/** Replies the node is the leaf node, a node without child.
	 *
	 * @return {@code true} if the node is the root node.
	 */
	default boolean isLeafNode() {
		return getChildCount() == 0;
	}

	/** Replies the unmodifiable list of all the child nodes.
	 *
	 * @return the children.
	 */
	List<N> getChildren();
	
	/** Replies the number of child nodes.
	 *
	 * @return the number of children.
	 */
	int getChildCount();

	/** Replies the unmodifiable list of data stored into this node (but the data from the child nodes).
	 *
	 * @return the data into the node.
	 */
	Collection<D> getData();
	
	/** Add data into this node and not into its children
	 *
	 * @param data the data to be added.
	 */
	void addData(D data);

	/** Remove data from this node and not into its children
	 *
	 * @param data the data to be remove.
	 */
	void removeData(D data);

	/** Add data into this node or one of its children depending on the tree node implementation.
	 *
	 * @param data the data to be added.
	 * @return the node in which the data is really added.
	 */
	N insertData(D data);

	/** Add an observer on the events related to data.
	 *
	 * @param observer the observer to add.
	 */
	void addTreeListener(TreeListener observer);

	/** Add an observer on the events related to data.
	 *
	 * @param observer the observer to add.
	 */
	void removeTreeListener(TreeListener observer);

	/** Replies the maximal depth of the tree.
	 *
	 * @return the maximal depth, i.e. the greater number of levels into the nodes' hierarchy.
	 */
	default int getMaximumDepth() {
		var maxSubLevel = 0;
		for (final var child : getChildren()) {
			final var level = child.getMaximumDepth();
			if (level > maxSubLevel) {
				maxSubLevel = level;
			}
		}
		return maxSubLevel + 1;
	}

}
