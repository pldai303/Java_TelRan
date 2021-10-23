import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Tarakan extends Thread{
	private static final int min = 2;
	private static final int max = 5;
	private int distance;
	private int number;
	private long time;
	
	public Tarakan(int distance, int number) {
		this.distance = distance;
		this.number = number;
	}
	@Override
	public void run() {
		Instant start = Instant.now();
		for (int i = 0; i < distance; i++) {
			try {
				sleep((int) ((Math.random() * (max - min)) + min));
			} catch (InterruptedException e) {
				
			}
		}
		time = ChronoUnit.MILLIS.between(start, Instant.now());
	}
	
	public long getTime() {
		return time;
	}
	
	public int getNumber() {
		return number;
	}
}
