import java.util.*;

public class Race {

	public Race(int nRacers, int lDistance) {
		Tarakan.reset();
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

	public Map<Integer, Tarakan> getRaceResults() {
		return Tarakan.getResults();
	}

}
