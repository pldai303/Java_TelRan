package telran.concurrency;

import telran.concurrency.consumer.Consumer;
import telran.concurrency.producer.Producer;


public class Controller {

	private static final int N_MESSAGES = 20;
	private static final int N_CONSUMERS = 10;

	public static void main(String[] args) {
		MessageBox messageBox = new MessageBox();
		Producer producer = new Producer(messageBox, N_MESSAGES);
		for (int i = 0; i < N_CONSUMERS; i++) {
			new Consumer(messageBox).start();
		}
		producer.start();
	}

}
