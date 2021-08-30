package telran.currency.services;

import java.util.HashMap;
import java.util.Map;


import java.io.*;

public class CurrencyMethodsMapImpl implements CurrencyMethods {
	private Map<String, String> mapOfCountriesAndCodes;
	private Map<String, Double> mapOfCodesAndUnits;
	
	@Override
	public Map<String, String> getCountriesAndCodes() {
		mapOfCountriesAndCodes = new HashMap<String, String>();
		try (BufferedReader br = new BufferedReader(new FileReader("codes-all_csv.txt"))){
			for (String line; (line = br.readLine()) != null;) {
				String[] words = line.split("\t");
				mapOfCountriesAndCodes.put(words[0], words[2]);
			}
			
		} catch (Exception e) {
			
		}
		return mapOfCountriesAndCodes;
	}

	@Override
	public Map<String, Double> getCodesAndUnits() {
		mapOfCodesAndUnits = new HashMap<String, Double>();
		try ( ObjectInput objInputStream = new ObjectInputStream(new FileInputStream("rates_2021-08-28"))){
			mapOfCodesAndUnits = (Map)objInputStream.readObject();
			objInputStream.close();
		} catch (Exception e) {
			
		}
		return mapOfCodesAndUnits;
	}
	
	private Double getPriceOfCurrency(String currencyNameOrCode) {
		String value = "";
		Double am = 0.0;
		if ( mapOfCountriesAndCodes.containsKey(currencyNameOrCode) ) {
			value = mapOfCountriesAndCodes.get(currencyNameOrCode);
		} else 
			if (mapOfCountriesAndCodes.containsValue(currencyNameOrCode)) {
				value = currencyNameOrCode;
			}
		if (mapOfCodesAndUnits.containsKey(value)) {
			am = mapOfCodesAndUnits.get(value);
		}
		return am;
	}
	

	@Override
	public Double convert(String from, String to, int amount) {
		Double priceOfFirst = getPriceOfCurrency(from); 
		Double priceOfSecond = getPriceOfCurrency(to);
		Double res = (amount * priceOfSecond) / priceOfFirst; 
		return res;
	}



}
