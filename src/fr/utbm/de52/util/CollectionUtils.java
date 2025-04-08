package fr.utbm.de52.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

/** Provide additional utilities on collections.
 *
 * <p>This class could be found in Guava library.
 * 
 * @author sgalland
 */
public final class CollectionUtils {

	private CollectionUtils() {
		//
	}

	/** Replies a view on the original collection in which all the elements of type {@code T}
	 * are transformed to values of type {@code RT}}.
	 *
	 * @param <T> the type of the data in the original collection.
	 * @param <TT> the type of the data in the collection view.
	 * @param original the original collection to transform.
	 * @param mapper the function to apply to all the elements.
	 * @return the view on the new values.
	 */
	public static <T, RT> Collection<RT> transform(Collection<T> original, Function<T, RT> mapper) {
		return new TransformView<>(original, mapper);
	}

	/** View on the transformed elements from a collection.
	 *
	 * <p>This class could be found in Guava library.
	 * 
	 * @author sgalland
	 * @param <T> the type of the data in the original collection.
	 * @param <TT> the type of the data in the collection view.
	 */
	private static class TransformView<T, RT> implements Collection<RT> {

		private final Collection<T> original;
		
		private final Function<T, RT> mapper;

		/** Constructor.
		 *
		 * @param original the original collection to transform.
		 * @param mapper the function to apply to all the elements.
		 */
		TransformView(Collection<T> original, Function<T, RT> mapper) {
			this.original = original;
			this.mapper = mapper;
		}
		
		@Override
		public Iterator<RT> iterator() {
			return new TransformIterator<>(this.original, this.mapper);
		}

		@Override
		public int size() {
			return this.original.size();
		}

		@Override
		public boolean isEmpty() {
			return this.original.isEmpty();
		}

		@Override
		public boolean contains(Object o) {
			return this.original.contains(o);
		}

		@Override
		public boolean containsAll(Collection<?> c) {
			for (final var element : c) {
				if (!contains(element)) {
					return false;
				}
			}
			return true;
		}

		@Override
		public Object[] toArray() {
			final var size = size();
			return toArray(new Object[size]);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <TT> TT[] toArray(TT[] a) {
			final var size = size();
			var tab = a;
			if (tab == null || tab.length < size) {
				tab = (TT[]) new Object[size];
			}
			final var iterator = iterator();
			var i = 0;
			while (iterator.hasNext() && i < size) {
				final var element = iterator.next();
				tab[i] = (TT) element;
				++i;
			}
			return tab;
		}

		@Override
		public boolean add(RT e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean remove(Object o) {
			return this.original.remove(o);
		}

		@Override
		public boolean addAll(Collection<? extends RT> c) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeAll(Collection<?> c) {
			return this.original.removeAll(c);
		}

		@Override
		public boolean retainAll(Collection<?> c) {
			return this.original.retainAll(c);
		}

		@Override
		public void clear() {
			this.original.clear();
		}
		
	}

	/** Iterator on the transformed elements from a collection.
	 *
	 * <p>This class could be found in Guava library.
	 * 
	 * @author sgalland
	 * @param <T> the type of the data in the collection.
	 */
	private static class TransformIterator<T, RT> implements Iterator<RT> {

		private final Iterator<T> original;
		
		private final Function<T, RT> mapper;

		/** Constructor.
		 *
		 * @param original the original collection to transform.
		 * @param mapper the function to apply to all the elements.
		 */
		TransformIterator(Collection<T> original, Function<T, RT> mapper) {
			this.original = original.iterator();
			this.mapper = mapper;
		}

		@Override
		public boolean hasNext() {
			return this.original.hasNext();
		}

		@Override
		public RT next() {
			return this.mapper.apply(this.original.next());
		}
		
	}

}