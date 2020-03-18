package fr.utbm.gl52.tree.model.binary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;
import fr.utbm.gl52.tree.model.AbstractTreeNode;

/** Abstract tree nodes for all the nodes that have 2 children.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public abstract class AbstractBinaryTreeNode<N extends AbstractBinaryTreeNode<N, D>, D> extends AbstractTreeNode<N, D> {

	private N leftChild;
	
	private N rightChild;

	/** Constructor for a root node.
	 *
	 * @param factory the factory of nodes to be used.
	 */
	public AbstractBinaryTreeNode(TreeNodeFactory<N> factory) {
		super(factory);
	}

	/** Constructor for a child node.
	 *
	 * @param parent is the parent node of this node.
	 * @param factory the factory of nodes to be used.
	 */
	public AbstractBinaryTreeNode(N parent, TreeNodeFactory<N> factory) {
		super(parent, factory);
	}

	/** Replies the left branch.
	 *
	 * @return the left branch or {@code null} if no branch is at the left.
	 */
	public N getLeftChild() {
		return this.leftChild;
	}

	/** Replies the right branch.
	 *
	 * @return the right branch or {@code null} if no branch is at the right.
	 */
	public N getRightChild() {
		return this.rightChild;
	}

	/** Replies the left branch.
	 *
	 * @param createIfNone indicates if the left branch must be created if it was not created before.
	 * @return the left branch, never {@code null}.
	 */
	public N getLeftChild(boolean createIfNone) {
		if (this.leftChild == null && createIfNone) {
			this.leftChild = getFactory().create(asN());
			if (this.leftChild != null) {
				fireChildAdded(this.leftChild, 0);
			}
		}
		return this.leftChild;
	}

	/** Replies the right branch.
	 *
	 * @param createIfNone indicates if the right branch must be created if it was not created before.
	 * @return the right branch, never {@code null}.
	 */
	public N getRightChild(boolean createIfNone) {
		if (this.rightChild == null && createIfNone) {
			this.rightChild = getFactory().create(asN());
			if (this.rightChild != null) {
				fireChildAdded(this.rightChild, 1);
			}
		}
		return this.rightChild;
	}

	@Override
	public List<N> getChildren() {
		if (this.leftChild != null) {
			if (this.rightChild != null) {
				return Arrays.asList(this.leftChild, this.rightChild);
			}
			return Collections.singletonList(this.leftChild);
		} else if (this.rightChild != null) {
			return Collections.singletonList(this.rightChild);
		}
		return Collections.emptyList();
	}

	@Override
	public int getChildCount() {
		if (this.leftChild != null) {
			if (this.rightChild != null) {
				return 2;
			}
			return 1;
		} else if (this.rightChild != null) {
			return 1;
		}
		return 0;
	}

}
