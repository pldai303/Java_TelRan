public class Tarakan extends Thread{
	private static final int min = 2;
	private static final int max = 5;
	private int distance;
	private int number;

	public Tarakan(int distance, int number) {
		this.distance = distance;
		this.number = number;
	}
	@Override
	public void run() {
		for (int i = 0; i < distance; i++) {
			try {
					sleep((int) ((Math.random() * (max - min)) + min));
			} catch (InterruptedException e) {
				
			}
		}
		if (!Race.getRaceStatus()) {
			Race.setRaceStatusFinished();
			Race.setRaceWinner(number);
		}
	}
}
