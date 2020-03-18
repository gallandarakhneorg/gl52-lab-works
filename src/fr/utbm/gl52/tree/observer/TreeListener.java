package fr.utbm.gl52.tree.observer;

import java.util.EventListener;

/** Observer on events in a tree node.
 * 
 * @author sgalland
 */
public interface TreeListener extends EventListener {

	/** Invoked when a data is added into a tree node.
	 *
	 * @param event the description of the event.
	 */
	void dataAdded(TreeDataEvent event);

	/** Invoked when a data is removed from a tree node.
	 *
	 * @param event the description of the event.
	 */
	void dataRemoved(TreeDataEvent event);

	/** Invoked when a child node is added into a tree node.
	 *
	 * @param event the description of the event.
	 */
	void childCreated(TreeEvent event);

}
