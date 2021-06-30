package telran.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T>  extends AbstractList<T>  {

	private static final int DEFAULT_CAPACITY = 16;
	private T array[];
	int size = 0;
	
	private class ArrayListIterator implements Iterator<T> {
		int index = 0;
		@Override
		public boolean hasNext() {
		return index < size;	
		}

		@Override
		public T next() {
		 T current = array[index];
		 index ++;
		 return current;
		}
		
	}
	

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

	@Override
	public boolean remove(T pattern) {
		return remove(indexOf(pattern));
	}

	@Override
	public void addAll(List<T> objects) {
		int size = objects.size();
		Iterator<T> iterator = objects.iterator();
		for (int i=0; i<size; i++)
		{
			if (iterator.hasNext()) {
				add(iterator.next());	
			}
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


	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		while (index < size && !predicate.test(array[index])) {
			index++;
		}
		
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = size - 1;
		while (index >= 0 && !predicate.test(array[index])) {
			index--;
		}
		return index < size ? index : -1;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

}