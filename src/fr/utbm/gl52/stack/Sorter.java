package fr.utbm.gl52.stack;

/** A tool for sorting the data into a stack.
 * The ascending or descending sorting depends on the {@code Sorter} implementation.
 * 
 * @author sgalland
 */
public interface Sorter {

	/** Sort the elements into the stack.
	 * This function has a boder effect on its argument.
	 *
	 * @param stack the stack to sort.
	 * @throws StackException if the stack cannot be changed.
	 */
	void sort(Stack<?> stack) throws StackException;

}
