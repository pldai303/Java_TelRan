package homework_l3;


public class ArrayInt {

	public static int indexOf(int ar[], int num) { //Complexity O[N]
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
	public static void sort(short arr[]) {
	for (int i = 0; i<arr.length; i++)
		for (int j=0; j<arr.length; j++)
			if (arr[i] < arr[j]) {
				short temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			} 
	}
	public static void displayArray(short arr[])	{
		System.out.println("Sorted array:");
		for (int i=0; i < arr.length; i++)
			System.out.print("A[" + i + "] = " + arr[i] + "; ");
		System.out.println("");
		System.out.println("Results:");
	}
	public static boolean isSumTemp(short arr[], short sum)
	{
		boolean result = false;
		short firstValue = -1,  secondValue = -1;
		int firstVauleIdx = -1, secondValueIdx = -1;
		
		
		for ( int i = arr.length-1 ; i >= 0; i-- )
		{
			    if ( (arr[i] <= sum) & (secondValue == -1))
				{
					firstValue = arr[i];
					firstVauleIdx = i;
					secondValue = (short)(sum - arr[i]);
				}
				
			    if (i > 0) { 
					if (arr[i] + arr[i-1] == sum){
					firstValue = arr[i];
					firstVauleIdx = i;
					secondValue = arr[i-1];
					secondValueIdx = i-1; 
					System.out.println("A[" + firstVauleIdx + "] = " + firstValue +  "; A[" + secondValueIdx + "] = " + secondValue + ";");
					System.out.println(firstValue +  " + " + secondValue + " = " + sum);
					result = true;
					break;
					}
				}
			    
			    if (arr[i] == secondValue)
				{
					secondValueIdx = i;
					System.out.println("A[" + firstVauleIdx + "] = " + firstValue +  "; A[" + secondValueIdx + "] = " + arr[i] + ";");
					System.out.println(firstValue +  " + " + secondValue + " = " + sum);
					result = true;
					break;
				}
			    
				
		}
		return result; 
	}
	
	public static boolean isSum2(short arr[], short sum) {
	boolean result = false;
	int i = 0; 
	int k = 1;
	short val1 = 0, val2 = 0;
	while (i < arr.length)
	{
		val1 =  arr[i];
		val2 = (short)(sum - val1);
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
}
