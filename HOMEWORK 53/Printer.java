
public class Printer extends Thread {
private int nNumbers;	
private int nPortions;
private int numberToPrint;
private Printer nextPrinter;
private int numbersCounter;
private int portionCounter = 0;
	
public Printer(int nNumbers, int nPortions, int numberToPrint) {
	this.nNumbers = nNumbers;
	this.nPortions = nPortions;
	this.numberToPrint = numberToPrint;
	this.numbersCounter = (int) (nNumbers / nPortions); 
}	

public void run() {
	if (portionCounter < nPortions)
	{
		try {
		for (int i = 0; i<numbersCounter; i++) {
			System.out.print(numberToPrint);
		}
		System.out.println();
		portionCounter++;
		this.interrupt();
		this.nextPrinter.run();
		} catch (Exception e) {
			this.nextPrinter.run();
		}
	}
}

public void setNextPrinter(Printer nextPrinter) {
	this.nextPrinter = nextPrinter;
}

public Printer getNextPrinter() {
	return this.nextPrinter;
}

	
}
