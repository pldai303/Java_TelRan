package telran.utils;

public class Person implements Comparable<Person> {
	
	int id;
	String name;
	int age;

	public Person(int id1, String name, int age) {
		super();
		this.id = id1;
		this.name = name;
		this.age = age;
	}

	public int compareTo(Person o) {
		return this.id - o.id > 0 ? 1 : -1;
	}

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if ((this.id == other.id) && (this.name == other.name) && (this.age == other.age)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name = " + name + ", age=" + age + "]";
	}

}
