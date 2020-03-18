package fr.utbm.gl52.stack;

import java.util.Arrays;

/** Stack implementation based on an internal array.
 * This implementation is not thread-safe.
 *
 * @author sgalland
 * @param <T> the type of the data stored into the stack.
 */
public class ArrayStack<T> extends AbstractStack<T> {

	private T[] content = null;
	
	/** Construct an empty stack.
	 */
	public ArrayStack() {
		grow(0);
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
	public void push(T data) {
		if (data != null) {
			grow(this.content.length + 1);
			this.content[this.content.length - 1] = data;
			fireDataAdded(data);
		}
	}

	@Override
	public T pop() throws StackException {
		if (this.content.length == 0) {
			throw new StackException();
		}
		final T topData = this.content[this.content.length - 1];
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

}
