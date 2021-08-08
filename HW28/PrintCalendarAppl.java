package telran.time;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public class PrintCalendarAppl {

	private static Locale locale;
	private static int offsetForYear = 8;
	private static int offsetForTitle = 1;
	private static int offsetSize = 1;
	private static int widthColumn;

	private static DayOfWeek[] days;
	private static String defaultDay = "SUNDAY";

	private static DayOfWeek[] replaceDays(DayOfWeek first) {
		DayOfWeek[] allDays = DayOfWeek.values();
		DayOfWeek[] result = new DayOfWeek[allDays.length];
		boolean startCopy = false;
		int count = 0;
		for (int i = 0; i < allDays.length; i++) {
			if (first == allDays[i]) {
				startCopy = true;
			}
			if (startCopy) {
				result[count] = allDays[i];
				count++;
			}
		}
		int j = count;
		for (int i = 0; i < allDays.length - count; i++) {
			result[j] = allDays[i];
			j++;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] yearMonth = null;
		try {
			yearMonth = getYearMonth(args);
			DayOfWeek first;
			if (args.length > 3) {
				first = getFirstDayOfWeek(args[3]);
			} else
				first = getFirstDayOfWeek(defaultDay);
			days = replaceDays(first);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		String languageCode = args.length > 3 ? args[2] : "xx";
		locale = Locale.forLanguageTag(languageCode);
		widthColumn = getWidthColumn() + offsetSize;
		printCalendar(yearMonth[1], yearMonth[0]);

		// start week from any day
		// print week days - change
		// print off set - change

	}

	private static DayOfWeek getFirstDayOfWeek(String firstDay) throws Exception {
		DayOfWeek first;
		try {
			if (firstDay == "") {
				first = DayOfWeek.valueOf(defaultDay);
			} else {
				first = DayOfWeek.valueOf(firstDay);
			}
		} catch (IllegalArgumentException e) {
			throw new Exception(
					"No such day! Allowed days SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY");
		}
		return first;
	}

	private static int[] getYearMonth(String[] args) throws Exception {
		LocalDate current = LocalDate.now();
		int[] res = { current.getYear(), current.getMonthValue() };
		if (args.length > 0) {
			int year = getYear(args[0]);
			res[0] = year;
		}
		if (args.length > 1) {
			int month = getMonth(args[1]);
			res[1] = month;
		}
		return res;
	}

	private static int getMonth(String monthStr) throws Exception {
		int res = 0;
		try {
			res = Integer.parseInt(monthStr);
		} catch (NumberFormatException e) {
			throw new Exception("Month should be a number");
		}
		if (res < 1 || res > 12) {
			throw new Exception("Wrong month number ");
		}
		return res;
	}

	private static int getYear(String strYear) throws Exception {
		int res = 0;
		try {
			res = Integer.parseInt(strYear);
		} catch (NumberFormatException e) {
			throw new Exception("Year should be a number");
		}
		if (res < 0) {
			throw new Exception("Year can't be negative");
		}
		return res;
	}

	private static int getWidthColumn() {

		return DayOfWeek.MONDAY.getDisplayName(TextStyle.SHORT, locale).length();
	}

	private static void printCalendar(int monthNum, int yearNum) {
		printTitle(monthNum, yearNum);
		printWeekDays();
		printOffset(monthNum, yearNum);
		printDates(monthNum, yearNum);
	}

	private static void printDates(int monthNum, int yearNum) {
		int firstColumn = getFirstColumn(monthNum, yearNum);
		int offset = firstColumn - 1;
		int countDaysOfWeek = DayOfWeek.values().length;
		int lastDate = YearMonth.of(yearNum, monthNum).lengthOfMonth();
		int counter = 1;
		for (int i = 1; i <= lastDate; i++) {
			System.out.printf("%" + widthColumn + "d", i);
			if ((counter + offset) % countDaysOfWeek == 0) {
				System.out.println();
			}
			counter++;
		}
	}

	private static void printOffset(int monthNum, int yearNum) {

		int firstColumn = getFirstColumn(monthNum, yearNum);

		var offset = (firstColumn - 1) * widthColumn;

		System.out.print(" ".repeat(offset));
	}

	private static int getFirstColumn(int monthNum, int yearNum) {
		LocalDate firstDate = LocalDate.of(yearNum, monthNum, 1);
		int i;
		for (i = 0; i < days.length; i++) {
			if (days[i] == firstDate.getDayOfWeek()) {
				break;
			}
		}
		return i + 1;// firstDate.getDayOfWeek().getValue();
	}

	private static void printWeekDays() {
		// DayOfWeek[] week = DayOfWeek.values();
		String res = " ".repeat(widthColumn / 2);
		String offset = " ".repeat(offsetForTitle);
		for (DayOfWeek dayOfWeek : days/* week */) {
			res += dayOfWeek.getDisplayName(TextStyle.SHORT, locale) + offset;
		}
		System.out.println(res);

	}

	private static void printTitle(int monthNum, int yearNum) {
		Month month = Month.of(monthNum);
		String monthName = month.getDisplayName(TextStyle.FULL_STANDALONE, locale);
		String offset = " ".repeat(offsetForYear);
		System.out.printf("%s%d, %s\n", offset, yearNum, monthName);

	}

}
