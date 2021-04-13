package fr.utbm.gl52.tree.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import fr.utbm.gl52.tree.factory.TreeNodeFactory;
import fr.utbm.gl52.tree.observer.TreeDataEvent;
import fr.utbm.gl52.tree.observer.TreeEvent;
import fr.utbm.gl52.tree.observer.TreeListener;

/** Abstract implementation for all the tree nodes.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the tree.
 * @param <D> the type of the data that is stored into the tree.
 */
public abstract class AbstractTreeNode<N extends AbstractTreeNode<N, D>, D> implements TreeNode<N, D> {

	// I decided to use an ArrayList because the complexity of this data-structure
	// is compatible with the usage of tree nodes, i.e. a lot of reading compared
	// to a smaller number of additions.
	private final Collection<D> dataStorage = new ArrayList<>();

	private final TreeNodeFactory<N> factory;

	private final WeakReference<N> parent;

	private Collection<TreeListener> listeners;

	/** Constructor a root node.
	 *
	 * @param factory the factory of nodes to be used.
	 */
	public AbstractTreeNode(TreeNodeFactory<N> factory) {
		assert factory != null;
		this.parent = null;
		this.factory = factory;
	}

	/** Constructor for a child node.
	 *
	 * @param parent is the parent node of this node.
	 * @param factory the factory of nodes to be used.
	 */
	public AbstractTreeNode(N parent, TreeNodeFactory<N> factory) {
		assert parent != null;
		assert factory != null;
		this.parent = new WeakReference<>(parent);
		this.factory = factory;
	}

	/** Replies the node that is at the root of the tree.
	 *
	 * @return the root.
	 */
	protected final N getRoot() {
		N p = getParent();
		N pp = asN();
		while (p != null) {
			pp = p;
			p = p.getParent();
		}
		assert pp != null;
		return pp;
	}

	@Override
	public final N getParent() {
		if (this.parent != null) {
			return this.parent.get();
		}
		return null;
	}

	/** Replies the factory of nodes that should be used by this tree node for creating child nodes.
	 *
	 * @return the factory.
	 */
	protected final TreeNodeFactory<N> getFactory() {
		return this.factory;
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
	public void addTreeListener(TreeListener observer) {
		assert observer != null;
		if (this.listeners == null) {
			this.listeners = new ArrayList<>();
		}
		this.listeners.add(observer);
	}

	@Override
	public void removeTreeListener(TreeListener observer) {
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
	protected void fireDataAdded(TreeDataEvent event) {
		// We must clone the list of listeners because the tree's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous Java exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final TreeListener[] observers = new TreeListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final TreeListener observer : observers) {
				observer.dataAdded(event);
			}
		}
	}

	/** Notify the observers that a data was added.
	 *
	 * @param data the added data.
	 */
	protected void fireDataAdded(D data) {
		final TreeDataEvent event = new TreeDataEvent(this, data);
		fireDataAdded(event);
		final N root = getRoot();
		if (root != null && root != this) {
			root.fireDataAdded(event);
		}
	}

	/** Notify the observers that a data was removed.
	 *
	 * @param event the event associated to the removed data.
	 */
	protected void fireDataRemoved(TreeDataEvent event) {
		// We must clone the list of listeners because the tree's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous Java exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final TreeListener[] observers = new TreeListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final TreeListener observer : observers) {
				observer.dataRemoved(event);
			}
		}
	}

	/** Notify the observers that a data was removed.
	 *
	 * @param data the removed data.
	 */
	protected void fireDataRemoved(D data) {
		final TreeDataEvent event = new TreeDataEvent(this, data);
		fireDataRemoved(event);
		final N root = getRoot();
		if (root != null && root != this) {
			root.fireDataRemoved(event);
		}
	}

	/** Notify the observers that a child node was added.
	 *
	 * @param event the event associated to the added data.
	 */
	protected void fireChildAdded(TreeEvent event) {
		// We must clone the list of listeners because the tree's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous Java exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final TreeListener[] observers = new TreeListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final TreeListener observer : observers) {
				observer.childCreated(event);
			}
		}
	}

	/** Notify the observers that a child node was added.
	 *
	 * @param child the new child.
	 * @param index the index of the child.
	 */
	protected void fireChildAdded(N child, int index) {
		final TreeEvent event = new TreeEvent(this, child, index);
		fireChildAdded(event);
		final N root = getRoot();
		if (root != null && root != this) {
			root.fireChildAdded(event);
		}
	}

	@Override
	public String toString() {
		final StringBuilder text = new StringBuilder();
		boolean first = true;
		text.append("{"); //$NON-NLS-1$
		for(final D data : getData()) {
			if (first) {
				first = false;
			} else {
				text.append(", "); //$NON-NLS-1$
			}
			text.append(data);
		}
		text.append("}"); //$NON-NLS-1$
		return text.toString();
	}

}
