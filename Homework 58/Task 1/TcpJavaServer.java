package telran.net;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class TcpJavaServer extends NetJavaServer {
private ServerSocket serverSocket;
private int port;
private ApplProtocolJava protocol;
private ExecutorService executor;
private final int TIME_OUT = 20000;

	public TcpJavaServer(int port, ApplProtocolJava protocol)  {
	this.port = port;
	this.protocol = protocol;
	try {
		serverSocket = new ServerSocket(port);
	} catch (IOException e) {
		System.out.println("Port in use " + port);
	}
	executor = Executors.newCachedThreadPool();
	
}

	@Override
	public void run() {
		System.out.println("TCP server is listening on port " + port);
		try {
			while(!executor.isShutdown()) {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(TIME_OUT);
				executor.execute(new TcpClientServer(socket, protocol));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void gracefullyShutDown() {
		executor.shutdown();
		try {
			executor.awaitTermination(TIME_OUT, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e1) {
		}
		Thread.currentThread().interrupt();
		try {
			serverSocket.close();
		} catch (IOException e) {
			
		}
		System.out.println("TCP server stopped!");
	}

}
