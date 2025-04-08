package fr.utbm.de52.tree.model.bst;

import java.util.Comparator;

import fr.utbm.de52.tree.factory.TreeNodeFactory;
import fr.utbm.de52.tree.model.binary.AbstractBinaryTreeNode;

/** Abstract tree nodes for all the nodes that have 2 children.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public abstract class AbstractBinarySearchTreeNode<N extends AbstractBinarySearchTreeNode<N, D>, D> extends AbstractBinaryTreeNode<N, D> {

	private final Comparator<? super D> comparator;
	
	/** Constructor for a root node.
	 *
	 * @param factory the factory of nodes to be used.
	 * @param comparator the comparator of data for inserting data into the tree.
	 */
	public AbstractBinarySearchTreeNode(TreeNodeFactory<N> factory, Comparator<? super D> comparator) {
		super(factory);
		this.comparator = comparator;
	}

	/** Constructor for a child node.
	 *
	 * @param parent is the parent node of this node.
	 * @param factory the factory of nodes to be used.
	 * @param comparator the comparator of data for inserting data into the tree.
	 */
	public AbstractBinarySearchTreeNode(N parent, TreeNodeFactory<N> factory, Comparator<? super D> comparator) {
		super(parent, factory);
		this.comparator = comparator;
	}

	/** Compare the two data.
	 *
	 * <p>If a comparator is provided, it is used for comparing. Otherwise the natural order of the value is used.
	 *
	 * @param a the first data.
	 * @param b the second data.
	 * @return a negative value if {@code a} is strictly lower than {@code b};
	 * a positive value of {@code a} is strictly greater than {@code b};
	 * zero if {@code a} and {@code b} are equal.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int compareData(D a, D b) {
		if (this.comparator != null) {
			return this.comparator.compare(a, b);
		}
		if (a == b) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		assert a != null && b != null;
		if (a instanceof Comparable) {
			return ((Comparable) a).compareTo(b);
		}
		if (a instanceof Number) {
			return Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue());
		}
		if (a instanceof String) {
			return ((String) a).compareTo((String) b);
		}
		return Integer.compare(System.identityHashCode(a), System.identityHashCode(b));
	}

	@Override
	public N insertData(D data) {
		assert data != null;
		if (getData().isEmpty()) {
			addData(data);
			return asN();
		}
		final var reference = getData().iterator().next();
		final var cmp = compareData(data, reference);
		if (cmp == 0) {
			return null;
		}
		if (cmp < 0) {
			return getLeftChild(true).insertData(data);
		}
		return getRightChild(true).insertData(data);
	}

}
