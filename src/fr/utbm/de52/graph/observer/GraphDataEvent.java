package fr.utbm.de52.graph.observer;

import java.util.EventObject;

import fr.utbm.de52.graph.model.AbstractGraphElement;

/** Description of an event into a graph.
 * 
 * @author sgalland
 */
public class GraphDataEvent extends EventObject {

	private static final long serialVersionUID = -1576224582644565132L;

	private final Object data;

	/** Constructor.
	 *
	 * @param source the source of the event.
	 * @param data the data associated to the event.
	 */
	public <D> GraphDataEvent(AbstractGraphElement<D> source, D data) {
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
