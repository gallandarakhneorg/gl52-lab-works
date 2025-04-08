package fr.utbm.de52.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Stack implementation based on an internal array.
 * This implementation is not thread-safe.
 *
 * @author sgalland
 * @param <T> the type of the data stored into the stack.
 */
public class ArrayStack<T> extends AbstractStack<T> {

	private T[] content;
	
	/** Construct an empty stack.
	 */
	public ArrayStack() {
		grow(0);
	}
	
	@Override
	public boolean isEmpty() {
		// This implementation is faster than the default code
		return this.content.length == 0;
	}

	// The following annotation is added because there is no other mean
	// to avoid unchecked warning from Object[] to T[]
	@SuppressWarnings("unchecked")
	private void grow(int newSize) {
		if (newSize <= 0) {
			this.content = (T[]) new Object[0];
		} else {
			assert this.content != null;
			this.content = Arrays.copyOf(this.content, newSize);
		}
	}
	
	@Override
	public void push(final T data) {
		assert data != null;
		grow(this.content.length + 1);
		this.content[this.content.length - 1] = data;
		fireDataAdded(data);
	}

	@Override
	public T pop() throws StackException {
		assert this.content.length != 0;
		final var topData = this.content[this.content.length - 1];
		grow(this.content.length - 1);
		fireDataRemoved(topData);
		return topData;
	}

	@Override
	public int size() {
		return this.content.length;
	}

	@Override
	protected T getElementAt(int index) {
		if (index < 0 || index >= this.content.length) {
			throw new IndexOutOfBoundsException();
		}
		return this.content[index];
	}

	/** An iterator on a stack that is replying the elements from the top to the bottom
	 * of the stack.
	 *
	 * @author sgalland
	 */
	private class StackIterator implements Iterator<T> {

		private int position;

		StackIterator() {
			this.position = ArrayStack.this.content.length - 1;
		}
		
		@Override
		public boolean hasNext() {
			return this.position >= 0;
		}

		@Override
		public T next() {
			if (this.position < 0) {
				throw new NoSuchElementException();
			}
			final var dataElement = ArrayStack.this.content[this.position];
			--this.position;
			return dataElement;
		}
		
	}

}
