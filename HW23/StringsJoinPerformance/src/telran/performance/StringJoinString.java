package telran.performance;

public class StringJoinString implements StringJoin{

	@Override
	public String join(String delimiter, String[] array) {
		String string = "";
		for (String str : array) {
		string += str + delimiter;	
		}
		return string;
	}
	
	
}
