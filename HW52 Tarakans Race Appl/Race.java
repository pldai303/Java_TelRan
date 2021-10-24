import java.util.ArrayList;

public class Race {
	private static boolean isRaceFinished;
	private static int winner;
	
	public Race (int nRacers, int lDistance) {
		isRaceFinished = false;
		ArrayList<Tarakan> racers = new ArrayList<Tarakan>();
		for (int i = 1; i <= nRacers; i++) {
			racers.add(new Tarakan(lDistance, i));
		}
		racers.stream().forEach(e -> e.start());
		racers.stream().forEach(e -> {
			try {
				e.join();
			} catch (InterruptedException exp) {

			}
		});
	}
	
	public static boolean getRaceStatus() {
		return isRaceFinished;
	}
	
	public static void setRaceStatusFinished() {
		isRaceFinished = true;
	}
	
	public static void setRaceWinner(int numberOfWinner) {
		winner = numberOfWinner;
	}
	
	public static int getRaceWinner() {
		return winner; 
	}
	
}
