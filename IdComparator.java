package telran.utils;

import java.util.Comparator;

public class IdComparator implements Comparator<Person> {
	public AgeComparator ageCompare = new AgeComparator();
	public NameLengthComparator nameCompare = new NameLengthComparator();
	@Override
	public int compare(Person o1, Person o2) {
		return o1.getId() - o2.getId() < 0 ? 1 : -1;
	}
}
class AgeComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.getAge() - o2.getAge() > 0 ? 1 : -1;
	}
}
class NameLengthComparator implements Comparator<Person> {
	@Override
	public int compare(Person o1, Person o2) {
		return o1.name.length() - o2.name.length() > 0 ? 1 : -1;
	}
}


