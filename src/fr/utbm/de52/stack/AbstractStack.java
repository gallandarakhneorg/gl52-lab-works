package fr.utbm.de52.stack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/** Abstract implementation of a stack.
 *
 * @author sgalland
 * @param <T> the type of the data stored into the stack.
 */
public abstract class AbstractStack<T> implements Stack<T> {

	// This function does not need to be implemented here because of the
	// default implementation that is defined in the super interface.
	// Therefore the code in automatically inherited from the stack definition.
	/*@Override
	public boolean isEmpty() {
		return size() == 0;
	}*/

	@Override
	public String toString() {
		final var iterator = iterator();
		final var stringRepresentation = new StringBuilder();
		stringRepresentation.append("{"); //$NON-NLS-1$
		var first = true;
		while (iterator.hasNext()) {
			final var element = iterator.next();
			if (first) {
				first = false;
			} else {
				stringRepresentation.append(", "); //$NON-NLS-1$
			}
			stringRepresentation.append(element.toString());
		}
		stringRepresentation.append("}"); //$NON-NLS-1$
		return stringRepresentation.toString();
	}

	@Override
	public Iterator<T> iterator() {
		//return new ExternalStackIterator<>(this);
		return new StackIterator();
	}

	/** Replies the element at the given position into the stack.
	 *
	 * @param index the index of the element to retrieve.
	 * @return the element.
	 * @throws IndexOutOfBoundsException if the index is not inside the stack.
	 */
	protected abstract T getElementAt(int index);
	
	/** An iterator on a stack that is replying the elements from the top to the bottom
	 * of the stack.
	 *
	 * @author sgalland
	 */
	private class StackIterator implements Iterator<T> {

		private int position;

		StackIterator() {
			this.position = AbstractStack.this.size() - 1;
		}
		
		@Override
		public boolean hasNext() {
			return this.position >= 0;
		}

		@Override
		public T next() {
			/*if (this.position < 0) {
				throw new NoSuchElementException();
			}*/
			assert this.position >= 0 : "Fucking developer";
			final var dataElement = AbstractStack.this.getElementAt(this.position);
			--this.position;
			return dataElement;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Collection<StackListener> listeners;

	
	
	@Override
	public final void addStackListener(StackListener listener) {
		assert listener != null;
		if (this.listeners == null) {
			this.listeners = new ArrayList<>();
		}
		this.listeners.add(listener);
	}

	@Override
	public final void removeStackListener(StackListener listener) {
		assert listener != null;
		if (this.listeners != null) {
			this.listeners.remove(listener);
			if (this.listeners.isEmpty()) {
				this.listeners = null;
			}
		}
	}

	/** Notify the observers that a data was added.
	 *
	 * @param data the added data.
	 */
	protected final void fireDataAdded(T data) {
		// We must clone the list of listeners because the stack's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous JAva exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final StackListener[] observers = new StackListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final StackListener observer : observers) {
				observer.dataAdded(this, data);
			}
			/*for (final StackListener observer : this.listeners) {
				observer.dataAdded(this, data);
			}*/
		}
	}

	/** Notify the observers that a data was removed.
	 *
	 * @param data the removed data.
	 */
	protected final void fireDataRemoved(T data) {
		// We must clone the list of listeners because the stack's functions
		// could be called from the listener's functions (or any function called
		// from the listener's functions). This may cause an invalid result, or
		// the famous JAva exception "ConcurrentModificationException".
		if (this.listeners != null) {
			final StackListener[] observers = new StackListener[this.listeners.size()];
			this.listeners.toArray(observers);
			for (final StackListener observer : observers) {
				observer.dataRemoved(this, data);
			}
		}
	}

}
