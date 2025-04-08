package fr.utbm.de52.graph.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.utbm.de52.graph.observer.GraphDataEvent;
import fr.utbm.de52.graph.observer.GraphListener;

/** Abstract implementation of a graph element with data.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the graph.
 */
public abstract class AbstractGraphElement<D> implements GraphElement<D> {

	// I decided to use an ArrayList because the complexity of this data-structure
	// is compatible with the usage of tree nodes, i.e. a lot of reading compared
	// to a smaller number of additions.
	private final Collection<D> dataStorage = new ArrayList<>();

	private Collection<GraphListener> listeners;

	private final WeakReference<AbstractGraph<?, ?, D>> graph;

	private final String name;

	/** Constructor.
	 *
	 * @param name the name of the element.
	 * @param graph the graph.
	 */
	public AbstractGraphElement(String name, AbstractGraph<?, ?, D> graph) {
		assert graph != null;
		this.name = name;
		this.graph = new WeakReference<>(graph);
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public AbstractGraph<?, ?, D> getGraph() {
		return this.graph == null ? null : this.graph.get();
	}

	@Override
	public final void addData(D data) {
		assert data != null;
		if (this.dataStorage.add(data)) {
			fireDataAdded(data);
		}
	}

	@Override
	public final void removeData(D data) {
		assert data != null;
		if (this.dataStorage.remove(data)) {
			fireDataRemoved(data);
		}
	}

	@Override
	public final Collection<D> getData() {
		return Collections.unmodifiableCollection(this.dataStorage);
	}

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
			final var observers = new GraphListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final var observer : observers) {
				observer.dataAdded(event);
			}
		}
	}
	
	/** Notify the observers that a data was added.
	 *
	 * @param data the added data.
	 */
	protected void fireDataAdded(D data) {
		final var event = new GraphDataEvent(this, data);
		fireDataAdded(event);
		final var egraph = getGraph();
		if (egraph != null) {
			egraph.fireDataAdded(event);
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
			final var observers = new GraphListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final var observer : observers) {
				observer.dataRemoved(event);
			}
		}
	}

	/** Notify the observers that a data was removed.
	 *
	 * @param data the removed data.
	 */
	protected void fireDataRemoved(D data) {
		final var event = new GraphDataEvent(this, data);
		fireDataRemoved(event);
		final var egraph = getGraph();
		if (egraph != null) {
			egraph.fireDataRemoved(event);
		}
	}

}
