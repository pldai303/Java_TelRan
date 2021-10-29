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
			io.writeObjectLine("Place   Racer num.   Time (ms.)");
			race.getRaceResults().entrySet().stream().forEach( t-> 
				io.writeObjectLine(
						 " ".repeat(3) + t.getKey() + 
						 " ".repeat(6) + t.getValue().getNumber() +  
						 " ".repeat(11) + t.getValue().getTime()));
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}

	}

}
