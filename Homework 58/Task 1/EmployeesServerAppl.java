package telran.employees.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import telran.employees.net.EmployeesProtocol;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.*;
import telran.view.ConsoleInputOutput;
import telran.view.EndOfInputException;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class EmployeesServerAppl {
	
	private static NetJavaServer server;
	
	@SuppressWarnings("deprecation")
	private static Properties configServer() throws Exception{
		Properties props = new Properties();
		File config = new File("server.properties");
		if (config.exists()) {
			props.load(new FileInputStream("server.properties"));
		} else {
			props.setProperty("Port", "2000");
			props.setProperty("Protocol", "TcpJavaServer"); //"TcpJavaServer"; "UdpJavaServer"
			config.createNewFile();
			props.save(new FileOutputStream(config), "properties for server");
		}
		return props;
	}
	


	public static void main(String[] args) throws Exception  {
		InputOutput io = new ConsoleInputOutput();
		Item startServer = Item.of("Start server", EmployeesServerAppl::startServer);
		Item stopServer = Item.of("Stop server", EmployeesServerAppl::stopServer);
		Item[] items = {startServer, stopServer};
		Menu menu = new Menu("Employees Server", items);
		try {
			menu.perform(io);
		} catch (EndOfInputException e) {
				
		
		}
		
	}
	
	private static void startServer(InputOutput io) {
		try {	
		Properties props = configServer();		
		ApplProtocolJava protocol = new EmployeesProtocol(EmployeesMethods.getEmployees("employees.data"));
		Class<?> clazz = (Class<?>)Class.forName("telran.net." + props.getProperty("Protocol"));
		server = (NetJavaServer)clazz.getConstructor(int.class, ApplProtocolJava.class).newInstance(Integer.parseInt(props.getProperty("Port")), protocol);
		server.start();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	private static void stopServer(InputOutput io) {
		server.gracefullyShutDown();
	}
	
	
	
	

}
