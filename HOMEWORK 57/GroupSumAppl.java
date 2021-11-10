import java.util.*;
import java.util.concurrent.*;

public class GroupSumAppl {

	private static final int N_GROUPS = 100000;
	private static final int N_NUMBERS_GROUP = 10000;

	public static void main(String[] args) {
		int[] nThreads = new int[] {1, 2, 4, 10, 40, 100, 200, 1000, 10000, 50000};
		int groups[][] = getGroups();
		for (int i=0; i < nThreads.length; i++) {
		PerformanceTest pf = new SumCountPerformance("Test " + i, N_GROUPS, nThreads[i], groups);
		pf.run();
		}
	}

	private static int[][] getGroups() {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		int res[][] = new int[N_GROUPS][N_NUMBERS_GROUP];
		for (int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_GROUP; j++) {
				res[i][j] = tlr.nextInt(100);
			}
		}
		return res;
	}

}
