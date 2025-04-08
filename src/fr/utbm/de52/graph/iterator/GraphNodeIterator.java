package fr.utbm.de52.graph.iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import fr.utbm.de52.graph.model.GraphEdge;
import fr.utbm.de52.graph.model.GraphNode;

/** Iterator on the graph nodes.
 * 
 * @author sgalland
 * @param <N> the type of the nodes into the graph.
 * @param <E> the type of the edges into the graph.
 */
public class GraphNodeIterator<N extends GraphNode<N, E, ?>, E extends GraphEdge<N, E, ?>> implements Iterator<N> {

	private final Set<N> repliedNodes = new HashSet<>();

	private final Deque<N> candidates = new ArrayDeque<>();

	private N next;

	/** Constructor.
	 *
	 * @param startNodes the starting nodes.
	 */
	public GraphNodeIterator(Iterable<N> startNodes) {
		N firstNode = null;
		for (final var node : startNodes) {
			if (firstNode == null) {
				firstNode = node;
			}
			if (node.getArrivingEdges().isEmpty()) {
				this.candidates.add(node);
			}
		}
		if (this.candidates.isEmpty() && firstNode != null) {
			this.candidates.add(firstNode);
		}
		searchNextCandidate();
	}

	private void searchNextCandidate() {
		this.next = null;
		while (this.next == null && !this.candidates.isEmpty()) {
			final var n = this.candidates.removeFirst();
			if (this.repliedNodes.add(n)) {
				this.next = n;
				for (final var edge : n.getStartingEdges()) {
					final var target = edge.getEndingNode();
					this.candidates.add(target);
				}
			}
		}
	}
	
	@Override
	public boolean hasNext() {
		return this.next != null;
	}

	@Override
	public N next() {
		final var n = this.next;
		if (n == null) {
			throw new NoSuchElementException();
		}
		searchNextCandidate();
		return n;
	}

}
