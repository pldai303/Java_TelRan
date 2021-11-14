package telran.concurrency;

public class MessageBox {

	private String message;
	private boolean finished = false;
	
	public synchronized void sendMessage(String message) {
		
		while(this.message != null) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
		
		this.message = message;
		this.notifyAll();
		
	}
	
	public synchronized String takeMessage() throws InterruptedException{
		while (this.message == null) {
			this.wait();
		}
		String res = message;
		this.message = null;
		this.notifyAll();
		return res;
	}
	
	public void finishSending() {
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
}
