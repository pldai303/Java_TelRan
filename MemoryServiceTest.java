package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryServiceTest {
	byte ar[];

	@Test
	void test() {
		int maxMemory = MemoryService.MaxAvailableMemory();
		ar = null;
		try {
			ar = new byte[maxMemory + 1];
			fail("There should be exception! Max Memory - " + maxMemory);
		} catch (OutOfMemoryError e) {
			System.out.println(maxMemory);
			// 524,288,000
		}
	}

}
