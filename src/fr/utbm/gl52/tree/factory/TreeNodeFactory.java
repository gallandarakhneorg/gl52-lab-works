package fr.utbm.gl52.tree.factory;

import fr.utbm.gl52.tree.model.TreeNode;

/** Factory of tree nodes.
 * 
 * @author sgalland
 * @param <N> the type of the node to be created.
 */
public interface TreeNodeFactory<N extends TreeNode<N, ?>> {

	/** Create a root node.
	 *
	 * @return the new node.
	 */
	N createRootNode();

	/** Create a node for the given parent.
	 *
	 * @param parent the parent node, never {@code null}.
	 * @return the new node.
	 */
	N create(N parent);

}
