import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SumCountPerformance extends PerformanceTest {

	int threadsCount;
	int[][] arr;
	int nGroups;
	
	public SumCountPerformance(String testName, int nGroups, int threadsCount, int[][] arr) {
		super(testName, threadsCount);
		this.nGroups = nGroups;
		this.arr = arr;
		this.threadsCount = threadsCount;
	}

	@Override
	protected void runTest() {
		ExecutorService executor = Executors.newFixedThreadPool(threadsCount);
		List<OneGroupSum> tasks = startGroups(executor, arr);
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
		
		}
		System.out.println(tasks.stream().mapToLong(t -> t.res).sum());
	}
	
	private List<OneGroupSum> startGroups(ExecutorService executor, int[][] groups) {
		List<OneGroupSum> res = new ArrayList<>();
		for (int i = 0; i < nGroups; i++) {
			OneGroupSum groupSum = new OneGroupSum(groups[i]);
			res.add(groupSum);
			executor.execute(groupSum);
		}
		return res;
	}


}
