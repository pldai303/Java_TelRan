package telran.performance;

public class StringJoinBuilder implements StringJoin{

	@Override
	public String join(String delimiter, String[] array) {
		StringBuilder sb = new StringBuilder("");
		for (String str : array) {
			sb.append(str);
			sb.append(delimiter);
			}
		return sb.toString();
	}

}
