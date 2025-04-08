package fr.utbm.de52.tree.model.binary;

import fr.utbm.de52.tree.factory.TreeNodeFactory;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the tree.
 */
public class BinaryTree<D> extends AbstractBinaryTree<BinaryTreeNode<D>, D> {

	/** The singleton of the node factory.
	 */
	@SuppressWarnings("rawtypes")
	private static final Factory FACTORY_SINGLETON = new Factory<>();

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
		super(FACTORY_SINGLETON);
	}

	/** Factory of binary tree nodes.
	 * 
	 * @author sgalland
	 * @param <D> the type of the data that is stored into the tree.
	 */
	public static class Factory<D> implements TreeNodeFactory<BinaryTreeNode<D>> {

		@Override
		public BinaryTreeNode<D> createRootNode() {
			return new BinaryTreeNode<>(this);
		}

		@Override
		public BinaryTreeNode<D> create(BinaryTreeNode<D> parent) {
			return new BinaryTreeNode<>(parent, this);
		}
		
	}

}
