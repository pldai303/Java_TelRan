import static org.junit.Assume.assumeNoException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class ConnectionPoolImpl implements ConnectionsPool {

	private int size = 0;
	private static final int LIMIT = 5;

	private static class Node {
		public Connection connection;
		public Node next;
		public Node prev;

		public Node(Connection connection) {
			this.connection = connection;
		}
	}

//	private static Node head;
//	private static Node tail;
	private HashMap<Integer, Node> poolMap = new HashMap<>();
	private ConnectionsList connectionsList = new ConnectionsList();

	private class ConnectionPoolIterator implements Iterator<Connection> {
		Node current = connectionsList.head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Connection next() {
			Connection res = current.connection;
			current = current.next;
			return res;
		}
	}

	private static class ConnectionsList {
		Node head;
		Node tail;

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

	public void add(Connection connection) {
		int connId = connection.getId();
		Node node = null;
		if (poolMap.containsKey(connId)) {
			node = poolMap.get(connId);
			if (!connectionsList.isFirst(node)) {
				connectionsList.moveNode(node);
			}
		} else {
			if (size == LIMIT) {
				poolMap.remove(connectionsList.tail.connection.getId());
				connectionsList.removeTail();
				size--;
			}
			node = connectionsList.addNode(connection);
			poolMap.put(connId, node);
			size++;
		}
	}

	@Override
	public Connection get(int id) throws Exception {
		Connection res = null;
		try {
			res = poolMap.get(id).connection;
		} catch (Exception e) {
			throw new Exception("No such connection");
		}
		return res;
	}

	public int getSize() {
		return size;
	}

}
