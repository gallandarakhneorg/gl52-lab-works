package fr.utbm.gl52.tree.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Function;

import fr.utbm.gl52.tree.model.TreeNode;

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
		final LinkedList<TreeNode<?, ?>> queue = new LinkedList<>();
		
		// Constructors' code
		queue.add(root);
		
		// Condition => stop condition (hasNext)
		while (!queue.isEmpty()) {
			
			// Code of next()
			final TreeNode<?, ?> element = queue.removeFirst();
			
			// Code of next()
			for (final TreeNode<?, ?> child : element.getChildren()) {
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
			TreeNode<?,?> element = queue.removeFirst();
			for (final TreeNode<?, ?> child : element.getChildren()) {
				queue.add(child);
			}
			return element;
		}

	}

	void doSomething(TreeNode<?, ?> root) {
		
		Iterator<TreeNode<?,?>> iter = new MyIterator(root);
		
		while (iter.hasNext()) {
			
			final TreeNode<?, ?> element = iter.next();
						
			// Do action AAA with the element
			
		}
	}
	

}
