package homework_l3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import homework_l3.ArrayInt;
//import sun.nio.fs.ExtendedOptions.InternalOption;

class ArrayIntTests {

	@Test
	void testMyArray() {
		ArrayInt.sort(BitOperations.arr);
		short[] test = { 0, 1, 2, 7, 8, 8, 18, 20, 27, 30, 60, 77, 81, 89, 99, 100 }; 
		assertArrayEquals(test, BitOperations.arr);
		ArrayInt.displayArray(BitOperations.arr);
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 16 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 77 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 100 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 199 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 170 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 85 )));
 	}

}
