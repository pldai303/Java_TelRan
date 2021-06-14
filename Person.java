package telran.utils;

public class Person {

	
public Person(int id1, String name) {
		super();
		this.id = id1;
		this.name = name;
	}
int id;
String name;
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
	if ( (id != other.id) | (name != other.name)) 
		return false;
	return true;
}
}
