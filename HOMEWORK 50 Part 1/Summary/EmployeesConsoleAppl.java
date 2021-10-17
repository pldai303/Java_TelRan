package telran.employees.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Properties;

import telran.employees.controller.actions.EmployeesActions;
import telran.employees.net.EmployeesProxyNetJava;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.NetJavaClient;
import telran.net.TcpJavaClient;
import telran.net.UdpJavaClient;
import telran.view.*;

public class EmployeesConsoleAppl {

	private static final String DEFAULT_FILE_PATH = "employees.data";
	
	private static Properties configClient() throws Exception{
		Properties props = new Properties();
		File config = new File("config.properties");
		if (config.exists()) {
			props.load(new FileInputStream("config.properties"));
		} else {
			props.setProperty("Port", "2000");
			props.setProperty("Host", "localhost");
			props.setProperty("Protocol", "TcpJavaClient"); //"TcpJavaClient"; "UdpJavaClient"
			config.createNewFile();
			props.save(new FileOutputStream(config), "properties for client");
		}
		return props;
	}
	

	public static void main(String[] args) throws Exception {
		Properties props = configClient();
		String filePath = args.length > 0 ? args[0] : DEFAULT_FILE_PATH;
		InputOutput io = new ConsoleInputOutput();
		EmployeesMethods employeesService = null;
		try {
			Class<?> clazz = (Class<?>)Class.forName("telran.net." + props.getProperty("Protocol"));
			NetJavaClient client = (NetJavaClient)clazz.getConstructor(String.class, int.class).newInstance("localhost", Integer.parseInt(props.getProperty("Port")));
			employeesService = new EmployeesProxyNetJava(client);
		} catch (Exception e) {
			io.writeObjectLine(e.getMessage());
		}
		Item[] items = EmployeesActions.getEmployeesItems(employeesService,
				Arrays.asList("QA", "Development", "Audit", "Management"));
		Menu menu = new Menu("Employees Menu", items);
		try {
			menu.perform(io);
		} catch (EndOfInputException e) {
					try {
						employeesService.save(filePath);
					} 
				
			catch (Exception e1) {
				io.writeObjectLine(e1.getMessage());
			}
		}
	}
}
		

	

