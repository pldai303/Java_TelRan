
package telran.utils;

import java.util.Comparator;

import java.util.function.Predicate;

public interface List<T> {

	/**
	 * adding obj at the end
	 * 
	 * @param obj
	 */
	void add(T obj);

	/**
	 * 
	 * @param obj
	 * @param index [0..size]
	 * @return true if added, otherwise false (wrong index)
	 */
	boolean add(T obj, int index);

	/**
	 * 
	 * @param index [0..size - 1]
	 * @return reference to T object; if a given index id wrong return null
	 */
	T get(int index);

	/**
	 * removes object at a given index
	 * 
	 * @param index [0, size - 1]
	 * @return true if removed, otherwise false (wrong index)
	 */
	boolean remove(int index);

	/**
	 * 
	 * @return number of elements
	 */
	int size();
	/**
	 * removes first occurred object equaled to a given pattern
	 * 
	 * @param pattern
	 * @return true if removed otherwise false (here there is some challenge, try to
	 *         understand it)
	 */
	boolean remove(T pattern);

	/**
	 * adds all objects
	 * 
	 * @param objects
	 */
	void addAll(List<T> objects);

	/**
	 * removes all objects equaled to the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	default boolean removeAll(List<T> patterns) {
		boolean res = false;
		if (this == patterns) {
			clean();
			res = true;
		} else {
			Predicate<T> predicate = temp -> patterns.indexOf( temp ) >= 0;
			res = removeIf(predicate);	
		}
		return res;
	}

	/**
	 * removes all objects not equaled to the given patterns
	 * 
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	default boolean retainAll(List<T> patterns) {
		boolean res = false;
		if (this == patterns) {
			res = true;
		} else {
			Predicate<T> predicate = temp -> patterns.indexOf( temp ) < 0;
			res = removeIf(predicate);	
		}
		return res;
	}

	/**
	 * sets new reference to an object at existing index
	 * 
	 * @param object
	 * @param index
	 * @return reference to an old object at the index or null in the case of wrong
	 *         index
	 */
	T set(T object, int index);

	/**
	 * swaps objects at the given indexes
	 * 
	 * @param index1
	 * @param index2
	 * @return return true if swapped, false in the case of any wrong index
	 */
	boolean swap(int index1, int index2);
	
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	default boolean contains(T pattern) {
		return indexOf(pattern) >= 0;
	}

	static <T> T max(List<T> list, Comparator<T> comp) {
		T max = list.get(0);
		int size = list.size();
		for (int i = 1; i < size; i++) {
			T current = list.get(i);
			if (comp.compare(max, current) < 0) {
				max = current;
			}
		}
		return max;
	}

	@SuppressWarnings("unchecked")
	static <T> T max(List<T> list) {
		return max(list, (Comparator<T>) Comparator.naturalOrder());
	}

	static <T> T min(List<T> list, Comparator<T> comp) {
		return max(list, comp.reversed());
	}

	@SuppressWarnings("unchecked")
	default void sort() {
		sort((Comparator<T>) Comparator.naturalOrder());
	}

	default void sort(Comparator<T> comparator) {
		int size = size();
		boolean isSorted = true;
		do {
			size--;
			isSorted = true;
			for (int i = 0; i < size; i++) {
				if (comparator.compare(get(i), get(i + 1)) > 0) {
					isSorted = false;
					swap(i, i + 1);
				}
			}

		} while (!isSorted);
	}

	boolean removeIf(Predicate<T> predicate);

	/**
	 * for several equaled objects to leave only one and remove others
	 * 
	 * @return true if at least one object has been removed
	 */
	default boolean removeRepeated() {
		Predicate<T> predicate = temp -> {
			if (lastIndexOf(temp) == indexOf(temp)) {
				return false;
			}
			return true;
		};
		return removeIf(predicate);
	}
	/**
	 * 
	 * @param predicate
	 * @return either index (first object matching or -1)
	 */
	default int indexOf(T pattern) {
		int size = size();
		int index = 0;
		Predicate<T> predicate = n -> n.equals(pattern);
		while (index < size && !predicate.test(get(index))) {
			index++;
		}
		return index < size ? index : -1;
	}
	/**
	 * 
	 * @param predicate
	 * @return either index (first object matching or -1)
	 */
	default int lastIndexOf(T pattern) {
		int size = size();
		int index = size-1;
		Predicate<T> predicate = n -> n.equals(pattern);
		while ( (index < size) && (index > -1) && !predicate.test(get(index)) ) {
			index--;
		}
		return index >= 0 ? index : -1;	
	}
	/**
	 * removing all objects matching a given predicate
	 * 
	 * @param predicate
	 * @return true if at least one object has been removed
	 */
	/**
	 * remove all elements from list
	 */
	void clean();

	default void displayList(List<T> list) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			System.out.println(list.get(i).toString());
		}

	}
	
	
	

}