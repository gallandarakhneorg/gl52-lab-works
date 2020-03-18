package fr.utbm.gl52.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.utbm.gl52.graph.iterator.DataIterator;
import fr.utbm.gl52.graph.iterator.GraphNodeIterator;
import fr.utbm.gl52.graph.observer.GraphDataEvent;
import fr.utbm.gl52.graph.observer.GraphListener;

/** Abstract implementation of a graph. Each node and edge may contains data.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 * @param <D> the type of the data that is stored into the graph.
 */
public abstract class AbstractGraph<N extends GraphNode<N, E, D>, E extends GraphEdge<N, E, D>, D> implements Graph<N, E, D> {

	private Collection<GraphListener> listeners;

	@Override
	public void addGraphListener(GraphListener observer) {
		assert observer != null;
		if (this.listeners == null) {
			this.listeners = new ArrayList<>();
		}
		this.listeners.add(observer);
	}

	@Override
	public void removeGraphListener(GraphListener observer) {
		assert observer != null;
		if (this.listeners != null) {
			this.listeners.remove(observer);
			if (this.listeners.isEmpty()) {
				this.listeners = null;
			}
		}
	}

	/** Notify the observers that a data was added.
	 *
	 * @param event the event associated to the added data.
	 */
	protected void fireDataAdded(GraphDataEvent event) {
		// We must clone the list of listeners because the stack's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous Java exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final GraphListener[] observers = new GraphListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final GraphListener observer : observers) {
				observer.dataAdded(event);
			}
		}
	}

	/** Notify the observers that a data was removed.
	 *
	 * @param event the event associated to the removed data.
	 */
	protected void fireDataRemoved(GraphDataEvent event) {
		// We must clone the list of listeners because the stack's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous Java exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final GraphListener[] observers = new GraphListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final GraphListener observer : observers) {
				observer.dataRemoved(event);
			}
		}
	}

	@Override
	public Iterator<N> iterator() {
		return new GraphNodeIterator<>(getNodes());
	}

	@Override
	public Iterator<D> dataIterator() {
		return new DataIterator<>(iterator());
	}

	@Override
	public String toString() {
		final StringBuilder text = new StringBuilder();
		text.append("{"); //$NON-NLS-1$
		final Iterator<D> iterator = dataIterator();
		boolean first = true;
		while (iterator.hasNext()) {
			final D data = iterator.next();
			if (first) {
				first = false;
			} else {
				text.append(", "); //$NON-NLS-1$
			}
			text.append(data.toString());
		}
		text.append("}"); //$NON-NLS-1$
		return text.toString();
	}

}
