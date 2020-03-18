package fr.utbm.gl52.tree.model.bst;

import java.util.Comparator;
import java.util.Iterator;

import fr.utbm.gl52.tree.iterator.DepthLNRTreeNodeIterator;
import fr.utbm.gl52.tree.model.binary.AbstractBinaryTree;

/** This interface represents a tree of data.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the tree.
 */
public class BinarySearchTree<D> extends AbstractBinaryTree<BinarySearchTreeNode<D>, D> {

	/** Constructor of a tree with this node factory.
	 *
		 * @param comparator the comparator to give to the created nodes.
	 */
	public BinarySearchTree(Comparator<? super D> comparator) {
		super(new BinarySearchTreeNode.Factory<>(comparator));
	}

	/** Constructor with the default node factory.
	 */
	public BinarySearchTree() {
		this(null);
	}


	@Override
	public Iterator<BinarySearchTreeNode<D>> iterator() {
		return new DepthLNRTreeNodeIterator<>(getRoot());
	}

}
