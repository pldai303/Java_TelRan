package homework_l3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class BitOperations {

	
	public static long setBit(long variable, int num)	{
		variable = variable | (1 << num);
		return variable;
	}
	
	public static long getBit(long variable, int num) {
		long bitValue = 0;
		if ( (variable & (1 << num)) > 0 ) {
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
	//Classwork 27.05.2021
	public static int getNumberSetBits(int a) {
		int res = 0;
		for (int i = 0; i < 32; i++) {
			if (((1 << i) & a) != 0) {
				res++;	
			}
		}
		return res;
	}
	//Classwork 27.05.2021
	public static long getMaxLong(long a) {
		var max = 1L;
		while (max > 0) {
			max <<= 1;
		}
		return max - 1;
	}


	public static void main(String[] args) {
		int n = 7, counter = 0;
		long bitStorage = 0;
		System.out.println(n + " random numbers  (from 1 to 49) :");
		while ( counter < n ) {	
			int value = (int) (1 + Math.random() * 49);
			    if ( getBit(bitStorage, value) == 0 ) {
			    	bitStorage = setBit(bitStorage, value);
			    	System.out.print(value + " ");
			    	counter++; 			   
			    	
			    }
		}
	}
	
	

	
	
	
	

}
