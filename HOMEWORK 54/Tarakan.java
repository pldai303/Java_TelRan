import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Tarakan extends Thread {
	private static final int min = 2;
	private static final int max = 5;
	private static final Object mutex = new Object();
	private static Map<Integer, Tarakan> results = new HashMap<>();
	private static Integer place = 1;
	private int distance;
	private int number;
	private long time;

	public Tarakan(int distance, int number) {
		this.distance = distance;
		this.number = number;
	}

	@Override
	public void run() {
		Instant timeOfStart = Instant.now();
		for (int i = 0; i < this.distance; i++) {
			try {
				sleep((int) ((Math.random() * (max - min)) + min));
			} catch (InterruptedException e) {

			}
		}
		this.time = ChronoUnit.MILLIS.between(timeOfStart, Instant.now());
		addRacerToResults(this);
	}

	private static void addRacerToResults(Tarakan racer) {
		synchronized (mutex) {
			results.putIfAbsent(place, racer);
			place++;
		}
	}

	public static Map<Integer, Tarakan> getResults() {
		return results;
	}

	public static void reset() {
		place = 1;
		results.clear();
	}

	public int getNumber() {
		return number;
	}

	public long getTime() {
		return time;
	}

}
