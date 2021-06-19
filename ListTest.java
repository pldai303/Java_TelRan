package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {

	List<Person> personsMen;
	List<Person> personsWomen;
	List<Person> personToDelete;
	List<Person> checkDeletedPersons;
	List<Person> personToRetain;
	List<Person> checkRetainPerson;
	List<Integer> intList;
	List<Person> testPersons;
	IdComparator testComparator;

	public static boolean listComparer(List<Person> firstList, List<Person> secondList) {
		boolean testResult = false;
		if (firstList.size() == secondList.size()) {
			testResult = true;
			for (int i = 0; i < secondList.size(); i++)
				if (!secondList.get(i).equals(firstList.get(i))) {
					testResult = false;
					break;
				}
		}
		return testResult;
	}

	@BeforeEach
	void setUp() throws Exception {
		personsMen = new ArrayList<Person>();
		personsMen.add(new Person(0, "Alexander Solzhenitsyn", 89));
		personsMen.add(new Person(1, "Fyodor Dostoevsky", 59));
		personsMen.add(new Person(2, "Alexander Pushkin", 37));
		personsMen.add(new Person(3, "Leo Tolstoy", 82));
		personsMen.add(new Person(4, "Mikhail Sholokhov", 78));
		personsMen.add(new Person(5, "Nikolai Gogol", 42));
		personsMen.add(new Person(6, "Anton Chekhov", 44));
		personsMen.add(new Person(7, "Taras Shevchenko", 47));

		personsWomen = new ArrayList<Person>();
		personsWomen.add(new Person(5, "Suzanne Collins", 58));
		personsWomen.add(new Person(4, "Astrid Lindgren", 94));
		personsWomen.add(new Person(3, "Lesya Ukrainka", 42));
		personsWomen.add(new Person(2, "Joanne Rowling", 55));
		personsWomen.add(new Person(1, "Agatha Christie", 85));

		personToDelete = new ArrayList<Person>();
		personToDelete.add(new Person(0, "Alexander Solzhenitsyn", 89));
		personToDelete.add(new Person(7, "Taras Shevchenko", 47));
		personToDelete.add(new Person(5, "Nikolai Gogol", 42));
		checkDeletedPersons = new ArrayList<Person>();
		checkDeletedPersons.add(new Person(1, "Fyodor Dostoevsky", 59));
		checkDeletedPersons.add(new Person(2, "Alexander Pushkin", 37));
		checkDeletedPersons.add(new Person(3, "Leo Tolstoy", 82));
		checkDeletedPersons.add(new Person(4, "Mikhail Sholokhov", 78));
		checkDeletedPersons.add(new Person(6, "Anton Chekhov", 44));

		personToRetain = new ArrayList<Person>();
		personToRetain.add(new Person(7, "Taras Shevchenko", 47));
		personToRetain.add(new Person(0, "Alexander Solzhenitsyn", 89));
		checkRetainPerson = new ArrayList<>();
		checkRetainPerson.add(new Person(0, "Alexander Solzhenitsyn", 89));
		checkRetainPerson.add(new Person(7, "Taras Shevchenko", 47));

		intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(2);
		intList.add(3);
		intList.add(4);
		intList.add(5);
		intList.add(6);
		intList.add(7);
		intList.add(8);
		intList.add(9);
		intList.add(10);
		intList.add(11);
		intList.add(13);
		intList.add(15);

		testPersons = new ArrayList<Person>();
		testPersons.addAll(personsMen);
		testPersons.addAll(personsWomen);
		testComparator = new IdComparator();
	}

	@Test
	void lastIndexOfPredicateTest() {
		assertEquals(9, intList.lastIndexOf(new DividerNumbersPredicate(2)));
		assertEquals(12, intList.lastIndexOf(new DividerNumbersPredicate(3)));
		assertEquals(-1, intList.lastIndexOf(new DividerNumbersPredicate(16)));
	}

	@Test
	void removeIf() {
		intList.removeIf(temp -> temp % 2 == 0); // remove all elements where value divided by 2
		assertEquals(1, intList.get(0));
		assertEquals(3, intList.get(1));
		assertEquals(5, intList.get(2));
		assertEquals(15, intList.get(7));
		personsMen.removeIf(temp -> (temp.id < 8) && (temp.age < 80)); // remove all objects where id < 8 and age < 80
		assertEquals(personsMen.get(0), new Person(0, "Alexander Solzhenitsyn", 89));
		assertEquals(personsMen.get(1), new Person(3, "Leo Tolstoy", 82));
		// personsMen.displayList(personsMen);
	}

	@Test
	void cleanTest() {
		personsMen.clean();
		assertTrue(personsMen.size() == 0);
	}

	@Test
	void removeRepeated() {
		List<Integer> duplicatesList = new ArrayList<Integer>();
		duplicatesList.add(1);
		duplicatesList.add(2);
		duplicatesList.add(1);
		duplicatesList.add(2);
		duplicatesList.add(2);
		duplicatesList.add(3);
		duplicatesList.add(5);
		duplicatesList.add(4);
		duplicatesList.add(1);
		duplicatesList.add(3);
		duplicatesList.add(5);
		duplicatesList.add(4);
		duplicatesList.add(5);
		duplicatesList.removeRepeated();
		personsMen.clean();
		personsMen.add(new Person(0, "Alexander Solzhenitsyn", 89));
		personsMen.add(new Person(1, "Fyodor Dostoevsky", 59));
		personsMen.add(new Person(2, "Alexander Pushkin", 37));
		personsMen.add(new Person(3, "Leo Tolstoy", 82));
		personsMen.add(new Person(4, "Mikhail Sholokhov", 78));
		personsMen.add(new Person(5, "Nikolai Gogol", 42));
		personsMen.add(new Person(6, "Anton Chekhov", 44));
		personsMen.add(new Person(7, "Taras Shevchenko", 47));
		assertFalse(personsMen.removeRepeated());
		personsMen.add(new Person(3, "Leo Tolstoy", 82));
		personsMen.add(new Person(4, "Mikhail Sholokhov", 78));
		personsMen.add(new Person(5, "Nikolai Gogol", 42));
		assertTrue(personsMen.removeRepeated());
		personsMen.sort();
		assertEquals(personsMen.get(0), new Person(0, "Alexander Solzhenitsyn", 89));
		assertEquals(personsMen.get(3), new Person(3, "Leo Tolstoy", 82));
		assertEquals(personsMen.get(7), new Person(7, "Taras Shevchenko", 47));
	}
	
	@Test
	void max() {
		// Max integer in list
		assertEquals(15, List.max(intList));
		// The oldest person din list
		assertEquals(94, List.max(testPersons, testComparator.ageCompare).age);
	}

	@Test
	void min() {
		// Min integer in list
		assertEquals(1, List.min(intList, Comparator.naturalOrder()));
		// The youngest person in list
		assertEquals(37, List.min(testPersons, testComparator.ageCompare).age);
	}

	@Test
	void sort() {
		// Sorting integer values in natural order
		intList.sort();
		assertEquals(1, intList.get(0));
		assertEquals(2, intList.get(1));
		assertEquals(5, intList.get(4));
		// Sorting object list in natural order (by id)
		testPersons.sort();
		assertEquals(0, testPersons.get(0).id);
		assertEquals(3, testPersons.get(6).id);
		assertEquals(7, testPersons.get(12).id);
		// Sorting object list in reverse order (by id)
		testPersons.sort(testComparator);
		assertEquals(7, testPersons.get(0).id);
		assertEquals(3, testPersons.get(6).id);
		assertEquals(0, testPersons.get(12).id);
		// Sorting object list in natural order (by age, 0 - isAllive)
		testPersons.sort(testComparator.ageCompare);
		assertEquals(37, testPersons.get(0).age);
		assertEquals(58, testPersons.get(6).age);
		assertEquals(94, testPersons.get(12).age);
	}

	@Test
	void indexOfLast() {
		// Changed condition. This objects are equal if their ids and names are equal;
		Person pattern = new Person(0, "Alexander Solzhenitsyn", 89);
		assertEquals(0, personsMen.lastIndexOf(pattern));
		pattern = new Person(1, "Fyodor Dostoevsky", 59);
		assertEquals(1, personsMen.lastIndexOf(pattern));
		pattern = new Person(7, "Taras Shevchenko", 47);
		assertEquals(7, personsMen.lastIndexOf(pattern));

	}

	@Test
	void removeTest() {
		Person pattern = new Person(7, "Taras Shevchenko", 47);
		assertTrue(personsMen.remove(pattern));
		pattern = new Person(6, "Anton Chekhov", 44);
		assertTrue(personsMen.remove(pattern));
		pattern = new Person(0, "Alexander Solzhenitsyn", 89);
		assertTrue(personsMen.remove(pattern));

	}

	@Test
	void addAllTest() {
		List<Person> listToTest = new ArrayList<Person>();
		for (int i = 0; i < personsMen.size(); i++) {
			listToTest.add(personsMen.get(i), listToTest.size());
		}
		for (int i = 0; i < personsWomen.size(); i++) {
			listToTest.add(personsWomen.get(i), listToTest.size());
		}
		personsMen.addAll(personsWomen);
		boolean isListsEquals = listComparer(listToTest, personsMen);
		assertTrue(isListsEquals);
	}

	@Test
	void removeAllTest() {
		personsMen.removeAll(personToDelete);
		boolean isListsEquals = listComparer(personsMen, checkDeletedPersons);
		assertTrue(isListsEquals);
	}

	@Test
	void retainAllTest() {
		personsMen.retainAll(personToRetain);
		boolean isListsEquals = listComparer(checkRetainPerson, personsMen);
		assertTrue(isListsEquals);
	}

	@Test
	void setTest() {
		Person person = new Person(8, "George Orwell", 46);
		personsMen.set(person, 7);
		assertTrue(personsMen.get(7).equals(person));
		personsMen.set(person, 0);
		assertTrue(personsMen.get(0).equals(person));
	}

	@Test
	void swapTest() {
		Person manToSwap1 = new Person(0, "", 0);
		manToSwap1 = personsMen.get(0);
		Person manToSwap2 = new Person(0, "", 0);
		manToSwap2 = personsMen.get(7);
		personsMen.swap(0, 7);
		assertTrue(manToSwap1.equals(personsMen.get(7)));
		assertTrue(manToSwap2.equals(personsMen.get(0)));
	}

	@Test
	void indexOfPredicateTest() {
		int q = 2;
		Predicate<Integer> pred = n -> n % q == 0;
		assertEquals(1, intList.indexOf(pred));
		assertEquals(9, intList.indexOf(n -> n % 10 == 0));
		assertEquals(-1, intList.indexOf(n -> n % 16 == 0));
	}
}
