package telran.utils;

public class DeviderRule implements Rule {
	private int devider;

	public DeviderRule(int devider) {
		this.devider = devider;
	}

	private int nearestFromRight(int number, int max) {
		int right_count = 0;
		for (int i = number; i <= max; i++) {
			if ((number % devider) == 0) {
				break;
			} else {
				number++;
				right_count++;
			}
		}
		if (number > max) {
			right_count = 0;
		}
		return right_count;
	}

	private int nearestFromLeft(int number, int min) {
		int left_count = 0;
		for (int i = number; i >= min; i--) {
			if ((number % devider) == 0) {
				break;
			} else {
				number--;
				left_count++;

			}
		}
		if (number < min) {
			left_count = 0;
		}

		return left_count;
	}

	private int returnDelta(int number, int min, int max) {
		int delta = 0;
		int right_count = nearestFromRight(number, max);
		int left_count = nearestFromLeft(number, min);
		if (left_count <= right_count) {
			delta = -left_count;
		} else {
			delta = right_count;
		}
		if (number == max) {
			delta = -left_count;
		}
		return delta;
	}

	@Override
	public void check(int number, int min, int max) throws NoRuleMatchException {
		if ((number % devider) != 0) {
			int delta = returnDelta(number, min, max);
			throw new NoRuleMatchException(delta);
		}
	}

}
