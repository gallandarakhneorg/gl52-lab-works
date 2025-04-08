package fr.utbm.de52.tree.model.binary;

import fr.utbm.de52.tree.factory.TreeNodeFactory;
import fr.utbm.de52.tree.model.AbstractTree;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public abstract class AbstractBinaryTree<N extends AbstractBinaryTreeNode<N, D>, D> extends AbstractTree<N, D> {

	/** Constructor of a binary tree.
	 *
	 * @param factory the factory of nodes.
	 */
	public AbstractBinaryTree(TreeNodeFactory<N> factory) {
		super(factory);
	}

}
