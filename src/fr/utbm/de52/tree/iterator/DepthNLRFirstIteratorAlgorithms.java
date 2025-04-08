package fr.utbm.de52.tree.iterator;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.de52.tree.model.TreeNode;

/** Only for oral explanation
 * 
 * @author sgalland
 * @param <N> the type of the nodes to iterate on.
 * @see "https://en.wikipedia.org/wiki/Tree_traversal"
 */
@SuppressWarnings("all")
class DepthNLRFirstIteratorAlgorithms {

	void depth_NLR_first_iterator_iterative(TreeNode<?, ?> root) { // Arguments => arguments' constructor
		
		// Local variables => attributes
		final Deque<TreeNode<?, ?>> stack = new LinkedList<>();
		
		// Constructors' code
		stack.push(root);
		
		// Condition => stop condition (hasNext)
		while (!stack.isEmpty()) {
			
			// Code of next()
			final var element = stack.pop();
			
			// Code of next()
			for (final var child : element.getChildren()) {
				stack.push(child);
			}
			
			// NLR means: current node should be treated before left branch and right branch

			// Do action AAA with the element
			
		}
	}
	
	/////////////////////////////////////////////////
	
	class MyIterator<D> implements Iterator<TreeNode<?,D>> {
		
		private Deque<TreeNode<?,D>> stack = new LinkedList<>();
		
		public MyIterator(TreeNode<?,D> root) {
			stack.push(root);
		}
		
		public boolean hasNext() {
			return !stack.isEmpty();
		}
		
		public TreeNode<?,D> next() {
			final var element = stack.pop();
			for (final var child : element.getChildren()) {
				stack.add(child);
			}
			return element;
		}

	}

	void doSomething(TreeNode<?, ?> root) {
		final var iter = new MyIterator(root);
		
		while (iter.hasNext()) {
			
			final var element = iter.next();
						
			// Do action AAA with the element
			
		}
	}
	
	class DataIterator<D> implements Iterator<D> {
		
		private final Iterator<? extends TreeNode<?, D>> it;
		
		private final List<D> foundDataToReturn = new ArrayList<>();
		
		public DataIterator(Iterator<? extends TreeNode<?,D>> it) {
			this.it = it;
			fillDataCol();
		}
		
		public boolean hasNext() {
			return !foundDataToReturn.isEmpty();
		}
		
		public D next() {
			final var dt = foundDataToReturn.remove(0);
			fillDataCol();
			return dt;
		}

		private void fillDataCol() {
			while (foundDataToReturn.isEmpty() && this.it.hasNext()) {
				foundDataToReturn.addAll(it.next().getData());
			}
		}

	}

	void doSomething2(TreeNode<?, Integer> root) {
		
		Iterator<TreeNode<?,Integer>> nodeIter = new MyIterator(root);
		Iterator<Integer> dataIter = new DataIterator(nodeIter);
		
		while (dataIter.hasNext()) {
			final Integer dt = dataIter.next();
			// Do action with dt
		}
	}

	class PositiveIntegerIterator implements Iterator<Integer> {
		
		private final Iterator<Integer> it;
		
		private Integer foundDataToReturn;
		
		public PositiveIntegerIterator(Iterator<Integer> it) {
			this.it = it;
			findData();
		}
		
		public boolean hasNext() {
			return foundDataToReturn != null;
		}
		
		public Integer next() {
			Integer dt = foundDataToReturn;
			findData();
			return dt;
		}

		private void findData() {
			while (foundDataToReturn == null && this.it.hasNext()) {
				final Integer dt = it.next();
				if (dt.compareTo(0) > 0) {
					foundDataToReturn = dt;
				}
			}
		}

	}

	void doSomething3(TreeNode<?, Integer> root) {
		
		Iterator<TreeNode<?,Integer>> nodeIter = new MyIterator(root);
		Iterator<Integer> dataIter = new DataIterator(nodeIter);
		Iterator<Integer> positiveIntegerIter = new PositiveIntegerIterator(dataIter);
		
		while (positiveIntegerIter.hasNext()) {
			final Integer dt = positiveIntegerIter.next();
			// Do action with dt
		}
	}

}
