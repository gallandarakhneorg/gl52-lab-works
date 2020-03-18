package fr.utbm.gl52.stack;

/** This interface represents a stack of data.
 * The stack structures data in order to enable to push the data element at the top
 * of the stack, and to pop (get and remove) the top-most data element only.
 *
 * @author sgalland
 * @param <T> the type of the data that is stored into the stack.
 */
public interface Stack<T> extends Iterable<T> {

	/** Put the data at the top position into the stack.
	 *
	 * @param data the data element to put at the top-most position of the stack.
	 * If the {@code data} is equal to {@code null}, it is not put into the stack.
	 */
	void push(T data);

	/** Remove the element at the top-most position of the stack and reply it.
	 * If there is no element into the stack, i.e. the stack is {@link #isEmpty() empty},
	 * then the exception {@code StackException} is thrown.
	 *
	 * @return the data at the top-most position into the stack; never {@code null}.
	 * @throws StackException if the stack is empty.
	 */
	T pop() throws StackException;
	
	/** Replies {@code true} if there is no element into the stack, {@code false} otherwise.
	 *
	 * @return the empty state of the stack.
	 */
	default boolean isEmpty() {
		return size() == 0;
	}
	
	/** Replies the number of elements into the stack.
	 *
	 * @return the number of elements into the stack.
	 */
	int size();

	/** Add the given observer on the changes into this stack.
	 *
	 * @param listener the listener.
	 */
	void addStackListener(StackListener listener);

	/** Remove the given observer on the changes into this stack.
	 *
	 * @param listener the listener.
	 */
	void removeStackListener(StackListener listener);

}
