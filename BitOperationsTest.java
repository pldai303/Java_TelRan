package homework_l3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import com.sun.org.apache.xpath.internal.operations.Operation;

import homework_l3.BitOperations;

class BitOperationsTest {


	@Test
	void getBitTest() {
		assertEquals(0, BitOperations.getBit(5, 1));	
		assertEquals(1, BitOperations.getBit(6, 1));	
		assertEquals(1, BitOperations.getBit(7, 1));	
	}

	@Test
	void setBitTest() {
		assertEquals(7, BitOperations.setBit(5, 1));	
		assertEquals(7, BitOperations.setBit(6, 0));	
		assertEquals(10, BitOperations.setBit(8, 1));	
	}
	@Test
	void resetBitTest() {
		assertEquals(5, BitOperations.resetBit(7, 1));
		assertEquals(6, BitOperations.resetBit(7, 0));
		assertEquals(8, BitOperations.resetBit(10, 1));
	}
	@Test
	void swapTest() {
		int a = 10;
		int b = 20;
		/*b = a + b;
		a = b - a;
		b = b - a;*/
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		assertEquals(20, a);
		assertEquals(10, b);
	}
	@Test
	void getNumberSetBits() {
		int a = -1;
		int c = 7;
		assertEquals(32, BitOperations.getNumberSetBits(a));
		assertEquals(3, BitOperations.getNumberSetBits(c));		
	}
	@Test
	void getMaxLong() {
		long maxExpected = -1L >>> 1;
		assertEquals(maxExpected, BitOperations.getMaxLong(maxExpected));
	}
	@Test
	void log2Test()
	{
		assertEquals(1, BitOperations.log2(3));
		assertEquals(7, BitOperations.log2(128));
		assertEquals(8, BitOperations.log2(256));
		assertEquals(8, BitOperations.log2(400));
		assertEquals(10, BitOperations.log2(1024));
		assertEquals(10, BitOperations.log2(-1024));
	}
	
	
	
	
	
}
