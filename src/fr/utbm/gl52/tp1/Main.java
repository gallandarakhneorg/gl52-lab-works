package fr.utbm.gl52.tp1;

import java.util.Random;

import fr.utbm.gl52.stack.ArrayStack;
import fr.utbm.gl52.stack.AscendingOrderSorter;
import fr.utbm.gl52.stack.Sorter;
import fr.utbm.gl52.stack.Stack;
import fr.utbm.gl52.stack.StackException;
import fr.utbm.gl52.stack.StackListener;

/** Main program for TP1 of GL52.
 * 
 * @author sgalland
 */
public class Main {

	/** Main program.
	 *
	 * @param args command line arguments.
	 * @throws StackException if an operation on the stack cannot be proceed.
	 */
	public static void main(String[] args) throws StackException {
		final Random random = new Random();
	
		final Stack<Integer> stack = new ArrayStack<>();

		final StackListener observer = new StackListener() {
			
			@Override
			public void dataAdded(Stack<?> changedStack, Object newData) {
				System.out.println("ADDED: " + newData); //$NON-NLS-1$
			}
			
			@Override
			public void dataRemoved(Stack<?> changedStack, Object removedData) {
				System.out.println("REMOVED: " + removedData); //$NON-NLS-1$
			}
	
		};
		stack.addStackListener(observer);
		
		final int nb = random.nextInt(90) + 10;
		for (int i = 0; i < nb; ++i) {
			stack.push(Integer.valueOf(random.nextInt()));
		}

		System.out.println(stack.toString());

		final Sorter sorter = new AscendingOrderSorter();
		sorter.sort(stack);

		System.out.println(stack.toString());
	}

}
