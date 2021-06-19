package telran.utils;

import java.util.function.Predicate;

public class DividerNumbersPredicate implements Predicate<Integer> {

	int divider;

	public DividerNumbersPredicate(int devider) {
	this.divider = devider;
	}

	@Override
	public boolean test(Integer t) {
		return t % divider == 0;
	}
}
