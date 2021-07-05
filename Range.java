package telran.range;

import java.util.Iterator;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {

	private Predicate<Integer> predicate;
	private int max = 0, min = 0;

	private class RangeIterator implements Iterator<Integer> {
		@Override
		public boolean hasNext() {
			return min++ < max;
		}

		@Override
		public Integer next() {
			if (predicate.test(min)) {
				return min;
			} else
				return null;
		}
	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	public void setPredicate() {
		setPredicate(n -> true);
	}

	public Range(int min, int max) {
		this.max = max;
		this.min = min - 1;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangeIterator();
	}

}
