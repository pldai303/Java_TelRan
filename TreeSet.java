package telran.utils;



import java.util.Comparator;
import java.util.Iterator;

public class TreeSet<T> implements Set<T> {

	private class TreeIterator implements Iterator<T> {
		private Node<T> next;
		private Node<T> current = null;
		
		public TreeIterator(Node<T> root) {
			next = root;
			
			if (next == null) {
				return;
			}
			while (next.left != null) {
				next = next.left;
			}
		}
		@Override
		public boolean hasNext() {
			return next != null;
		}
		
	
		@Override
		public T next() {
			if (!hasNext()) return null;
			current = next;
			if (next.right != null) {
				next = next.right;
				while (next.left != null)
					next = next.left;
				return current.obj;
			}
			while (true) {
				if(next.parent == null) {
	                next = null;
	                return current.obj;
	            }
	            if(next.parent.left == next) {
	                next = next.parent;
	               return current.obj;
	            }
	            next = next.parent;
			}
		}
		
		@Override 
		public void remove() {
			removeByType(current);
			
		}
	}
	
	private static class Node<T> {
		T obj;
		Node<T> left;
		Node<T> right;
		Node<T> parent;
		public Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> root;
	private int size;
	private Comparator<T> comp;

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this ( (Comparator<T>)Comparator.naturalOrder());
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeIterator(root);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean add(T obj) {
		if (root == null) {
			root = new Node<>(obj);
		} else {
			Node<T> parent = getParent(obj);
			if (parent == null) {
				return false;
			}
			Node<T> node = new Node<>(obj);
			if (comp.compare(obj, parent.obj) > 0) {
				parent.right = node;
				
			} else {
				parent.left = node;
				
			}
			node.parent = parent;
		}
		size++;
		return true;
	}

	private Node<T> getParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		while (current != null) {
			parent = current;
			int compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				return null;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}
	
	private boolean removeLeaf(Node<T> current) {
		if (comp.compare(current.obj, current.parent.obj) < 0) 
			current.parent.left = null;
			else
			current.parent.right = null;	
			
			current = null;
			return true;
	}
	
	private boolean removeBranche(Node<T> current) {
		if (comp.compare(current.obj, current.parent.obj) < 0 ) {
			if (current.left != null) {
				current.parent.left = current.left;
				current.left.parent = current.parent;
				}
			else {
				current.parent.left = current.right;
				current.right.parent = current.parent;
			}
				
		}
		else {
			if (current.right != null) {
				current.parent.right = current.right;
				current.right.parent = current.parent;
			}
			else {
				current.parent.right = current.left;
				current.left.parent = current.parent;
			}
		}
		current = null;
		return true;
	}
	
	private boolean removeJunction(Node<T> current) {
		Node<T> temp = current;
		temp = temp.right;
		while (true) {
			if (temp.left != null)
				temp = temp.left;
				else {
					current.obj = temp.obj;
					temp.parent.left = null;
					break;
				}
		}
		return true;
	}
	
	private boolean removeByType(Node<T> current) {
		boolean res = false;
		
		if (current.parent == null) {
			size--;
			current = null;
			return true;
		} 

		switch (objectType(current)){
		case 0: //removing element with no child
			res = removeLeaf(current);
		break;
		case 1: //removing element with one child;
			res = removeBranche(current);
		break;
		case 2: //removing junction 
			res = removeJunction(current);
		break;
		}
		size--;
		return res;
	}

	@Override
	public boolean remove(T pattern) {
		//printTree();
		boolean res = false;
		int tempSize = size;
		Node<T> current = root;
		while (current != null) {
			int compRes = comp.compare(pattern, current.obj);
			if (compRes == 0) {
				removeByType(current);
			 	break;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		if (tempSize > size) {
			
			res = true;
		}
		return res;
	}
	
	private int objectType(Node<T> node) {
		int result = -1;
		if (node == root) {
			clean();
		}
		
		if ((node.left == null) && (node.right == null)) {
			result = 0;
		}
		else {
			if ((node.left == null) || (node.right == null)) {
				result = 1;
			} else {
				result = 2;
			}
		}
		return result;
	}

	@Override
	public boolean contains(T pattern) {
		return root != null && getParent(pattern) == null;
	}

	@Override
	public void clean() {
		root = null;
		size = 0;
	}
	//-----------------------------------------
	public int checkObject(T obj) {
		int res = -1;
		Node<T> current = root;
		while (current != null) {
			int compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
			 	res = objectType(current);
			 	String str = "";
			 	switch (res){
				case 0: 
					str = "No children";
				break;
				case 1: 
					str = "One child";
				break;
				case 2: 
					str = "Junction";
				break;
				}
			 	System.out.println( obj + " - " + str);
			 	break;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return res;
	}
	
	public void printTree(){
		Iterator<T> it = new TreeIterator(root);
		while (it.hasNext()) {
			T current = it.next();
			System.out.println(current);
			
		}
			
	}
	
	
	

}
