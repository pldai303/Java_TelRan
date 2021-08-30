package telran.currency.services;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Map;

public interface CurrencyMethods extends Serializable {

	Map<String, String> getCountriesAndCodes() ;
	Map<String, Double> getCodesAndUnits();
	Double convert (String from, String to, int amount);
	
	
	
}
