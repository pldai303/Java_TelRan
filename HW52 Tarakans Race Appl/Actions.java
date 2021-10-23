import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Actions {

	private static int counter;

	private static void printRacerResults(Tarakan obj, int place) {
		if (place == 1) {
			System.out.printf("Number %d is on a %d place. Time: %d ms. (WINNER!)\n", obj.getNumber(), place,
					obj.getTime());
		} else {
			System.out.printf("Number %d is on a %d place. Time: %d ms.\n", obj.getNumber(), place, obj.getTime());
		}
	}

	private Actions() {

	}

	public static Item[] getUsersItems() throws Exception {
		return getItems();
	}

	private static Item[] getItems() throws Exception {
		Item startRace = Item.of("Start new race", Actions::startNewRace);
		Item[] items = { startRace, Item.exit() };
		return items;
	}

	private static void startNewRace(InputOutput io) {
		try {
			int nRacers = io.readInt("Enter racers count");
			int lDistance = io.readInt("Enter distance length");
			io.writeObjectLine("Race was started...");
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
			counter = 1;
			racers.stream().sorted(Comparator.comparingLong(Tarakan::getTime)).collect(Collectors.toList())
					.forEach(e -> {
						printRacerResults(e, counter);
						counter++;
					});
			io.writeObjectLine("Race finished!");
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}

	}

}
