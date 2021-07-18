import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LineryRecursionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void pow() {
		assertEquals(243, LineryRecursion.pow(3, 5));
		assertEquals(-243, LineryRecursion.pow(-3, 5));
		assertEquals(1024, LineryRecursion.pow(2, 10));
		assertEquals(1024, LineryRecursion.pow(-2, 10));
		assertEquals(1, LineryRecursion.pow(-2, 0));
		
	}

	@Test
	void sumArr() {
		assertEquals(10, LineryRecursion.sum(new int[] { 1, 2, 3, 4 }));
	}

	@Test
	void square() {
		assertEquals(25, LineryRecursion.square(5));
		assertEquals(81, LineryRecursion.square(9));
		assertEquals(9, LineryRecursion.square(3));
		assertEquals(25, LineryRecursion.square(-5));
		assertEquals(4, LineryRecursion.square(-2));
		assertEquals(0, LineryRecursion.square(0));
	}

	@Test
	public void factorial() {
		assertEquals(24, LineryRecursion.factorial(4));
		assertEquals(120, LineryRecursion.factorial(5));
	}
			
	@Test 
	void isSubstring() {
		assertTrue(LineryRecursion.isSubstring("Hello", "llo"));
		assertTrue(LineryRecursion.isSubstring("Hello", "el"));
		assertTrue(LineryRecursion.isSubstring("Hello", "Hello"));
		assertFalse(LineryRecursion.isSubstring("Hello", "Helo"));
		assertFalse(LineryRecursion.isSubstring("aba", "aa"));
		assertTrue(LineryRecursion.isSubstring("Hello", "lo"));
	}

}
