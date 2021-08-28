package telran.view;

import java.util.function.Function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public interface InputOutput {

	String readString(String prompt);

	void writeObject(Object obj);

	default void writeObjectLine(Object obj) {
		writeObject(obj + "\n");
	}

	default Integer readInt(String prompt) {
		Integer res = 0;
		res = readObject(prompt, "Wrong integer value, please enter integer one more time", n -> {
			return Integer.parseInt(n);
		});
		return res;

	}

	default Integer readInt(String prompt, int min, int max) throws Exception {
		Integer res = 0;
		if (min < max) {
			res = readObject(prompt, "Wrong integer value, please enter integer between " + min + " and " + max, n -> {
				Integer temp = Integer.parseInt(n);
				if ((min < temp) && (temp < max)) {
					return temp;
				} else {
					throw new IllegalArgumentException();
				}
			});
		} else {
			throw new Exception("Min should be less than max ");
		}
		return res;

	}

	default String readStringOption(String prompt, Set<String> options) throws Exception {
		String res = "";
		res = readObject(prompt, "No such value in set", n -> {
			if (options.contains(n)) {
				return n;
			} else {
				throw new IllegalArgumentException();

			}

		});
		return res;
	}

	default Long readLong(String prompt) {
		Long res = 0L;
		res = readObject(prompt, "Wrong long value, please enter long one more time", n -> {
			return Long.parseLong(n);
		});
		return res;
	}

	default LocalDate readDate(String prompt) {
		LocalDate res = null;
		res = readObject(prompt, "Wrong LocalDate value, please enter LocalDate one more time", n -> {
			return LocalDate.parse(n);
		});
		return res;
	}

	default LocalDate readDate(String prompt, String formatPattern) {
		LocalDate res = null;
		res = readObject(prompt, "Wrong LocalDate value, please enter LocalDate one more time", n -> {
			return LocalDate.parse(n, DateTimeFormatter.ofPattern(formatPattern));
		});
		return res;
	}

	default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {

		while (true) {
			try {
				String string = readString(prompt);
				R res = mapper.apply(string);
				return res;
			} catch (Exception e) {
				writeObjectLine(errorPrompt);
			}
		}
	}

}
