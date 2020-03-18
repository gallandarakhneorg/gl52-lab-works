package fr.utbm.gl52.tp2;

import java.util.Random;

import fr.utbm.gl52.tree.model.bst.BinarySearchTree;
import fr.utbm.gl52.tree.observer.TreeDataEvent;
import fr.utbm.gl52.tree.observer.TreeEvent;
import fr.utbm.gl52.tree.observer.TreeListener;

/** Main program for TP2 of GL52.
 * 
 * @author sgalland
 */
public class Main {

	/** Main program.
	 *
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		final Random random = new Random();
	
		final BinarySearchTree<Integer> tree = new BinarySearchTree<>();

		final TreeListener observer = new TreeListener() {

			@Override
			public void dataAdded(TreeDataEvent event) {
				System.out.println("DATA-ADDED: " + event.getData()); //$NON-NLS-1$
			}

			@Override
			public void dataRemoved(TreeDataEvent event) {
				System.out.println("DATA-REMOVED: " + event.getData()); //$NON-NLS-1$
			}

			@Override
			public void childCreated(TreeEvent event) {
				System.out.println("CHID-ADDED: " + event.getSource()); //$NON-NLS-1$
			}
	
		};
		tree.addTreeListener(observer);
		
		final int nb = random.nextInt(90) + 10;
		for (int i = 0; i < nb; ++i) {
			tree.insertData(Integer.valueOf(random.nextInt()));
		}

		System.out.println(tree.toString());
	}

}
