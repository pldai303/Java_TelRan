import static org.junit.Assume.assumeNoException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ConnectionPoolImpl implements ConnectionsPool {

	private int size = 0;

	private static class Node {
		public Connection connection;
		public Node next;
		public Node prev;

		public Node(Connection connection) {
			this.connection = connection;
		}
	}

	private static Node head;
	private static Node tail;

	private class ConnectionPoolIterator implements Iterator<Connection> {
		Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Connection next() {
			Connection res = current.connection;
			current = current.next;
			System.out.println(res);
			return res;
		}
	}

	private static class ConnectionsList {
		Node addNode(Connection connection) {
			Node node = new Node(connection);
			addFirst(node);
			return node;
		}

		private void addFirst(Node node) {
			if (head == null) {
				head = tail = node;
			} else {
				node.next = head;
				node.prev = null;
				head.prev = node;
				head = node;
			}
		}

		void removeNode(Node node) {
			if (node == tail) {
				removeTail();
			} else {
				node.next.prev = node.prev;
				node.prev.next = node.next;
			}
		}

		boolean isFirst(Node node) {
			return node == head;
		}

		void removeTail() {
			tail.prev.next = null;
			tail = tail.prev;
		}

		void moveNode(Node node) {
			removeNode(node);
			addFirst(node);
		}
	}

	@Override
	public Iterator<Connection> iterator() {
		return new ConnectionPoolIterator();
	}

	@Override
	public void add(Connection connection) {
		Node node = new Node(connection);
		if (tail != null) {
			tail.next = node;
			node.prev = tail;
			tail = node;
		} else {
			head = tail = node;
		}
		size++;
	}

	@Override
	public Connection get(int id) throws Exception {
		Node current = head;
		Connection res = null;
		int size = getSize();
		for (int i = 0; i < size; i++) {
			if (current.connection.getId() == id) {
				res = current.connection;
				break;
			}
			current = current.next;
		}
		if (res == null) {
			throw new NullPointerException("No such connection in connection pool");
		}
		return res;
	}

	public int getSize() {
		return size;
	}

}
