public class MainRace {
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		try {
			Item[] items = Actions.getUsersItems();
			Menu menu = new Menu("Tarakans Race Application \n", items);
			menu.perform(io);
		} catch (Exception e) {

		}
	}

}
