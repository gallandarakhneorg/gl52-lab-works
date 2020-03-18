package fr.utbm.gl52.tree.model.binary;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the tree.
 */
public class BinaryTree<D> extends AbstractBinaryTree<BinaryTreeNode<D>, D> {

	/** Constructor of a tree with this node factory.
	 *
	 * @param factory the node factory.
	 */
	public BinaryTree(TreeNodeFactory<BinaryTreeNode<D>> factory) {
		super(factory);
	}

	/** Constructor with the default node factory.
	 */
	@SuppressWarnings("unchecked")
	public BinaryTree() {
		super(BinaryTreeNode.FACTORY_SINGLETON);
	}

}
