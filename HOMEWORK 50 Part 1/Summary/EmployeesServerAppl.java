package telran.employees.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import telran.employees.net.EmployeesProtocol;
import telran.employees.services.EmployeesMethods;
import telran.employees.services.EmployeesMethodsMapsImpl;
import telran.net.*;

public class EmployeesServerAppl {
	
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

	public static void main(String[] args)  {
		try {	
		Properties props = configServer();		
		ApplProtocolJava protocol = new EmployeesProtocol(EmployeesMethods.getEmployees("employees.data"));
		Class<?> clazz = (Class<?>)Class.forName("telran.net." + props.getProperty("Protocol"));
		Runnable server = (Runnable)clazz.getConstructor(int.class, ApplProtocolJava.class).newInstance(Integer.parseInt(props.getProperty("Port")), protocol);
		server.run(); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
