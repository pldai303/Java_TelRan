package telran.range;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {
	private static final int N_COUNT = 9;
	int[] arrOfPair = new int[] { 2, 4, 6, 8, 10 };
	int[] arrOfImpair = new int[] { 1, 3, 5, 7, 9 };
	int[] arrOfAll = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] maxArrOfPair = new int[] { 2147483638, 2147483640, 2147483642, 2147483644, 2147483646 };
	int[] maxArrOfAll = new int[] { 2147483638, 2147483639, 2147483640, 2147483641, 2147483642, 2147483643, 2147483644,
			2147483645, 2147483646, 2147483647 };
	int[] maxArrOfImpair = new int[] { 2147483639, 2147483641, 2147483643, 2147483645, 2147483647 };
	int[] sqrtArr = new int[] { 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144 };

	int range1 = 1;
	int range2 = 10;
	int maxIntTest1 = Integer.MAX_VALUE - N_COUNT;
	int maxIntTest2 = Integer.MAX_VALUE;

	Range obj = null;

	@BeforeEach
	void setUp() throws Exception {
		obj = new Range(range1, range2);
	}

	@Test
	void testPair() throws Exception {
		obj.setPredicate(n -> n % 2 == 0);
		assertTrue(checkArray(obj, arrOfPair, "Pair numbers: "));
		obj = new Range(maxIntTest1, maxIntTest2);
		obj.setPredicate(n -> n % 2 == 0);
		assertTrue(checkArray(obj, maxArrOfPair, "Max. pair numbers: "));

	}

	@Test
	void testImpair() {
		obj.setPredicate(n -> n % 2 != 0);
		assertTrue(checkArray(obj, arrOfImpair, "Impair numbers: "));
		obj = new Range(maxIntTest1, maxIntTest2);
		obj.setPredicate(n -> n % 2 != 0);
		assertTrue(checkArray(obj, maxArrOfImpair, "Max. impair numbers: "));

	}

	@Test
	void testNoPredicate() {
		obj.setPredicate();
		assertTrue(checkArray(obj, arrOfAll, "No predicate (all): "));
		obj = new Range(maxIntTest1, maxIntTest2);
		obj.setPredicate();
		assertTrue(checkArray(obj, maxArrOfAll, "No predicate (max. all): "));

	}

	@Test
	void testIfSQRT() {
		obj = new Range(1, 150);
		obj.setPredicate(n -> Math.sqrt(n) % 1 == 0);
		assertTrue(checkArray(obj, sqrtArr, "Sqrt: "));

	}

	public boolean checkArray(Range obj, int[] array, String msg) {
		boolean res = false;
		int i = 0;
		for (Integer num : obj) {
			if (num == null) {
				continue;
			} else {
				if (array[i] == num) {
					i++;
					res = true;
					msg = msg + num + "; ";
				} else {
					res = false;
					msg = "Test failed!";
					break;
				}
			}
		}
		System.out.println(msg);
		System.out.println();
		return res;
	}

	public void output(Range obj, String name) {
		System.out.println(name);
		for (Integer num : obj) {
			if (num == null) {
				continue;
			} else
				System.out.print(num + "; ");
		}
		System.out.println("");
	}

}
