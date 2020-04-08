package fr.utbm.gl52.tree.iterator;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;

import fr.utbm.gl52.tree.model.TreeNode;

/** Only for oral explanation
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 * @see "https://en.wikipedia.org/wiki/Tree_traversal"
 */
@SuppressWarnings("all")
class IteratorAlgorithms {

	void breadthFirstIterator_iterative(
			TreeNode<?, ?> root,
			Function<TreeNode<?,?>, Void> callback) {
		
		final LinkedList<TreeNode<?, ?>> queue = new LinkedList<>();
		
		queue.add(root);
		
		while (!queue.isEmpty()) {
			
			final TreeNode<?, ?> element = queue.removeFirst();
			
			for (final TreeNode<?, ?> child : element.getChildren()) {
				queue.add(child);
			}
			
			callback.apply(element);
			
		}
	}
	
	/////////////////////////////////////////////////
	
	void iterator_init(LinkedList<TreeNode<?,?>> queue, TreeNode<?,?> root) {
		queue.add(root);
	}
	
	boolean iterator_hasNext(LinkedList<TreeNode<?,?>> queue) {
		return !queue.isEmpty();
	}
	
	TreeNode<?,?> iterator_next(LinkedList<TreeNode<?,?>> queue) {
		TreeNode<?,?> element = queue.removeFirst();
		for (final TreeNode<?, ?> child : element.getChildren()) {
			queue.add(child);
		}
		return element;
	}

	void breadthFirstIterator_iterator(
			TreeNode<?, ?> root,
			Function<TreeNode<?,?>, Void> callback) {
		
		final LinkedList<TreeNode<?, ?>> queue = new LinkedList<>();
		
		iterator_init(queue, root);
		
		while (iterator_hasNext(queue)) {
			
			final TreeNode<?, ?> element = iterator_next(queue);
						
			callback.apply(element);
			
		}
	}
	

}
