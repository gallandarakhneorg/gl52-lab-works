package fr.utbm.gl52.stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Sort a stack in ascending order from top to bottom.
 * The top-most data element into the stack will have the lower
 * value.
 * The ascending order criteria is:<ul>
 * <li>If the type of the stack's data is {@link Comparable}, the function
 * {@link Comparable#compareTo(Object)} is invoked to compare the stack's elements.</li>
 * <li>The natural order of the data is used (for numbers and strings).</li>
 * <li>The addresses in memory of the data object are used for comparison.</li>
 * </ul>
 * 
 * @author sgalland
 */
public class AscendingOrderSorter implements Sorter {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Comparator<Object> COMPARATOR = (a, b) -> {
		if (a == b) {
			return 0;
		}
		if (a == null) {
			return -1;
		}
		if (b == null) {
			return 1;
		}
		assert a != null && b != null;
		if (a instanceof Comparable) {
			return ((Comparable) a).compareTo(b);
		}
		if (a instanceof Number) {
			return Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue());
		}
		return Integer.compare(System.identityHashCode(a), System.identityHashCode(b));
	};
	
	@Override
	public void sort(Stack<?> stack) throws StackException {
		sortStack(stack);
	}

	/** Sort the given stack in ascending order.
	 *
	 * @param <T> the type of the element into the stack.
	 * @param stack the stack to sort.
	 * @throws StackException if the stack cannot be changed.
	 */
	public static <T> void sortStack(Stack<T> stack) throws StackException {
		assert stack != null;
		final List<T> content = new ArrayList<>();

		while (!stack.isEmpty()) {
			content.add(stack.pop());
		}

		// Sort in reverse order because the stack must be filled
		// from bottom to top.
		content.sort((a, b) -> COMPARATOR.compare(b, a));

		for (final T element : content) {
			stack.push(element);
		}
	}

}
