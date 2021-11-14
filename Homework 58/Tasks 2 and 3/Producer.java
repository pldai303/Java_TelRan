package telran.concurrency.producer;

import java.util.Scanner;

import telran.concurrency.MessageBox;

public class Producer extends Thread {

	private final MessageBox messageBox;
	private int nMessages;
	
	@Override
	public void run() {
		for (int i = 0; i < nMessages; i++) {
		messageBox.sendMessage("Message " + (i+1));
		}
		messageBox.finishSending();
	}

	public Producer(MessageBox messageBox, int nMessages) {
		this.messageBox = messageBox;
		this.nMessages = nMessages;
	}
	
	
	
}
