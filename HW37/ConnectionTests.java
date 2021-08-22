import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ConnectionTests {

	private static final int CONNECTION_COUNT = 3;
	ConnectionPoolImpl connPool;
	Connection[] connArr;

	@BeforeEach
	void setUp() throws Exception {
		connPool = new ConnectionPoolImpl();
		connArr = new Connection[CONNECTION_COUNT];
		for (int i = 0; i < CONNECTION_COUNT; i++) {
			connArr[i] = new Connection(10000 + i, "192.168.1." + i, 80 + i);
		}
		for (int i = 0; i < CONNECTION_COUNT; i++) {
			connPool.add(connArr[i]);
		}
	}

	@Test
	void addConnectionsTest() {
		assertEquals(3, connPool.getSize());
		connPool.add(new Connection(10003, "192.168.1.3", 83));
		connPool.add(new Connection(10004, "192.168.1.4", 84));
		assertEquals(5, connPool.getSize());
		// Limit is 5;
		connPool.add(new Connection(10005, "192.168.1.5", 85));
		assertEquals(5, connPool.getSize());
		// Adding existing connections which should be on a first place
		connPool.add(new Connection(10004, "192.168.1.4", 84));
		connPool.add(new Connection(10001, "192.168.1.1", 81));
	}

	@Test
	void getConnectionsTest() {
		Connection getConn = null;
		try {
			getConn = connPool.get(10002);
		} catch (Exception e) {
		}
		Connection getTestConnection = new Connection(10002, "192.168.1.2", 82);
		assertTrue(getTestConnection.equals(getConn));

		try {
			getConn = connPool.get(10008);
		} catch (Exception e) {
			String message = "No such connection";
			assertTrue(e.getMessage().equals(message));
		}
	}

	@Test
	void iteratorTest() {
		int counter = CONNECTION_COUNT - 1;
		for (Connection con : connPool) {
			assertTrue(con.equals(new Connection(connArr[counter].getId(), connArr[counter].getIpAddress(),
					connArr[counter].getPort())));
			counter--;
		}
	}
}
