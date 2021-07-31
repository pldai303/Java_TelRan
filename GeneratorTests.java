package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeneratorTests {

	@Test
	void dividerCheckTests() {

		var divider = new DeviderRule(3);

		try {
			// 6 div by 3
			divider.check(6, 1, 10);
		} catch (NoRuleMatchException e) {
			System.out.println(e.getDelta());
		}

		try {
			// nearest value div by 3 is 9 (delta -1)
			divider.check(10, 1, 10);
		} catch (NoRuleMatchException e) {
			assertEquals(-1, e.getDelta());
		}

		try {
			divider.check(2, 0, 4);
		} catch (NoRuleMatchException e) {
			assertEquals(1, e.getDelta());
		}

		try {
			divider.check(1, 0, 4);
		} catch (NoRuleMatchException e) {
			assertEquals(-1, e.getDelta());
		}

	}

	@Test
	void generateTests() throws NoRuleMatchException {
		int[] arr = new int[10];
		Generator gn = new Generator();
		arr = gn.generate(10, 1, 10);
		assertEquals(10, arr.length);
		for (int num : arr) {
			assertTrue(num >= 1 && num <= 10);
		}
	}

}
