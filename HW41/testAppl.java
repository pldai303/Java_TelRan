import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import telran.employeers.dto.Employee;
import telran.view.*;

public class testAppl {
	private static InputOutput inputOutput = new ConsoleInputOutput();

	public static void main(String[] args) {

		testEmployee();
		testReadInt();
		testReadIntPrm(10, 20);
		testReadLong();
		testReadDate();
		testReadDateFormat();
		testReadStringOption();

	}

	private static void testEmployee() {
		Employee empl = inputOutput.readObject("Enter employee <id>#<salary>#<dep>#<bod ISO>",
				"Wrong employee, please repeat according to format", str -> {
					String[] fields = str.split("#");
					return new Employee(Long.parseLong(fields[0]), Integer.parseInt(fields[1]),
							LocalDate.parse(fields[3]), fields[2]);
				});
		inputOutput.writeObjectLine(empl);
	}

	private static void testReadInt() {
		Integer intValue = inputOutput.readInt("Enter valid integer value:");
		inputOutput.writeObject(intValue);
	}

	private static void testReadIntPrm(int min, int max) {
		try {
			Integer intValuePrm = inputOutput.readInt(
					inputOutput.readInt("Enter valid integer value between " + min + " and " + max).toString(), min,
					max);

			inputOutput.writeObject(intValuePrm);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void testReadLong() {
		Long longValue = inputOutput.readLong("Enter valid long value:");
		inputOutput.writeObject(longValue);
	}

	private static void testReadDate() {
		LocalDate ldValue = inputOutput.readDate("Enter LocalDate value (yyyy-MM-dd, yyyy-M-d):");
		inputOutput.writeObject(ldValue);
	}

	private static void testReadDateFormat() {
		try {
			LocalDate ldValue = inputOutput.readDate("Enter LocalDate value:",
					inputOutput.readString("Enter date time format"));
			inputOutput.writeObject(ldValue);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void testReadStringOption() {
		Set<String> set = new TreeSet<String>();
		set.add("QA");
		set.add("HR");
		set.add("R&D");
		try {
			String str = inputOutput.readStringOption("Enter string from set " + set.toString(), set);
			inputOutput.writeObject(str);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
