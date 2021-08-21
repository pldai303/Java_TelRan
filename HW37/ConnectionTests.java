import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ConnectionTests {
	
	private static final int CONNECTION_COUNT = 5;
	ConnectionPoolImpl connPool;
	Connection[] connArr;
	
	@BeforeEach
	void setUp() throws Exception {
		 connPool = new ConnectionPoolImpl();
		 connArr = new Connection[CONNECTION_COUNT];
		 int ipVal = 1;
		 for (int i = 0; i < 5; i++) {
		 connArr[i] = new Connection(10000 + i, "192.168.1." + ipVal, 80 + i);
		 ipVal++;
		 }
	}

	@Test
	void addConnectionsTest() {
		for (int i=0; i<CONNECTION_COUNT; i++) {
		connPool.add(connArr[i]);
		}
		try {
			Connection testConn = new Connection(10003, "192.168.1.4",83);
			assertEquals(true, testConn.equals(connPool.get(10003)));
			assertEquals("No such connection in connection pool", connPool.get(10008));
			} catch (Exception e) {
				
			}
	}
}
