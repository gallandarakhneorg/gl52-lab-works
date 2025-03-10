package fr.utbm.gl52.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** An iterator on a stack that is replying the elements from the top to the bottom
 * of the stack.
 *
 * @param <T> is the type of the data stored in the stack.
 * @author sgalland
 */
public class ExternalStackIterator<T> implements Iterator<T> {

	private final AbstractStack<T> structure;
	private int position;

	/** Constructor.
	 *
	 * @param structure is the pointer to the stack on which the iteration must be applied.
	 */
	public ExternalStackIterator(AbstractStack<T> structure) {
		this.structure = structure;
		this.position = structure.size() - 1;
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
		final T dataElement = this.structure.getElementAt(this.position);
		--this.position;
		return dataElement;
	}
	
}
