

public abstract class PerformanceTest {

private String testName;
private int threadsCount;	
	


public PerformanceTest(String testName, int threadsCount) {
	this.testName = testName;
	this.threadsCount = threadsCount;
}

public void run() {
long start = System.currentTimeMillis();
runTest();
printResult(System.currentTimeMillis() - start);
}

private void printResult(long diffTime) {
	System.out.printf("%s;  Threads pool size: %d; Running time: %d\n", testName, threadsCount, diffTime);
}

protected abstract void runTest(); 

}
