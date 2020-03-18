package fr.utbm.gl52.graph.model;

import java.util.Collection;

import fr.utbm.gl52.graph.observer.GraphListener;

/** This interface represents a graph element with data.
 * 
 * @author sgalland
 * @param <D> the type of the data that is stored into the graph.
 */
public interface GraphElement<D> {

	/** Replies the name of the element.
	 *
	 * @return the name.
	 */
	String getName();

	/** Replies the graph in which this element is located.
	 *
	 * @return the graph.
	 */
	Graph<?, ?, D> getGraph();

	/** Add data into this node and not into its children
	 *
	 * @param data the data to be added.
	 */
	void addData(D data);

	/** Remove data from this node and not into its children
	 *
	 * @param data the data to be remove.
	 */
	void removeData(D data);

	/** Replies the unmodifiable list of data stored into this node (but the data from the child nodes).
	 *
	 * @return the data into the node.
	 */
	Collection<D> getData();

	/** Add an observer on the events related to the graph.
	 *
	 * @param observer the observer to add.
	 */
	void addGraphListener(GraphListener observer);

	/** Add an observer on the events related to the graph.
	 *
	 * @param observer the observer to add.
	 */
	void removeGraphListener(GraphListener observer);

}
