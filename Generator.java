package telran.utils;

public class Generator {
	private Rule rule;

	public int[] generate(int number, int min, int max) throws NoRuleMatchException {
		if (min >= max) {
			throw new IllegalArgumentException(String.format("min %d can't be greater or equal max %d", min, max));
		}
		int[] array = new int[number];
		int range = max - min + 1;
		int value = 0;
		for (int i = 0; i < number; i++) {
			value = (int) (min + Math.random() * range);
			if (rule != null) {
				try {
					rule.check(value, min, max);
				} catch (NoRuleMatchException e) {
					if (e.getDelta() == 0) {
						value = 0;
					} else {
						value = value + e.getDelta();
					}

				}
			}
			array[i] = value;
		}
		return array;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}
