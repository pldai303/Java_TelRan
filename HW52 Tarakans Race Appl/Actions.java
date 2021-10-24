public class Actions {

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
			Race race = new Race(io.readInt("Enter racers count"), 
					io.readInt("Enter distance length"));
			
			io.writeObjectLine("Winner of race is number " + race.getRaceWinner());
			
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}

	}

}
