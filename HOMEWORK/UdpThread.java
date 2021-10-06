package game.thread;

import game.controller.UdpServerJava;
import game.net.GameProtocol;
import game.services.UserMethodsImpl;

public class UdpThread extends Thread {
private UdpServerJava server;
	
	public UdpThread(int port) {
		try {
			server = new UdpServerJava(port, new GameProtocol(UserMethodsImpl.getEmptyUser()));
		} catch (Exception e) {
			
		}
	}
	
	public void run() {
		server.run();
	}

}
