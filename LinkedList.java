package telran.utils;

import java.util.function.Predicate;

public class LinkedList<T> extends AbstractList<T> {

	static private class Node<T> {
		public T obj;
		public Node<T> next; //link for next object;
		public Node<T> prev; //link for prev. object;
		public Node(T obj) {
			this.obj = obj;
		} 
	}
	
	private Node<T> head;
	private Node<T> tail;
	
	

	@Override
	public void add(T obj) {
		Node<T> node =  new Node<T>(obj);
		if (tail != null) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		else {
			head = tail = node;
		}
		
		size++;
		
		
	}

	@Override
	public boolean add(T obj, int index) {
		if (index == size) {
			add(obj);
			return true;
		}
		if (!isValidIndex(index)) {
			return false;
		}
		Node<T> node = new Node<T>(obj);
		if (index == 0) {
			head.prev = node;
			node.next = head;
			head = node;
		}
		else {
			Node<T> rNode = getNodeIndex(index);
			Node<T> lNode = rNode.prev;
			rNode.prev = lNode.next = node;
			node.next = rNode;
			node.prev = lNode;
		}
		size++;
		return true;
	}

	@Override
	public T get(int index) {
		return isValidIndex(index) ? getNodeIndex(index).obj : null;
	}

	
	@Override
	public boolean remove(int index) {
		if (!isValidIndex(index) ) {
			return false;
		}
		removeNode(getNodeIndex(index));
		return true;
	}

	
	private void removeNode(Node<T> node) {
		if (head == tail) {
			head = tail = null;
			size = 0;
			return;
		}
		if (node == head) {
			removeHead();
		} else if (node == tail) {
			removeTail();
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			
		}
		size--;
			
	}

	private void removeTail() {
		tail.prev.next = null;
		tail = tail.prev;
	}

	private void removeHead() {
		head.next.prev = null;
		head = head.next;
		
	}

	@Override
	public boolean remove(T pattern) {
		return remove(indexOf(pattern));
	}

	@Override
	public void addAll(List<T> objects) {
		int size = objects.size();
		
		for (int i=0; i<size; i++) {
		add(objects.get(i));	
		}
	}

	@Override
	public T set(T object, int index) {
		if (!isValidIndex(index)) {
			return null;
		}
		Node<T> prev = getNodeIndex(index);
		T val = prev.obj;
		prev.obj = object;
		return val;
	}

	@Override
	public boolean swap(int index1, int index2) {
		if ( !isValidIndex(index1) || !isValidIndex(index2) || (index1 == index2) ) {
			return false;
		}
		Node<T> el1 = getNodeIndex(index1);
		Node<T> el2 = getNodeIndex(index2);
		T temp = el1.obj;
		el1.obj = el2.obj;
		el2.obj = temp;
		return true;
	}
	

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Node<T> temp = head;
		int prevSize = size;
		while (temp != null) {
			if (predicate.test(temp.obj)) {				
				removeNode(temp);				
			}
			temp = temp.next;
		}
		return prevSize > size;
	}

	@Override
	public void clean() {
		head = tail = null;
		size = 0;
	}
	
	private Node<T> getNodeIndex(int index) {
		return index < size / 2 ? getNodeLtr(index) : getNodeRtr(index)  ;
	}

	private Node<T> getNodeLtr(int index) {
		Node<T> node = head;
 		for (int i=0; i < index; i++) {
			node = node.next;
		}
 		return node;
 		
 			
	}

	private Node<T> getNodeRtr(int index) {
	
 		Node<T> node = tail;
		for (int i = size - 1; i > index; i--) {
			node = node.prev;
		}
		return node;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		var index = 0;
		Node<T> temp = head;
		while (index < size && !predicate.test(temp.obj)) {
			temp = temp.next;
			index++;
		}
		return index < size ? index : -1;	
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		var index = size - 1;
		Node<T> temp = tail;
		while (index >= 0 && !predicate.test(temp.obj)) {
			temp = temp.prev;
			index--;
		}
		return index < size ? index : -1;
	}
	
	


}
