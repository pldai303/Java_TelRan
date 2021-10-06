package game.controller;



import game.controller.actions.GameActions;
import game.net.GameProxyJava;
import game.services.UserMethods;
import game.services.UserMethodsImpl;
import telran.view.*;
import telran.net.dto.*;

public class GameClientAppl {

	
	private static final String HOST = "localhost";
	
	private static String name = 
			

				"██████╗░██╗░░░██╗██╗░░░░░██╗░░░░░░██████╗  ░█████╗░███╗░░██╗██████╗░  ░█████╗░░█████╗░░██╗░░░░░░░██╗░██████╗   ██╗░░░██╗░░░  ░░███╗░░░░░░░███╗░░\n" +
				"██╔══██╗██║░░░██║██║░░░░░██║░░░░░██╔════╝  ██╔══██╗████╗░██║██╔══██╗  ██╔══██╗██╔══██╗░██║░░██╗░░██║██╔════╝   ██║░░░██║░░░  ░████║░░░░░░████║░░\n" + 
				"██████╦╝██║░░░██║██║░░░░░██║░░░░░╚█████╗░  ███████║██╔██╗██║██║░░██║  ██║░░╚═╝██║░░██║░╚██╗████╗██╔╝╚█████╗░   ╚██╗░██╔╝░░░  ██╔██║░░░░░██╔██║░░\n" + 
				"██╔══██╗██║░░░██║██║░░░░░██║░░░░░░╚═══██╗  ██╔══██║██║╚████║██║░░██║  ██║░░██╗██║░░██║░░████╔═████║░░╚═══██╗   ░╚████╔╝░░░░  ╚═╝██║░░░░░╚═╝██║░░\n" + 
				"██████╦╝╚██████╔╝███████╗███████╗██████╔╝  ██║░░██║██║░╚███║██████╔╝  ╚█████╔╝╚█████╔╝░░╚██╔╝░╚██╔╝░██████╔╝   ░░╚██╔╝░░██╗  ███████╗██╗███████╗\n" + 
				"╚═════╝░░╚═════╝░╚══════╝╚══════╝╚═════╝░  ╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░  ░╚════╝░░╚════╝░░░░╚═╝░░░╚═╝░░╚═════╝░   ░░░╚═╝░░░╚═╝  ╚══════╝╚═╝╚══════╝";


	public static void main(String[] args) throws Exception {
		InputOutput io = new ConsoleInputOutput();
		UserMethods userService = null;
		int port = 0;
		String str = io.readString("Enter protocol TCP or UDP:");
		Protocols selectedProtocol = Protocols.UDP;
		if (str.toUpperCase().equals("TCP"))
		selectedProtocol = Protocols.TCP;
		switch (selectedProtocol) {
		case TCP: {
			port = 5000;
			break;
		}
		case UDP: {
			port = 2000;
			break;
		}
		}
		
		try {
			userService = new GameProxyJava(HOST, port, selectedProtocol);
			Item[] items = GameActions.getUsersItems(userService);
			Menu menu = new Menu(name, items);
			menu.perform(io);
		} catch (Exception e) {
			io.writeObjectLine("No server connection");
		}
	}
}
		

	

