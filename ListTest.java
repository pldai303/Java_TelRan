package telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListTest {
	List<Person> personsMen;
	List<Person> personsWomen;
	List<Person> personToDelete;
	List<Person> checkDeletedPersons;
	List<Person> personToRetain;
	List<Person> checkRetainPerson;
	
	public static boolean listComparer(List<Person> firstList, List<Person> secondList) {
		boolean testResult = false;
		if (firstList.size() == secondList.size())
		{
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
		personsMen = new ArrayList<>();
		personsMen.add(new Person(18 , "Alexander Solzhenitsyn")); //0
		personsMen.add(new Person(11 , "Fyodor Dostoevsky"));      //1
		personsMen.add(new Person(13 , "Alexander Pushkin"));      //2
		personsMen.add(new Person(14 , "Leo Tolstoy"));            //3
		personsMen.add(new Person(18 , "Alexander Solzhenitsyn")); //4 
		personsMen.add(new Person(15 , "Nikolai Gogol"));          //5  
		personsMen.add(new Person(16 , "Anton Chekhov"));          //6
		personsMen.add(new Person(19, "Taras Shevchenko"));        //7
		personsMen.add(new Person(17 , "Mikhail Sholokhov"));      //8
		personsMen.add(new Person(19, "Taras Shevchenko"));        //9

		personsWomen = new ArrayList<>();
		personsWomen.add(new Person(21 , "Erich Maria Remarque")); //0
		personsWomen.add(new Person(25 , "FAgatha Christie")); //1
		personsWomen.add(new Person(26 , "Astrid Lindgren")); //2
	    personsWomen.add(new Person(29 , "Lesya Ukrainka")); //3
	    personsWomen.add(new Person(20 , "Joanne Rowling")); //4
	    personsWomen.add(new Person(23 , "Suzanne Collins")); //5
		
	    personToDelete = new ArrayList<>();
	    personToDelete.add(new Person(18 , "Alexander Solzhenitsyn"));
	    personToDelete.add(new Person(19, "Taras Shevchenko"));
	    personToDelete.add(new Person(15 , "Nikolai Gogol"));
	    checkDeletedPersons = new ArrayList<>();
	    checkDeletedPersons.add(new Person(11 , "Fyodor Dostoevsky"));      
	    checkDeletedPersons.add(new Person(13 , "Alexander Pushkin"));      
	    checkDeletedPersons.add(new Person(14 , "Leo Tolstoy"));            
	    checkDeletedPersons.add(new Person(16 , "Anton Chekhov"));          
	    checkDeletedPersons.add(new Person(17 , "Mikhail Sholokhov"));
	    
	    personToRetain = new ArrayList<>();
	    personToRetain.add(new Person(19, "Taras Shevchenko"));
	    personToRetain.add(new Person(18 , "Alexander Solzhenitsyn"));
	    checkRetainPerson = new ArrayList<>();
	    checkRetainPerson.add(new Person(18 , "Alexander Solzhenitsyn"));
	    checkRetainPerson.add(new Person(18 , "Alexander Solzhenitsyn"));
	    checkRetainPerson.add(new Person(19, "Taras Shevchenko"));
	    checkRetainPerson.add(new Person(19, "Taras Shevchenko"));
	}
	@Test
	void indexOfLast() {
		//Changed condition. This objects are equal if their ids and names are equal; 
		Person pattern = new Person(18, "Alexander Solzhenitsyn");
		assertEquals(4, personsMen.lastIndexOf(pattern));
		pattern = new Person(11, "Fyodor Dostoevsky");
		assertEquals(1, personsMen.lastIndexOf(pattern));
		pattern = new Person(19, "Taras Shevchenko");
		assertEquals(9, personsMen.lastIndexOf(pattern));
	}
	@Test 
	void removeTest() {
		Person pattern = new Person(19, "Taras Shevchenko");
		assertTrue(personsMen.remove(pattern));
		pattern = new Person(16 , "Anton Chekhov");
		assertTrue(personsMen.remove(pattern));
		pattern = new Person(18 , "Alexander Solzhenitsyn");
		assertTrue(personsMen.remove(pattern));
		pattern = new Person(118 , "Alexander Solzhenitsyn");
		assertFalse(personsMen.remove(pattern));
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
		Person person = new Person(1984, "George Orwell");
		personsMen.set(person, 9);
		assertTrue(personsMen.get(9).equals(person));
		personsMen.set(person, 0);
		assertTrue(personsMen.get(0).equals(person));
	}
	@Test 
	void swapTest(){
		Person manToSwap1 = new Person(0,"");
		manToSwap1 = personsMen.get(0);
		Person manToSwap2 = new Person(0,"");
		manToSwap2 = personsMen.get(9);
		personsMen.swap(0, 9);
		assertTrue(manToSwap1.equals(personsMen.get(9)));
		assertTrue(manToSwap2.equals(personsMen.get(0)));
	}
}
	
