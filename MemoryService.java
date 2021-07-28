package telran.utils;

import static org.junit.Assert.assertNotNull;

public class MemoryService {
	public static int MaxAvailableMemory() {
		int lLimit = 0, rLimit = Integer.MAX_VALUE;
		int middle;
		byte[] byteArray;
		while (rLimit - lLimit > 1) {
			byteArray = null;
			middle = ((lLimit + rLimit) / 2);
			try {
				byteArray = new byte[middle];
				lLimit = middle; // if memory allocated - move leftLimit to middle
			} catch (OutOfMemoryError e) {
				// if memory not allocated - move rightLimit to middle and catch exception
				rLimit = middle;
			}
		}
		return lLimit;
	}

}
