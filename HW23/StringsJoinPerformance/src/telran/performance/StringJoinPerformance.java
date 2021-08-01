package telran.performance;

public class StringJoinPerformance extends PerformanceTest {
	String[] array;
	StringJoin join;
	public StringJoinPerformance(int nStrings, StringJoin joinImpl, 
			String testName, int nRuns) {
		super(testName, nRuns);
		array = new String[nStrings];
		for (int i = 0; i < nStrings; i++) {
			array[i] = "string";
		}
		this.join = joinImpl;
	}
	@Override
	protected void runTest() {
		join.join(";", array);

	}

}


//2 test with name StringBuilderTest and StringTest
