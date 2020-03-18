package fr.utbm.gl52.tree.model.bst;

import java.util.Comparator;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;

/** A concrete binary earch tree node that contains other binary search tree nodes as children.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the tree.
 */
public class BinarySearchTreeNode<D> extends AbstractBinarySearchTreeNode<BinarySearchTreeNode<D>, D> {

	/** Constructor for root node.
	 *
	 * @param factory the factory of nodes to be used.
	 * @param comparator the comparator of data for inserting data into the tree.
	 */
	public BinarySearchTreeNode(TreeNodeFactory<BinarySearchTreeNode<D>> factory, Comparator<? super D> comparator) {
		super(factory, comparator);
	}

	/** Constructor for child node.
	 *
	 * @param parent is the parent node of this node.
	 * @param factory the factory of nodes to be used.
	 * @param comparator the comparator of data for inserting data into the tree.
	 */
	public BinarySearchTreeNode(BinarySearchTreeNode<D> parent, TreeNodeFactory<BinarySearchTreeNode<D>> factory, Comparator<? super D> comparator) {
		super(parent, factory, comparator);
	}
	
	/** Factory of binary tree nodes.
	 * 
	 * @author sgalland
	 * @param <D> the type of the data that is stored into the tree.
	 */
	public static class Factory<D> implements TreeNodeFactory<BinarySearchTreeNode<D>> {

		private final Comparator<? super D> comparator;

		/** Constructor.
		 *
		 * @param comparator the comparator to give to the created nodes.
		 */
		public Factory(Comparator<? super D> comparator) {
			this.comparator = comparator;
		}
		
		@Override
		public BinarySearchTreeNode<D> createRootNode() {
			return new BinarySearchTreeNode<>(this, this.comparator);
		}

		@Override
		public BinarySearchTreeNode<D> create(BinarySearchTreeNode<D> parent) {
			return new BinarySearchTreeNode<>(parent, this, this.comparator);
		}
		
	}

}
