package fr.utbm.de52.tree.observer;

import java.util.EventObject;

import fr.utbm.de52.tree.model.TreeNode;

/** Description of an event into a tree node.
 * 
 * @author sgalland
 */
public class TreeEvent extends EventObject {

	private static final long serialVersionUID = -1576224582644565132L;

	private final TreeNode<?, ?> child;

	private final int childIndex;

	/** Constructor.
	 *
	 * @param source the source of the event.
	 * @param child the child associated to the event.
	 * @param index is the index of the child.
	 */
	public <N extends TreeNode<?, ?>> TreeEvent(N source, N child, int index) {
		super(source);
		this.child = child;
		this.childIndex = index;
	}

	/** Replies the child associated to the event.
	 *
	 * @return the child.
	 */
	TreeNode<?, ?> getChildNode() {
		return this.child;
	}

	/** Replies the index of the child into its parent node.
	 *
	 * @return the index of the child.
	 */
	public int getIndex() {
		return this.childIndex;
	}

}
