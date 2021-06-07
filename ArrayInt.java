package homework_l3;

import java.util.Arrays;

public class ArrayInt {

	public static int[] arr = { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7 };
	public static int[] arrEqualElements = { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
	public static int[] arrSortTest = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10, 9, 7, 6, 3, 2, 1 };
	public static int[] arrEmpty = {};
	public static int[] arrExample = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	public static int indexOf(int ar[], int num) { 
		int i = 0;
		while ( i < ar.length &&  ar[i] != num ) {
			i++;
		}
		return i == ar.length ? -1 : i;
	}
	public static int indexOfbinary(int ar[], int num) {
		int middle = -1;
		int left = 0;
		int right = ar.length - 1;
		while ( left <= right ) {
		middle = (int) ((left + right) / 2);
		if (ar[middle] == num) {
		return middle;
		}
		if (ar[middle] > num) {
			right = middle - 1;
		}
		else {
			left = middle + 1;
		}
		return middle;
		}
		return middle;
	}
	
	static final int MAX_int = (1 << 15) - 1;
	
	public static boolean isSum2(int[] ar, int sum) {
		
		boolean helper[] = new boolean[sum == MAX_int ? sum : sum + 1];
		int first = 0;
		int second = 0;
		boolean res = false;
		for (int i = 0; i < ar.length; i++) {
			first = ar[i];
			if (first <= sum) {
				second = (int) (sum - first);
				if (helper[second]) {
					res = true;
					break;
				} else {
					helper[first] = true;
				}
			}
		}
		return res;
	}
	public static void sort(int ar[]) {
		int counters[] = new int[MAX_int];
		//index of counters is one number from ar
		//value at index is count of the number from ar
		//for example, counter[100] == 3 means 100 occurs in ar 3 times
		fillCounters(counters, ar);
		fillSortedArray(counters, ar);
	}
	private static void fillSortedArray(int[] counters, int[] ar) {
		int arInd = 0;
		for(int i = 0; i < counters.length && arInd < ar.length; i++) {
			for (int j = 0; j < counters[i]; j++) {
				ar[arInd++] = (int) i;
			}
		}
		
	}
	private static void fillCounters(int[] counters, int[] ar) {
		int index = 0;
		for (int i = 0; i < ar.length; i++) {
			index = ar[i];
			counters[index]++;
		}
	}
	public static int[] addNumber(int ar[], int num) {
		int[] res = Arrays.copyOf(ar, ar.length + 1);
		res[res.length - 1] = num;
		return res;
	}
	public static int[] insertNumber(int ar[], int index, int num) {
		if (index < 0  || index > ar.length) {
			return ar;
		}
		int res[] = new int[ar.length + 1];
		System.arraycopy(ar, 0, res, 0, index);
		res[index] = num;
		System.arraycopy(ar, index, res, index + 1, ar.length - index);
		return res;
	}
	public static void displayArray(int arr[])	{
		for (int i=0; i < arr.length; i++)
			System.out.print("A[" + i + "] = " + arr[i] + "; ");
	}
	//one loop 	
	public static boolean myIsSum2(int arr[], int sum) {
	boolean result = false;
	int i = 0; 
	int k = 1;
	int val1 = 0, val2 = 0;
	while (i < arr.length)
	{
		val1 =  arr[i];
		val2 = (int)(sum - val1);
		if ((arr[k] == val2) & ( i != k ) ) {
			val2 = arr[k];
			System.out.println("Input: " + sum + ". Arr[" + i + "]=" + val1 + "; Arr[" + k + "]=" + val2 + "; (" + val1 + " + " + val2 + " = " + sum + ")");
			result = true;
			break;
		}
		else
			k++;
		if (k == arr.length) {
			k = i;
			i++;
		}
		if (i==arr.length) {
			System.out.println("A pair of values not found!");
			result = false;
		}
	}
	return result;	
	}
	public static int binaryCount(int[] arr, int num)
	{
		int lLimit = 0, rLimit = arr.length-1, result = -1;
		int firstIdx = 0, lastIdx = 0;
		boolean leftFirst =  true;
		boolean rightFirst = false;
		int iterationsCount = 0;
		while (lLimit <= rLimit) {
			iterationsCount++;
			int midPos = ((lLimit + rLimit) / 2);
			if (arr[midPos] == num ) {
				result = midPos;
				if (leftFirst)
				rLimit = midPos - 1;
				if (rightFirst)
				lLimit = midPos + 1;
			}
			else {
				if (num < arr[midPos]) {
					rLimit = midPos - 1;
				}
				else {
					lLimit = midPos + 1;
				}
			}
			
			if (rightFirst) {
				if (lLimit > rLimit)  {
					if ( (firstIdx >= 0) & (lastIdx >= 0) ) {
					//System.out.println("Iterations count: " + iterationsCount);
					lastIdx = result;
					//System.out.println("Interval of " + num + " is from " + firstIdx + " to " + lastIdx);
					result = lastIdx - firstIdx + 1;
					}
					break;
				}
			}
		if (lLimit > rLimit) {
			leftFirst = false;
			lLimit = 0; 
			rLimit = arr.length-1;
			firstIdx = result;
			rightFirst = true;
		}
		}
		return result;  
	}
	
	
	public static int binarySearch(int[] arr, int num) {
		int lLimit = 0, rLimit = arr.length-1, result = -1;
		if (arr.length == 0) {
			return 0;
		}
		if (arr[0] > num) {
			return 0;
		}
		if (arr[arr.length-1] < num) {
			return arr.length;
		}
		while (lLimit <= rLimit) {
			int midPos = ((lLimit + rLimit) / 2);
			if (arr[midPos] == num ) {
				result = midPos;
				rLimit = midPos - 1;
			}
			else {
				if (num < arr[midPos]) {
					rLimit = midPos - 1;
				}
				else {
					lLimit = midPos + 1;
				}
				if ((arr[midPos-1] < num) & (num < arr[midPos+1]))
					result = midPos;
			}
		}	
		return result;  	
	}
	
	
	
	public static int[] addNumberSorted(int ar[], int num)
	{
		int arr[] = new int[ar.length+1];
		int pos = binarySearch(ar, num);
		System.arraycopy(ar, 0, arr, 0, pos);
		arr[pos] = num;
		System.arraycopy(ar, pos, arr, pos+1, ar.length-pos);
		return arr;
		
	}
	
	public static int[] removeNumber(int ar[], int index)
	{
		int arr[] = new int[ar.length-1];
		if (index == 0) { 
			index = 1; 
		}
		if (index > arr.length) {
			index = (int)arr.length;
		}
			
		
		System.arraycopy(ar, 0, arr, 0, index-1);
		System.arraycopy(ar, index, arr, index-1, ar.length-index);
		return arr;
		
		
	}
	//написать тесты для 3 методов и binarysearch
	
	
	
	
}
