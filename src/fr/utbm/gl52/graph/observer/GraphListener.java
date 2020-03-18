package fr.utbm.gl52.graph.observer;

import java.util.EventListener;

/** Observer on events in a graph.
 * 
 * @author sgalland
 */
public interface GraphListener extends EventListener {

	/** Invoked when a data is added into a graph element.
	 *
	 * @param event the description of the event.
	 */
	void dataAdded(GraphDataEvent event);

	/** Invoked when a data is removed from a graph element.
	 *
	 * @param event the description of the event.
	 */
	void dataRemoved(GraphDataEvent event);

}
