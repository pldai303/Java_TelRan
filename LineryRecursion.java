
public class LineryRecursion {

	public static long factorial(int a) {
		if (a == 0) {
			return 1;
		}
		return a * factorial(a - 1);
	}

	public static long sum(int ar[]) {
		return sumR(0, ar);
	}

	private static int sumR(int index, int[] ar) {
		if (index >= ar.length) {
			return 0;
		}
		return ar[index] + sumR(index + 1, ar);
	}

	private static int powMultiply(int a, int n) {
		if (n == 0) {
			return 1;
		}
		int k = multiply(a, powMultiply(a, --n));
		return k;
	}

	public static int pow(int a, int n) {
		boolean changeSign = false;
		if (a < 0) {
			a = changeSign(a);
			changeSign = true;
		}
		int res = powMultiply(a, n);

		if (((n & 1) == 1) && changeSign) {
			res = changeSign(res);
		}
		return res;
	}

	private static int changeSign(int x) {
		x = ~x + 1;
		return x;
	}

	private static int multiply(int val1, int val2) {
		if (val2 == 0) {
			return 0;
		}
		return val1 + multiply(val1, --val2);
	}

	public static int square(int x) {
		if (x < 0) {
			x = ~x + 1;
		}
		if (x == 0) {
			return 0;
		}
		return x + square(x - 1) + x - 1;
	}

	public static boolean isSubstring(String str, String substr) {
		boolean res = false;
		int startPos = 0;
		int strLength = str.length();
		int substrLength = substr.length();
		if (substrLength > strLength) {
			return false;
		}
		return isSubstringInStr(str, substr, startPos);
	}

	private static boolean isSubstringInStr(String str, String substr, int startPos) {
		boolean res = false;

		if (startPos == str.length()) {
			return res;
		}
		int countMatch = 0;
		int indexInSub = 0;
		int mainPos = 0;
		if (startPos < str.length()) {
			if (checkChar(str, mainPos, substr, indexInSub, countMatch)) {
				res = true;
			} else
				res = false;

		}
		return res;
	}

	private static boolean checkChar(String str, int mainPos, String subStr, int index, int countMatch) {
		if (mainPos == str.length()) {
			return false;
		}
		if (str.charAt(mainPos) == subStr.charAt(index)) {
			++countMatch;
			++index;
			if (countMatch == subStr.length()) {
				return true;
			}
		} else {
			index = 0;
			countMatch = 0;
		}
		return checkChar(str, ++mainPos, subStr, index, countMatch);
	}

}
