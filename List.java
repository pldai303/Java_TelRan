
package telran.utils;


import java.util.Comparator;

public interface List<T>  {

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
	 * 
	 * @param pattern
	 * @return index of first occurrence for an object in array otherwise return -1
	 */
	int indexOf(T pattern);
	
	int lastIndexOf(T pattern);
	
	/**
	 * removes first occurred object equaled to a given pattern
	 * @param pattern
	 * @return true if removed otherwise false
	 * (here there is some challenge, try to understand it)
	 */
	boolean remove(T pattern);
	
	/** 
	 * adds all objects 
	 * @param objects
	 */
	void addAll(List<T> objects);
	
	/**
	 * removes all objects equaled to the given patterns
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	boolean removeAll (List<T> patterns);
	
	/**
	 * removes all objects not equaled to the given patterns
	 * @param patterns
	 * @return true if at least one object has been removed
	 */
	boolean retainAll (List<T> patterns);
	
	/**
	 * sets new reference to an object at existing index
	 * @param object
	 * @param index
	 * @return reference to an old object at the index or null in the case of wrong index
	 */
	T set(T object, int index);
	
	/**
	 * swaps objects at the given indexes
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
		for (int i=1; i < size; i++) {
			T current = list.get(i);
			if ( comp.compare(max, current) < 0) {
				max = current;
			}
		}
		return max;
	} 
	
	@SuppressWarnings("unchecked")
	static  <T> T max(List<T> list) {
		return max(list, (Comparator<T>)Comparator.naturalOrder());
	}
	
	static <T> T min(List<T> list, Comparator<T> comp) {
		return max(list, comp.reversed());
	}
	
	default void sort() {
		sort((Comparator<T>) Comparator.naturalOrder());
	}
	
	default void sort(Comparator<T> comparator) {
		int size = size();
		for (int i = 0; i < size; i++) {
			for (int j=i+1; j < size; j++) {
				if (comparator.compare(get(i), get(j)) > 0)
					swap(j, i);
				}
			}
	}
}
