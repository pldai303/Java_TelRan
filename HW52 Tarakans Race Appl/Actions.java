import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Actions {

	private static boolean photofinish = false;
	
	public static void setPhotoFinish(boolean isFinished) {
		photofinish = isFinished;
	}
	
	public static boolean getPhotoFinish() {
		return photofinish;
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
			racers.stream().forEach(e -> {
				if (e.getWinnrer()) {
					System.out.printf("Number %d is a WINNER \n", e.getNumber());
				}
			});
			io.writeObjectLine("Race finished!");
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}

	}

}
