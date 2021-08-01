package telran.utils;

public interface Rule {

	public void check(int number, int min, int max) throws NoRuleMatchException;

}
