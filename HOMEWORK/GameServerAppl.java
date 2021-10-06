package game.controller;

import game.net.GameProtocol;
import game.services.UserMethodsImpl;
import game.thread.TcpThread;
import game.thread.UdpThread;
import telran.net.TcpJavaServer;



public class GameServerAppl {

	public static void main(String[] args) throws Exception {
		TcpThread tcp = new TcpThread(5000);
		new Thread(tcp).start();
		UdpThread udp = new UdpThread(2000);
		new Thread(udp).start();
	}
}
