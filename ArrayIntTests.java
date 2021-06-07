package homework_l3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import homework_l3.ArrayInt;
//import sun.nio.fs.ExtendedOptions.InternalOption;

class ArrayIntTests {

	private static final int N_NUMBERS = 1_000_000;

	@Test
	void testMyArray() {
		/*assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 16 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 77 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 100 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 199 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 170 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 85 )));
		assertEquals(true, ArrayInt.isSum2(BitOperations.arr, (short)( 88 )));*/
		//homework lesson 6
		//sort test
		int[] sortTest = new int[ArrayInt.arrSortTest.length];
		System.arraycopy(ArrayInt.arrSortTest, 0, sortTest, 0, ArrayInt.arrSortTest.length);
		Arrays.sort(sortTest);
		int[] myArray = new int[ArrayInt.arrSortTest.length];
		System.arraycopy(ArrayInt.arrSortTest, 0, myArray, 0, ArrayInt.arrSortTest.length);
		ArrayInt.sort(myArray);
		assertArrayEquals(sortTest, myArray);
		//add number sorted
		int[] arrTestEmpty = new int[1];
		arrTestEmpty[0] = 10;
		assertArrayEquals(arrTestEmpty, ArrayInt.addNumberSorted(ArrayInt.arrEmpty, 10));
		int[] arrExampleInsert = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] arrResultInsert = {0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9 };
		assertArrayEquals(arrResultInsert, ArrayInt.addNumberSorted(arrExampleInsert, 5));
		//remove number sorted
		int[] arrExampleRemove = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] arrResultRemove = {0, 1, 2, 3, 4, 6, 7, 8, 9 };
		assertArrayEquals(arrResultRemove, ArrayInt.removeNumber(arrExampleRemove, 6));
		//test binarycount
		assertEquals(1, ArrayInt.binaryCount(ArrayInt.arr, 1));// 1 element 1 in arr
		assertEquals(2, ArrayInt.binaryCount(ArrayInt.arr, 2));// 2 elements 2 in arr
		assertEquals(3, ArrayInt.binaryCount(ArrayInt.arr, 3));// 3 elements 3 in arr
		assertEquals(4, ArrayInt.binaryCount(ArrayInt.arr, 4));// 4 elements 4 in arr
		assertEquals(5, ArrayInt.binaryCount(ArrayInt.arr, 5));// 5 elements 5 in arr
		assertEquals(6, ArrayInt.binaryCount(ArrayInt.arr, 6));// 6 elements 6 in arr
		assertEquals(7, ArrayInt.binaryCount(ArrayInt.arr, 7));// 7 elements 7 in arr
		assertEquals(10, ArrayInt.binaryCount(ArrayInt.arrEqualElements, 5)); // without ( if a[0] == num and a[n] == num) 
		assertEquals(-1, ArrayInt.binaryCount(ArrayInt.arrEqualElements, 100));//no element in array or array is empty 
		assertEquals(-1, ArrayInt.binaryCount(ArrayInt.arrEmpty, 0));//no element in array or array is empty
 	}
	
		
		
	
	
	@Test
	void sortTest() {
		short ar[] = getShortArray(N_NUMBERS);
		//ArrayInt.sort(ar);
		java.util.Arrays.sort(ar);
		int lim = N_NUMBERS - 1;
		for (int i = 0; i < lim; i++) {
			assertTrue(ar[i] <= ar[i+1]);
		}
		
	}
	
	private short[] getShortArray(int nNumbers)	{
		short res[] = new short[nNumbers];
		for (int i = 0; i < nNumbers; i++ ) {
			res[i] = (short) (Math.random() * Short.MAX_VALUE);
	 	}
		return res;
	}

}
