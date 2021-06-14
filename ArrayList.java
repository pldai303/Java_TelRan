package telran.utils;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_CAPACITY = 16;

	private T array[];

	int size = 0;

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
		System.arraycopy(array, index, array, index, size - index);
		array[index] = obj;
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		T res = null;
		if (index >= 0 && index < size) {
			res = array[index];
		}
		return res;
	}

	@Override
	public boolean remove(int index) {
		boolean res = false;
		if (index >= 0 && index < size) {
			size--;
			System.arraycopy(array, index + 1, array, index, size - index);
			array[size] = null;
			res = true;
		}

		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		while (index < size && !array[index].equals(pattern)) {
			index++;
		}
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index > 0 && !array[index].equals(pattern)) {
			index--;
		}
		return index;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = remove(indexOf(pattern));
		return res;
	}

	@Override
	public void addAll(List<T> objects) {
		for (int i = 0; i < objects.size(); i++) {
			add(objects.get(i), size);
		}
	}

	@Override
	public boolean removeAll(List<T> patterns) {
		int i = 0;
		int j = 0;
		boolean isRemoved = false;
		while (i < patterns.size()) {
			if (patterns.get(i).equals(array[j])) {
				remove(j);
				isRemoved = true;
			}
			if (j == size) {
				j = 0;
				i++;
			}
			j++;
		}
		return isRemoved;
	}

	@Override
	public boolean retainAll(List<T> patterns) {
		int i = 0;
		boolean res = false;
		while (i <= size-1)
		{
			if ( patterns.indexOf( array[i] ) == -1 ) { 
			remove(i);
			res = true;
			}
			else
			i++; 
		}
		return res;
	}

	@Override
	public T set(T object, int index) {
		if ((index >= 0) & (index <= size))
			array[index] = object;
		return null;
	}

	@Override
	public boolean swap(int index1, int index2) {
		if ((index1 >= 0) && (index1<=size) && (index2 >= 0) && (index2 <= size)) {
		T object = (T) new Object();
		object = array[index2];
		array[index2] = array[index1];
		array[index1] = object;
		return true;
		}
		else
			return false;
	}

}
