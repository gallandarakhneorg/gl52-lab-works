package fr.utbm.de52.tree.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

import fr.utbm.de52.tree.model.TreeNode;

/** Only for oral explanation
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 * @see "https://en.wikipedia.org/wiki/Tree_traversal"
 */
@SuppressWarnings("all")
class BreadthFirstIteratorAlgorithms {

	void breadthFirstIterator_iterative(TreeNode<?, ?> root) { // Arguments => arguments' constructor
		
		// Local variables => attributes
		final var queue = new LinkedList<TreeNode<?, ?>>();
		
		// Constructors' code
		queue.add(root);
		
		// Condition => stop condition (hasNext)
		while (!queue.isEmpty()) {
			
			// Code of next()
			final var element = queue.removeFirst();
			
			// Code of next()
			for (final var child : element.getChildren()) {
				queue.add(child);
			}
			
			// Do action AAA with the element
			
		}
	}
	
	/////////////////////////////////////////////////
	
	class MyIterator implements Iterator<TreeNode<?,?>> {
		
		private LinkedList<TreeNode<?,?>> queue = new LinkedList<>();
		
		public MyIterator(TreeNode<?,?> root) {
			queue.add(root);
		}
		
		public boolean hasNext() {
			return !queue.isEmpty();
		}
		
		public TreeNode<?,?> next() {
			final var element = queue.removeFirst();
			for (final TreeNode<?, ?> child : element.getChildren()) {
				queue.add(child);
			}
			return element;
		}

	}

	void doSomething(TreeNode<?, ?> root) {
		
		var iter = new MyIterator(root);
		
		while (iter.hasNext()) {
			
			final var element = iter.next();
						
			// Do action AAA with the element
			
		}
	}
	

}
