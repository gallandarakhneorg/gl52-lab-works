package fr.utbm.gl52.stack;

import java.util.EventListener;

/** An observer on the events into a {@link Stack}.
 *
 * @author sgalland
 */
public interface StackListener extends EventListener {

	/** This function is invoked when a data is added at the top of the stack.
	 *
	 * @param stack the changed stack.
	 * @param newData the added data.
	 */
	default void dataAdded(Stack<?> stack, Object newData) {
		// By default, do nothing.
		// The implementation class should provide a specific code
		// for dataRemoved() or dataAdded(), or both.
	}
	
	/** This function is invoked when a data is removed from the top of the stack.
	 *
	 * @param stack the changed stack.
	 * @param removedData the removed data.
	 */
	default void dataRemoved(Stack<?> stack, Object removedData) {
		// By default, do nothing.
		// The implementation class should provide a specific code
		// for dataRemoved() or dataAdded(), or both.
	}

}
