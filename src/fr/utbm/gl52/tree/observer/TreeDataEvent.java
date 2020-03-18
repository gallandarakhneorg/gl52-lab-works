package fr.utbm.gl52.tree.observer;

import java.util.EventObject;

import fr.utbm.gl52.tree.model.TreeNode;

/** Description of an event into a tree node.
 * 
 * @author sgalland
 */
public class TreeDataEvent extends EventObject {

	private static final long serialVersionUID = -1576224582644565132L;

	private final Object data;

	/** Constructor.
	 *
	 * @param source the source of the event.
	 * @param data the data associated to the event.
	 */
	public <D> TreeDataEvent(TreeNode<?, D> source, D data) {
		super(source);
		this.data = data;
	}

	/** Replies the data associated to the event.
	 *
	 * @return the data.
	 */
	public Object getData() {
		return this.data;
	}

}
