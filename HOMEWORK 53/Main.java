import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final int N_PRINTERS = 4;
	
	public static void main(String[] args) throws InterruptedException {
		List<Printer> printerList = new ArrayList();
		for (int i = 0; i<= N_PRINTERS; i++) {
			Printer p = new Printer(100, 10, i+1);
			if (printerList.size() > 0) {
				if (i == N_PRINTERS) {
					printerList.get(i-1).setNextPrinter(printerList.get(0));
					break;
				} else {
					printerList.get(i-1).setNextPrinter(p);
				}
			}
			printerList.add(p);
		}
		
		printerList.get(0).start();
	}

}
