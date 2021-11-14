package telran.concurrency.consumer;

import telran.concurrency.MessageBox;

public class Consumer extends Thread {

	public Consumer(MessageBox messageBox) {
		this.messageBox = messageBox;
		setDaemon(false);
	}

	public void setMessageBox(MessageBox messageBox) {
		this.messageBox = messageBox;
	}

	private MessageBox messageBox;

	@Override
	public void run() {
		while (true) {
			try {
				if (messageBox.isFinished()) {
					break;
				}
				String message = messageBox.takeMessage();
				
				System.out.printf("Thread: %s, message: %s \n",getName(), message);
			} catch (InterruptedException e) {
			
				
			}
		}
	}

}
