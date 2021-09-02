package telran.currency.service;

import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterFileImpl extends AbstractCurrencyConverter {
	
	public static CurrencyConverter getCurrencyConverter(String filePath) throws Exception{
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
			Map<String, Double> rates = (Map<String, Double>) input.readObject();

			
			return new CurrencyConverterFileImpl(rates);
		}
		
	} 
	
	protected CurrencyConverterFileImpl(Map<String, Double> rates) {
		super(rates);
		
	}

	@Override
	protected void refresh() {
		// TODO Auto-generated method stub

	}

}
