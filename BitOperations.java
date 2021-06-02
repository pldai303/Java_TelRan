package homework_l3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Math;
import java.util.Arrays;
import java.util.Random;
import homework_l3.ArrayInt;

public class BitOperations {
	private static final int N_NUMBERS = 7; 
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 49;
	
	public static short[] arr = {0, 77, 2, 20, 1, 30, 8, 100, 7, 60, 27, 18, 99, 8, 81, 89};
	public static short[] sortedArr;
	public static long setBit(long variable, int num)	{
		variable = variable | (1 << num);
		return variable;
	}
	public static long getBit(long variable, int num) {
		long bitValue = 0;
		if ( (variable & (1 << num)) != 0 ) {
			bitValue = 1;
		}
		return bitValue;
	}
	public static long resetBit(long variable, int num) {
		variable = variable & (~(1 << num));
		return variable;
	}
	public static long negativeToPositive(long variable)
	{
		variable = ~variable + 1;
		return variable;
	}
	public static int log2(long n) {
		int res = -1;
		if (n < 0)
		n = negativeToPositive(n);
		while ( n > 0 ) {
			res++ ; 
			n = n >> 1;
		}
		return res;
	}
	public static int getNumberSetBits(int a) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & a) != 0) {
				res++;	
			}
		}
		return res;
	}
	public static long getMaxLong(long a) {
		var max = 1L;
		while (max > 0) {
			max <<= 1;
		}
		return max - 1;
	}
	public static void displayLottoNumbers() {
		long counter = 0;
		long bitStorage = 0;
	    System.out.println(N_NUMBERS + " random numbers  (from 1 to 49) :");
		while ( counter < N_NUMBERS ) {	
			int value = (int) (MIN_NUMBER + Math.random() * MAX_NUMBER);
			    if ( getBit(bitStorage, value) == 0 ) {
			    	bitStorage = setBit(bitStorage, value);
			    	System.out.print(value + " ");
			    	counter++; 			   
			    }
		}
	}
	
	
	public static void main(String[] args) {
		//Task_4;
		//displayLottoNumbers();
		//Task_5;
		/*short sum = 16;
		sortedArr = new short[arr.length]; 
		ArrayInt.sort(arr);
		System.out.println("Sorted array: ");
		ArrayInt.displayArray(arr);
		System.out.println("Results: ");
		boolean isSum = ArrayInt.isSum2(arr, sum);
		if (isSum)
			System.out.println("True. Values are in array!");
		else
			System.out.println("False. Values are not in array!");*/
	}
}
