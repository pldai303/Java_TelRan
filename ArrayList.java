package telran.utils;

import java.util.Arrays;

import java.util.function.Predicate;

public class ArrayList<T>  implements List<T> {

	private static final int DEFAULT_CAPACITY = 16;
	private T array[];
	int size = 0;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public void add(T obj) {
		if (size >= array.length) {
			allocate();
		}
		array[size++] = obj;
	}

	private void allocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}

	@Override
	public boolean add(T obj, int index) {
		if (index < 0 || index > size) {
			return false;
		}
		if (size >= array.length) {
			allocate();
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		T res = null;
		if (isValidIndex(index)) {
			res = array[index];
		}
		return res;
	}

	@Override
	public boolean remove(int index) {
		boolean res = false;
		if (isValidIndex(index)) {
			size--;
			System.arraycopy(array, index + 1, array, index, size - index);
			array[size] = null;
			res = true;
		}
		return res;
	}

	private boolean isValidIndex(int index) {
		return index >= 0 && index < size;
	}

	@Override
	public int size() {

		return size;
	}


	@Override
	public boolean remove(T pattern) {
		return remove(indexOf(pattern));
	}

	@Override
	public void addAll(List<T> objects) {
		int size = objects.size();
		for (int i = 0; i < size; i++) {
			add(objects.get(i));
		}
	}

	@Override
	public T set(T object, int index) {
		T res = null;
		if (isValidIndex(index)) {
			res = array[index];
			array[index] = object;
		}
		return res;
	}

	@Override
	public boolean swap(int index1, int index2) {
		boolean res = false;
		if (isValidIndex(index1) && isValidIndex(index2)) {
			T tmp = array[index1];
			array[index1] = array[index2];
			array[index2] = tmp;
			res = true;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int i = 0;
		int beforeRemoveSize = size();
		while (i < size) {
			if (predicate.test(array[i])) {
				remove(i);
			} else
				i++;
		}
		return beforeRemoveSize > size();
	}

	@Override
	public void clean() {
		int i = 0;
		while (i < size) {
			remove(i);
		}
	}
	/*@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0 && !array[index].equals(pattern)) {
			index--;
		}
		return index >= 0 ? index : -1;
	}*/

	
	/*private void clean(int startIndex, int sizeBefore) {
	for (int i = startIndex; i < sizeBefore; i++) {
		array[i] = null;
	}
	}
	
	private boolean removing(List<T> patterns, boolean isRetain) {
	int sizeBeforeRemoving = size;
	int indexAfterRemoving = 0;
	for (int i = 0; i < sizeBeforeRemoving; i++) {
		T current = array[i];
		if (conditionRemoving(patterns, current, isRetain)) {
			size--;
		} else {
			array[indexAfterRemoving++] = array[i];
		}
	}
	boolean res = sizeBeforeRemoving > size;
	if (res) {
		clean(size, sizeBeforeRemoving);
	}
	return res;
	}

	private boolean conditionRemoving(List<T> patterns, T current, boolean isRetain) {
	boolean res = patterns.indexOf(current) >= 0;
	return isRetain ? !res : res;
	}*/
}