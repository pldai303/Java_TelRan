package telran.utils;


import java.util.Iterator;





public class HashSet<T> implements Set<T> {
private static final int DEFAULT_TABLE_LENGTH = 16;
private int size;
private LinkedList<T>[] hashTable;
private double factor = 0.75;
@SuppressWarnings("unchecked")
public HashSet(int initialTableLength) {
	hashTable = new LinkedList[initialTableLength];
}
public HashSet() {
	this(DEFAULT_TABLE_LENGTH);
}

		private class HashSetIterator implements Iterator<T> {
			int idx;
			Iterator<T> curIterator;
			T prev;

			public HashSetIterator() {
				curIterator = getNext();
			}
			
			private Iterator<T> getNext(){
				while (idx < hashTable.length) {
					LinkedList<T> list = hashTable[idx];
					if (list == null || !list.iterator().hasNext()) {
						idx++;
					} 
					else {
						return list.iterator();
					}
				}
				return null;
			}
			
			
			@Override
			public boolean hasNext() {
				return curIterator != null;
			}
		
			@Override
			public T next() {
				if (curIterator == null) {
					return null;
				}
				else {
					prev = curIterator.next();
					if (!curIterator.hasNext()) {
						idx++;
						curIterator = getNext();
					}
					return prev;
				}
			}
			
			@Override
			public void remove() {
				HashSet.this.remove(prev);
			}
			
		}
		
	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}
	
	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if (size >= hashTable.length * factor) {
			recreateTable();
		}
		int index = getIndex(obj);
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if (!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			res = true;
			size++;
		}
		return res;

	}

	private void recreateTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for(LinkedList<T> list: hashTable) {
			if (list != null) {
				for (T obj: list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
		
	}
	private int getIndex(T obj) {
		int hashCode = obj.hashCode();
		int res = Math.abs(hashCode) % hashTable.length;
		return res;
	}
	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		LinkedList<T> list = hashTable[getIndex(pattern)];
		if (list.remove(pattern)) {
			res = true;
			size--;
		}
		
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}

	@Override
	public void clean() {
		int cSize = size();
		for (int i=0; i<cSize; i++) {
			hashTable[i] = null;
		}
		size = 0;
	}

}
