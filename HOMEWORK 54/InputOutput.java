import java.util.function.Function;

public interface InputOutput {
	String readString(String prompt);

	void writeObject(Object obj);

	default void writeObjectLine(Object obj) {
		writeObject(obj + "\n");

	}

	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		while (true) {
			try {
				String string = readString(prompt);
				if (string == null) {
					break;
				}
				R res = mapper.apply(string);
				return res;
			} catch (Exception e) {
				writeObjectLine(errorPrompt);
			}
		}
		throw new EndOfInputException();
	}

	public default Integer readInt(String prompt) {
		return readObject(prompt, "No integer number", Integer::parseInt);
	}

	public default Integer readInt(String prompt, int min, int max) {
		return readObject(prompt, String.format("no number in [%d - %d]\n", min, max), str -> {
			int num = Integer.parseInt(str);
			if (num < min || num > max) {
				throw new IllegalArgumentException();
			}
			return num;
		});
	}

}
