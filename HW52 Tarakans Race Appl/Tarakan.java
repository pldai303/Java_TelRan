
public class Tarakan extends Thread{
	private static final int min = 2;
	private static final int max = 5;
	private int distance;
	private int number;
	private boolean isWinner = false;
	
	public Tarakan(int distance, int number) {
		this.distance = distance;
		this.number = number;
	}
	@Override
	public void run() {
		Actions.setPhotoFinish(false);
		isWinner = false;
		for (int i = 0; i < distance; i++) {
			try {
					if ((i == distance-1)  && (!Actions.getPhotoFinish())) {
						Actions.setPhotoFinish(true);
						isWinner = true;
					} else {
					sleep((int) ((Math.random() * (max - min)) + min));
					}
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean getWinnrer() {
		return isWinner;
	}
}
