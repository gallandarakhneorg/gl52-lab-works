package fr.utbm.gl52.tree.model.binary;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;

/** A concrete binary tree node that contains other binary tree nodes as children.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the tree.
 */
public class BinaryTreeNode<D> extends AbstractBinaryTreeNode<BinaryTreeNode<D>, D> {

	/** The singleton of the node factory.
	 */
	@SuppressWarnings("rawtypes")
	public static final Factory FACTORY_SINGLETON = new Factory<>();

	/** Constructor for root node.
	 *
	 * @param factory the factory of nodes to be used.
	 */
	public BinaryTreeNode(TreeNodeFactory<BinaryTreeNode<D>> factory) {
		super(factory);
	}

	/** Constructor for child node.
	 *
	 * @param parent is the parent node of this node.
	 * @param factory the factory of nodes to be used.
	 */
	public BinaryTreeNode(BinaryTreeNode<D> parent, TreeNodeFactory<BinaryTreeNode<D>> factory) {
		super(parent, factory);
	}

	@Override
	public BinaryTreeNode<D> insertData(D data) {
		addData(data);
		return this;
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
