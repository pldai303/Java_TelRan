package game.thread;

import game.net.GameProtocol;
import game.services.UserMethodsImpl;
import telran.net.TcpJavaServer;

public class TcpThread extends Thread {
private TcpJavaServer server;
	
	public TcpThread(int port) {
		server = new TcpJavaServer(port, new GameProtocol(UserMethodsImpl.getEmptyUser()));
	}
	
	public void run() {
		server.run();
	}
	
}
